package com.tcl.id3v2;

import com.tcl.id3v2.id3v23.ID3v23;
import com.tcl.id3v2.id3v24.ID3v24;
import com.tcl.id3v2.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * @author shuhao1.zhang
 * @version 1.0
 * @className ID3Tag
 * @description 解析ID3v2Tag
 * @date 2023/3/2 16:42
 */
public class ID3v2Tag {

    private static final Logger log = LoggerFactory.getLogger(ID3v2Tag.class);

    // tag  ID3
    final byte[] id3 = new byte[3];
    // 主版本
    final byte[] version = new byte[1];
    // 副版本
    final byte[] subVersion = new byte[1];
    // flags
    final byte[] flags = new byte[1];
    // 标签大小字节
    final byte[] sizeByte = new byte[4];
    // 标签总大小-10byte
    final int tagSize;
    // tag标签所有frames
    final byte[] frames;

    public ID3v2Tag(File file) {

        try (BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(file.toPath()))){
            // ID3
            int len1 = bis.read(id3);
            log.info("读取长度：" + len1);
            log.info("ID3v2标记：" + new String(id3, StandardCharsets.ISO_8859_1));

            // 主版本
            int len2 = bis.read(version);
            log.info("读取长度：" + len2);
            switch (version[0]) {
                case 3:
                    log.info("这是一个ID3v2.3.0的标签");
                    break;
                case 4:
                    log.info("这是一个ID3v2.4.0的标签");
                    break;
                default:
                    log.info("这是一个非ID3v2的标签， 无法解析");
                    break;
            }

            // 副版本
            int len3 = bis.read(subVersion);
            log.info("读取长度：" + len3);
            log.info("副版本为：" + subVersion[0]);

            // flags
            int len4 = bis.read(flags);
            log.info("读取长度：" + len4);
            log.info("ID3v2 Flags 字段");
            
            // 标签大小计算
            int len5 = bis.read(sizeByte);
            log.info("读取长度：" + len5);

            tagSize = Util.getTagByteSize(sizeByte);

            frames = new byte[tagSize];
            int len6 = bis.read(frames);
            log.info("读取长度：" + len6);
            log.info("ID3v2标签-10byte为：" + tagSize);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ID3v2X getID3v2X(){
        switch (version[0]) {
            case 3:
                return new ID3v23(frames, flags);
            case 4:
                return new ID3v24(frames, flags);
            default:
                return new ID3v2X() {
                    @Override
                    public String getAlbumName() {
                        return "未知专辑名称";
                    }

                    @Override
                    public String getSingerName() {
                        return "未知歌手";
                    }

                    @Override
                    public String getSongName() {
                        return "未知歌曲名称";
                    }

                    @Override
                    public String getLyricistWriter() {
                        return "未知歌词作者";
                    }

                    @Override
                    public String getComposer() {
                        return "未知作曲家";
                    }

                    @Override
                    public String getCopyright() {
                        return "未知版权";
                    }
                };
        }
    }

}
