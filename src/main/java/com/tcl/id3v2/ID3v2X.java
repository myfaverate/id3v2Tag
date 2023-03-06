package com.tcl.id3v2;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public interface ID3v2X {

    /**
     * TALB
     * 返回专辑名称
     *
     * @return 专辑名称
     */
    String getAlbumName();

    /**
     * TPE1
     * 获得歌手名字
     *
     * @return 歌手名字
     */
    String getSingerName();

    /**
     * TALB
     * 获得歌曲名称
     *
     * @return 歌曲名称
     */
    String getSongName();

    /**
     * TEXT
     * 获取歌词
     *
     * @return 歌词
     */
    String getLyricistWriter();

    /**
     * TCOM
     * 获得作曲家
     *
     * @return 作曲家
     */
    String getComposer();

    /**
     * TCOP
     * 获取版权信息
     *
     * @return 版权信息
     */
    String getCopyright();

    /**
     * APIC  图片附件  [#sec4.15 Attached picture]
     * 获取图片
     *
     * @return 图片字节流
     */
    default byte[] getImageByte(){
        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream("src/main/resources/image/girl.png"))){
            return bis.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * USLT
     * 获得非同步歌词！！！
     *
     * @return 同步歌词
     */
    default String getUnSynchronisedLyrics(){
        return "未知非同步歌词";
    }
}
