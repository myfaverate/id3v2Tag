package com.tcl.id3v2;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * 乌梅子酱
 * @author shuhao1.zhang
 */
public class ParseID3v2_2 {

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

    // TPE1
    public final byte[] TPE1_frameID = new byte[4];
    public final byte[] TPE1_frameSize_byte = new byte[4];
    public final byte[] TPE1_frameFlag = new byte[2];
    public final int TPE1_frameSize;
    public final byte[] TPE1_frameContent;


    // TALB
    public final byte[] TALB_frameID = new byte[4];
    public final byte[] TALB_frameSize_byte = new byte[4];
    public final byte[] TALB_frameFlag = new byte[2];
    public final int TALB_frameSize;
    public final byte[] TALB_frameContent;

    // TYER
    public final byte[] TYER_frameID = new byte[4];
    public final byte[] TYER_frameSize_byte = new byte[4];
    public final byte[] TYER_frameFlag = new byte[2];
    public final int TYER_frameSize;
    public final byte[] TYER_frameContent;

    // TCON
    public final byte[] TCON_frameID = new byte[4];
    public final byte[] TCON_frameSize_byte = new byte[4];
    public final byte[] TCON_frameFlag = new byte[2];
    public final int TCON_frameSize;
    public final byte[] TCON_frameContent;

    // TRCK
    public final byte[] TRCK_frameID = new byte[4];
    public final byte[] TRCK_frameSize_byte = new byte[4];
    public final byte[] TRCK_frameFlag = new byte[2];
    public final int TRCK_frameSize;
    public final byte[] TRCK_frameContent;

    // COMM
    public final byte[] COMM_frameID = new byte[4];
    public final byte[] COMM_frameSize_byte = new byte[4];
    public final byte[] COMM_frameFlag = new byte[2];
    public final int COMM_frameSize;
    public final byte[] COMM_frameContent;
    public ParseID3v2_2(File file) {
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

            // 这个非常牛 TIT2
            ByteArrayInputStream bai = new ByteArrayInputStream(frames);
            int read11 = bai.read(TIT2_frameID);
            int read12 = bai.read(TIT2_frameSize_byte);
            int read14 = bai.read(TIT2_frameFlag);

            this.TIT2_frameSize = TIT2_frameSize_byte[0] << 24 | TIT2_frameSize_byte[1] << 16 | TIT2_frameSize_byte[2] << 8
                    | TIT2_frameSize_byte[3];
            log.info("帧标签：" + new String(TIT2_frameID, StandardCharsets.ISO_8859_1));
            log.info("TIT2帧大小-10byte为：" + TIT2_frameSize);

            bai.read(new byte[1]);
            TIT2_frameContent = new byte[TIT2_frameSize - 1];
            int read5 = bai.read(TIT2_frameContent);
            log.info("TIT2: " + new String(TIT2_frameContent, "Unicode"));

            // TPE1
            int read13 = bai.read(TPE1_frameID);
            int read15 = bai.read(TPE1_frameSize_byte);
            int read16 = bai.read(TPE1_frameFlag);
            log.info(new String(TPE1_frameID, StandardCharsets.ISO_8859_1));
            this.TPE1_frameSize = TPE1_frameSize_byte[0] << 24 | TPE1_frameSize_byte[1] << 16 | TPE1_frameSize_byte[2] << 8
                    | TPE1_frameSize_byte[3];
            bai.read(new byte[1]);
            TPE1_frameContent = new byte[TPE1_frameSize - 1];
            log.info("TPE1_frameSize: " + TPE1_frameSize); // 7
            int read17 = bai.read(TPE1_frameContent);
            log.info(Arrays.toString(TPE1_frameContent));
            log.info("TPE1: " + new String(TPE1_frameContent, "Unicode"));

            // TALB
            int read6 = bai.read(TALB_frameID);
            int read7 = bai.read(TALB_frameSize_byte);
            int read8 = bai.read(TALB_frameFlag);
            this.TALB_frameSize = TALB_frameSize_byte[0] << 24 | TALB_frameSize_byte[1] << 16 | TALB_frameSize_byte[2] << 8
                    | TALB_frameSize_byte[3];
            log.info("帧标签：" + new String(TALB_frameID, StandardCharsets.ISO_8859_1));
            log.info("TALB_frameSize帧大小-10byte为：" + TALB_frameSize);
            bai.read(new byte[1]);
            TALB_frameContent = new byte[TALB_frameSize - 1];
            int read9 = bai.read(TALB_frameContent);
            log.info(Arrays.toString(TALB_frameContent));
            log.info("===========");
            log.info("TALB: " + new String(TALB_frameContent, "Unicode"));

            // TYER
            int read18 = bai.read(TYER_frameID);
            int read19 = bai.read(TYER_frameSize_byte);
            int read20 = bai.read(TYER_frameFlag);
            log.info("TYER_frameID: " + new String(TYER_frameID, StandardCharsets.ISO_8859_1));
            this.TYER_frameSize = TYER_frameSize_byte[0] << 24 | TYER_frameSize_byte[1] << 16 | TYER_frameSize_byte[2] << 8
                    | TYER_frameSize_byte[3];
            log.info("UFID_frameSize: " + TYER_frameSize);
            TYER_frameContent = new byte[TYER_frameSize];
            int read21 = bai.read(TYER_frameContent);
            log.info(new String(TYER_frameContent, StandardCharsets.UTF_8));

            // TCON
            bai.read(TCON_frameID);
            bai.read(TCON_frameSize_byte);
            bai.read(TCON_frameFlag);
            log.info("TCON_frameID: " + new String(TCON_frameID, StandardCharsets.ISO_8859_1));
            this.TCON_frameSize = TCON_frameSize_byte[0] << 24 | TCON_frameSize_byte[1] << 16 | TCON_frameSize_byte[2] << 8
                    | TCON_frameSize_byte[3];
            log.info("UFID_frameSize: " + TCON_frameSize);
            TCON_frameContent = new byte[TCON_frameSize];
            bai.read(TCON_frameContent);
            log.info(new String(TCON_frameContent, StandardCharsets.ISO_8859_1));

            // TRCK
            bai.read(TRCK_frameID);
            bai.read(TRCK_frameSize_byte);
            bai.read(TRCK_frameFlag);
            log.info("TCON_frameID: " + new String(TRCK_frameID, StandardCharsets.ISO_8859_1));
            this.TRCK_frameSize = TRCK_frameSize_byte[0] << 24 | TRCK_frameSize_byte[1] << 16 | TRCK_frameSize_byte[2] << 8
                    | TRCK_frameSize_byte[3];
            log.info("UFID_frameSize: " + TRCK_frameSize);
            TRCK_frameContent = new byte[TRCK_frameSize];
            bai.read(TRCK_frameContent);
            log.info(new String(TRCK_frameContent, StandardCharsets.ISO_8859_1));

            // COMM
            bai.read(COMM_frameID);
            bai.read(COMM_frameSize_byte);
            bai.read(COMM_frameFlag);
            log.info("TCON_frameID: " + new String(COMM_frameID, StandardCharsets.ISO_8859_1));
            this.COMM_frameSize = COMM_frameSize_byte[0] << 24 | COMM_frameSize_byte[1] << 16 | COMM_frameSize_byte[2] << 8
                    | COMM_frameSize_byte[3];
            log.info("UFID_frameSize: " + COMM_frameSize);
            COMM_frameContent = new byte[COMM_frameSize];
            bai.read(COMM_frameContent);
            log.info(new String(COMM_frameContent, StandardCharsets.ISO_8859_1));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
