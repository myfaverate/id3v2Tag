package com.tcl.id3v2;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

/**
 * 说书人
 * @author shuhao1.zhang
 */
public class ParseID3v2_1 {

    public final Logger log = Logger.getLogger("ParseID3v2");

    // tag  ID3
    public final byte[] id3 = new byte[3];
    // 主版本
    public final byte[] version = new byte[1];
    // 副版本
    public final byte[] subVersion = new byte[1];
    // flags
    public final byte[] flags = new byte[1];


    public final byte[] size_byte = new byte[4];
    // 标签总大小-10byte
    public final int tagSize;

    public final byte[] frames;

    // TIT2
    public final byte[] TIT2_frameID = new byte[4];
    public final byte[] TIT2_frameSize_byte = new byte[4];
    public final byte[] TIT2_frameFlag = new byte[2];
    public final int TIT2_frameSize;
    public final byte[] TIT2_frameContent;

    // TALB
    public final byte[] TALB_frameID = new byte[4];
    public final byte[] TALB_frameSize_byte = new byte[4];
    public final byte[] TALB_frameFlag = new byte[2];
    public final int TALB_frameSize;
    public final byte[] TALB_frameContent;


    // TPA1
    public final byte[] TPA1_frameID = new byte[4];
    public final byte[] TPA1_frameSize_byte = new byte[4];
    public final byte[] TPA1_frameFlag = new byte[2];
    public final int TPA1_frameSize;
    public final byte[] TPA1_frameContent;

    // UFID
    public final byte[] UFID_frameID = new byte[4];
    public final byte[] UFID_frameSize_byte = new byte[4];
    public final byte[] UFID_frameFlag = new byte[2];
    public final int UFID_frameSize;
    public final byte[] UFID_frameContent;
    public ParseID3v2_1(File file) {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))){
            int read = bis.read(id3);
            int read1 = bis.read(version);
            int read2 = bis.read(subVersion);
            int read3 = bis.read(flags);
            int read4 = bis.read(size_byte);

            this.tagSize = (size_byte[0] & 0x7F) << 21 | (size_byte[1] & 0x7F) << 14 | (size_byte[2] & 0x7F) << 7 |
                    size_byte[3] & 0x7F;
            log.info("标签大小-10byte为：" + tagSize);
            frames = new byte[tagSize];
            int read10 = bis.read(frames);

            // 这个非常牛
            ByteArrayInputStream bai = new ByteArrayInputStream(frames);
            int read11 = bai.read(TIT2_frameID);
            int read12 = bai.read(TIT2_frameSize_byte);
            int read14 = bai.read(TIT2_frameFlag);

            this.TIT2_frameSize = TIT2_frameSize_byte[0] << 24 | TIT2_frameSize_byte[1] << 16 | TIT2_frameSize_byte[2] << 8
                    | TIT2_frameSize_byte[3];
            log.info("帧标签：" + new String(TIT2_frameID, StandardCharsets.ISO_8859_1));
            log.info("TIT2帧大小-10byte为：" + TIT2_frameSize);

            TIT2_frameContent = new byte[TIT2_frameSize];
            int read5 = bai.read(TIT2_frameContent);

            log.info(new String(TIT2_frameContent, StandardCharsets.UTF_8));

            // TALB
            int read6 = bai.read(TALB_frameID);
            int read7 = bai.read(TALB_frameSize_byte);
            int read8 = bai.read(TALB_frameFlag);
            this.TALB_frameSize = TALB_frameSize_byte[0] << 24 | TALB_frameSize_byte[1] << 16 | TALB_frameSize_byte[2] << 8
                    | TALB_frameSize_byte[3];
            log.info("帧标签：" + new String(TALB_frameID, StandardCharsets.ISO_8859_1));
            log.info("TALB_frameSize帧大小-10byte为：" + TALB_frameSize);
            TALB_frameContent = new byte[TALB_frameSize];
            int read9 = bai.read(TALB_frameContent);
            log.info(new String(TALB_frameContent, StandardCharsets.UTF_8));

            // TPA1
            int read13 = bai.read(TPA1_frameID);
            int read15 = bai.read(TPA1_frameSize_byte);
            int read16 = bai.read(TPA1_frameFlag);
            log.info(new String(TPA1_frameID, StandardCharsets.ISO_8859_1));
            this.TPA1_frameSize = TPA1_frameSize_byte[0] << 24 | TPA1_frameSize_byte[1] << 16 | TPA1_frameSize_byte[2] << 8
                    | TPA1_frameSize_byte[3];
            TPA1_frameContent = new byte[TPA1_frameSize];
            log.info("TPA1_frameSize: " + TPA1_frameSize); // 7
            int read17 = bai.read(TPA1_frameContent);
            log.info(new String(TPA1_frameContent, StandardCharsets.UTF_8));

            // UFID
            int read18 = bai.read(UFID_frameID);
            int read19 = bai.read(UFID_frameSize_byte);
            int read20 = bai.read(UFID_frameFlag);
            log.info("UFID_frameID: " + new String(UFID_frameID, StandardCharsets.ISO_8859_1));
            this.UFID_frameSize = UFID_frameSize_byte[0] << 24 | UFID_frameSize_byte[1] << 16 | UFID_frameSize_byte[2] << 8
                    | UFID_frameSize_byte[3];
            log.info("UFID_frameSize: " + UFID_frameSize);
            UFID_frameContent = new byte[UFID_frameSize];
            int read21 = bai.read(UFID_frameContent);
            log.info(new String(UFID_frameContent, StandardCharsets.ISO_8859_1));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
