package com.tcl.id3v2.id3v24;

import com.tcl.id3v2.ID3v2X;
import com.tcl.id3v2.id3v23.FrameBodyAPIC;
import com.tcl.id3v2.id3v23.FrameBodyUSLT;
import com.tcl.id3v2.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 解析MP3 ID3v24
 * <p>
 * 4.19  AENC Audio encryption <br/>
 * 4.14  APIC Attached picture <br/>
 * 4.30  ASPI Audio seek point index <br/>
 * 4.10  COMM Comments <br/>
 * 4.24  COMR Commercial frame <br/>
 * 4.25  ENCR Encryption method registration <br/>
 * 4.12  EQU2 Equalisation (2) <br/>
 * 4.5   ETCO Event timing codes <br/>
 * 4.15  GEOB General encapsulated object <br/>
 * 4.26  GRID Group identification registration <br/>
 * 4.20  LINK Linked information <br/>
 * 4.4   MCDI Music CD identifier <br/>
 * 4.6   MLLT MPEG location lookup table <br/>
 * 4.23  OWNE Ownership frame <br/>
 * 4.27  PRIV Private frame <br/>
 * 4.16  PCNT Play counter <br/>
 * 4.17  POPM Popularimeter <br/>
 * 4.21  POSS Position synchronisation frame <br/>
 * 4.18  RBUF Recommended buffer size <br/>
 * 4.11  RVA2 Relative volume adjustment (2) <br/>
 * 4.13  RVRB Reverb <br/>
 * 4.29  SEEK Seek frame <br/>
 * 4.28  SIGN Signature frame <br/>
 * 4.9   SYLT Synchronised lyric/text <br/>
 * 4.7   SYTC Synchronised tempo codes <br/>
 * 4.2.1 TALB Album/Movie/Show title <br/>
 * 4.2.3 TBPM BPM (beats per minute) <br/>
 * 4.2.2 TCOM Composer <br/>
 * 4.2.3 TCON Content type <br/>
 * 4.2.4 TCOP Copyright message <br/>
 * 4.2.5 TDEN Encoding time <br/>
 * 4.2.5 TDLY Playlist delay <br/>
 * 4.2.5 TDOR Original release time <br/>
 * 4.2.5 TDRC Recording time <br/>
 * 4.2.5 TDRL Release time <br/>
 * 4.2.5 TDTG Tagging time <br/>
 * 4.2.2 TENC Encoded by <br/>
 * 4.2.2 TEXT Lyricist/Text writer <br/>
 * 4.2.3 TFLT File type <br/>
 * 4.2.2 TIPL Involved people list <br/>
 * 4.2.1 TIT1 Content group description <br/>
 * 4.2.1 TIT2 Title/songname/content description <br/>
 * 4.2.1 TIT3 Subtitle/Description refinement <br/>
 * 4.2.3 TKEY Initial key <br/>
 * 4.2.3 TLAN Language(s) <br/>
 * 4.2.3 TLEN Length <br/>
 * 4.2.2 TMCL Musician credits list <br/>
 * 4.2.3 TMED Media type <br/>
 * 4.2.3 TMOO Mood <br/>
 * 4.2.1 TOAL Original album/movie/show title <br/>
 * 4.2.5 TOFN Original filename <br/>
 * 4.2.2 TOLY Original lyricist(s)/text writer(s) <br/>
 * 4.2.2 TOPE Original artist(s)/performer(s) <br/>
 * 4.2.4 TOWN File owner/licensee <br/>
 * 4.2.2 TPE1 Lead performer(s)/Soloist(s) <br/>
 * 4.2.2 TPE2 Band/orchestra/accompaniment <br/>
 * 4.2.2 TPE3 Conductor/performer refinement <br/>
 * 4.2.2 TPE4 Interpreted, remixed, or otherwise modified by <br/>
 * 4.2.1 TPOS Part of a set <br/>
 * 4.2.4 TPRO Produced notice <br/>
 * 4.2.4 TPUB Publisher <br/>
 * 4.2.1 TRCK Track number/Position in set <br/>
 * 4.2.4 TRSN Internet radio station name <br/>
 * 4.2.4 TRSO Internet radio station owner <br/>
 * 4.2.5 TSOA Album sort order <br/>
 * 4.2.5 TSOP Performer sort order <br/>
 * 4.2.5 TSOT Title sort order <br/>
 * 4.2.1 TSRC ISRC (international standard recording code) <br/>
 * 4.2.5 TSSE Software/Hardware and settings used for encoding <br/>
 * 4.2.1 TSST Set subtitle <br/>
 * 4.2.2 TXXX User defined text information frame <br/>
 * 4.1   UFID Unique file identifier <br/>
 * 4.22  USER Terms of use <br/>
 * 4.8   USLT Unsynchronised lyric/text transcription <br/>
 * 4.3.1 WCOM Commercial information <br/>
 * 4.3.1 WCOP Copyright/Legal information <br/>
 * 4.3.1 WOAF Official audio file webpage <br/>
 * 4.3.1 WOAR Official artist/performer webpage <br/>
 * 4.3.1 WOAS Official audio source webpage <br/>
 * 4.3.1 WORS Official Internet radio station homepage <br/>
 * 4.3.1 WPAY Payment <br/>
 * 4.3.1 WPUB Publishers official webpage <br/>
 * 4.3.2 WXXX User defined URL link frame <br/>
 * </p>
 *
 * @author shuhao1.zhang
 * @version 1.0
 * @className ID3v23
 * @description TODO
 * @date 2023/3/2 16:13
 */
public class ID3v24 implements ID3v2X {
    private static final Logger log = LoggerFactory.getLogger(ID3v24.class);
    private static final int FRAME_COUNT = 83;

    public static final String TALB = "TALB";
    public static final String TPE1 = "TPE1";
    public static final String APIC = "APIC";
    public static final String TIT2 = "TIT2";
    public static final String TEXT = "TEXT";
    public static final String TCOM = "TCOM";
    public static final String TCOP = "TCOP";
    public static final String USLT = "USLT";

    private boolean isUnSynchronisation; // 是否非同步
    private boolean isExtendedHeader; // 是否扩展头部
    private boolean isExperimentalIndicator; // 是否实验指示器
    private boolean isFooterPresent; // 是否有footer

    /*
        扩展头部
        在id3v2.4.0中的扩展头部变化很大
    */
    private byte[] extendedHeaderSizeByte = new byte[4];
    private byte[] numberOfFlagBytes = new byte[1];
    private byte[] extendedFlags = new byte[1];
    private int extendedHeaderSize;
    private byte[] extendedHeaderData;

    private final Map<String, FrameHeader> map = new HashMap<>();

    public ID3v24(byte[] frames, byte[] tagFlags) {
        isUnSynchronisation = (tagFlags[0] >> 7 & 1) == 1;
        log.info("是否非同步: " + isUnSynchronisation);
        isExtendedHeader = (tagFlags[0] >> 6 & 1) == 1;
        log.info("是否拥有扩展头部：" + isExtendedHeader);
        isExperimentalIndicator = (tagFlags[0] >> 5 & 1) == 1;
        log.info("是否是实验指示器: " + isExperimentalIndicator);
        isFooterPresent = (tagFlags[0] >> 4 & 1) == 1;
        log.info("是否存在footer：" + isFooterPresent);

        ByteArrayInputStream bai = new ByteArrayInputStream(frames);

        // 处理扩展头部
        if (isExtendedHeader) {
            try {
                int len1 = bai.read(extendedHeaderSizeByte);
                log.info("extendedHeaderSizeByte, 读取长度：" + len1);
                int len2 = bai.read(numberOfFlagBytes);
                log.info("numberOfFlagBytes, 读取长度：" + len2);
                int len3 = bai.read(extendedFlags);
                log.info("extendedFlags, 读取长度：" + len3);
                extendedHeaderSize = Util.getTagByteSize(extendedHeaderSizeByte);
                log.info("extendedHeaderSize 扩展头大小为：" + extendedHeaderSize);
                extendedHeaderData = new byte[extendedHeaderSize];
                int len4 = bai.read(extendedHeaderData);
                log.info("extendedHeaderData, 读取长度：" + len4);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // 处理83个帧
        List<FrameHeader> frameArray = new ArrayList<>(FRAME_COUNT);
        for (int i = 0; i < FRAME_COUNT; i++) {
            frameArray.add(new FrameHeader());
        }

        try {

            log.info("====================== 处理" + FRAME_COUNT + "个帧 ===============================");

            for (int i = 0; i < FRAME_COUNT; i++) {
                int len1 = bai.read(frameArray.get(i).frameID);
                log.info("frameID, 读取长度：" + len1);
                String frameID = new String(frameArray.get(i).frameID, StandardCharsets.ISO_8859_1);
                log.info("帧标签：" + frameID);

                int len2 = bai.read(frameArray.get(i).frameSizeByte);
                frameArray.get(i).getFrameSizeAndFrameContent();
                log.info("frameSizeByte, 读取长度：" + len2);

                int len3 = bai.read(frameArray.get(i).frameFlag);
                frameArray.get(i).analyseFlag();
                log.info("frameFlag, 读取长度：" + len3);

                int len4 = bai.read(frameArray.get(i).frameContent);
                log.info("frameContent, 读取长度：" + len4);

                /*
                    存入map
                    这里可以大做文章，因为一些FrameID可以出现多次！！！
                 */
                if (map.containsKey(frameID)) {
                    // 定义一个记录某个标签出现的次数的变量
                    int count = 1;
                    while (map.containsKey(frameID + count)) {
                        count++;
                    }
                    map.put(frameID + count, frameArray.get(i));
                } else {
                    map.put(frameID, frameArray.get(i));
                }
            }
            log.info("====================== 处理74个帧 ===============================");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
        TIT2  标题/歌曲名称/内容描述  [#TIT2 Title/song name/content description] √
        TALB  专辑、电影、显示 标题  [#TALB Album/Movie/Show title] √
        TPE1  主要演员(s) /独奏者(s)  [#TPE1 Lead performer(s)/Soloist(s)] √
        TEXT  歌词作者  [#TEXT Lyricist/Text writer] √
        TCOM  作曲家  [#TCOM Composer] √
        TCOP  版权信息  [#TCOP Copyright message] √
        SYLT  同步歌词/文本  [#sec4.10 Synchronized lyric/text]
        USLT  非同步歌词抄写  [#sec4.9 Unsychronized lyric/text transcription]
        APIC  图片附件  [#sec4.15 Attached picture]
    */

    /**
     * TALB
     * 获得专辑名称
     *
     * @return 专辑名称
     */
    @Override
    public String getAlbumName() {
        FrameHeader frameHeader = map.get(TALB);

        if (frameHeader == null) return "未知专辑";

        return getTextInfo(frameHeader);
    }

    /**
     * TPE1
     * 获得歌手名称
     *
     * @return 歌手名称
     */
    @Override
    public String getSingerName() {
        FrameHeader frameHeader = map.get(TPE1);

        if (frameHeader == null) return "未知歌手";

        return getTextInfo(frameHeader);
    }

    /**
     * TIT2
     * 获得歌曲名称
     *
     * @return 歌曲名称
     */
    @Override
    public String getSongName() {
        FrameHeader frameHeader = map.get(TIT2);

        if (frameHeader == null) return "未知歌曲名称";

        return getTextInfo(frameHeader);
    }

    /**
     * TEXT
     * 获得歌词作者
     *
     * @return 歌词作者
     */
    @Override
    public String getLyricistWriter() {
        FrameHeader frameHeader = map.get(TEXT);

        if (frameHeader == null) return "未知歌词作者";

        return getTextInfo(frameHeader);
    }

    /**
     * TCOM
     * 获得作曲家
     *
     * @return 作曲家
     */
    @Override
    public String getComposer() {
        FrameHeader frameHeader = map.get(TCOM);

        if (frameHeader == null) return "未知作曲家";

        return getTextInfo(frameHeader);
    }

    /**
     * TCOP
     * 获得版权信息！！！
     *
     * @return 版权信息
     */
    @Override
    public String getCopyright() {
        FrameHeader frameHeader = map.get(TCOP);

        if (frameHeader == null) return "未知版权";

        return getTextInfo(frameHeader);
    }

    /**
     * APIC
     * 获得图片！！！
     *
     * @return 图片二进制数据
     */
    @Override
    public byte[] getImageByte() {
        FrameHeader header = map.get(APIC);
        if (header == null) {
            try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream("src/main/resources/image/girl.png"))){
                return bis.readAllBytes();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        FrameBodyAPIC frameBodyAPIC = new FrameBodyAPIC(header.frameContent);
        return frameBodyAPIC.getImageByte();
    }

    /**
     * USLT
     * 获得非同步歌词
     *
     * @return 非同步歌词
     */
    @Override
    public String getUnSynchronisedLyrics() {
        FrameHeader frameHeader = map.get(USLT);
        if (frameHeader == null) return "未知歌词！！！";
        com.tcl.id3v2.id3v23.FrameBodyUSLT frameBodyUSLT = new FrameBodyUSLT(frameHeader.frameContent);
        return frameBodyUSLT.getLyrics();
    }


    /**
     * <p>
     * 获得文本帧信息 <br/>
     * < Header for 'Text information frame', ID: "T000" - "TZZZ", excluding "TXXX" described in 4.2.2.> <br/>
     * Text encoding    $xx <br/>
     * Information    < text string according to encoding> <br/>
     * </p>
     * 存储为`null`分隔的列表
     *
     * @param frameHeader 帧体
     * @return 文本信息
     */
    private String getTextInfo(FrameHeader frameHeader) {
        byte encoding = frameHeader.frameContent[0];
        // 如果是0x00则是 ISO-8859-1 编码
        switch (encoding) {
            case 0:
                return new String(frameHeader.frameContent, 1, frameHeader.frameContent.length - 1, StandardCharsets.ISO_8859_1);
            case 1:
                return new String(frameHeader.frameContent, 1, frameHeader.frameContent.length - 1, StandardCharsets.UTF_16);
            case 2:
                return new String(frameHeader.frameContent, 1, frameHeader.frameContent.length - 1, StandardCharsets.UTF_16BE);
            case 3:
                return new String(frameHeader.frameContent, 1, frameHeader.frameContent.length - 1, StandardCharsets.UTF_8);
            default:
                return new String(frameHeader.frameContent, 1, frameHeader.frameContent.length - 1, StandardCharsets.US_ASCII);
        }
    }
}
