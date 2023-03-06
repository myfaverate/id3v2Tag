package com.tcl.id3v2.id3v23;

import com.tcl.id3v2.ID3v2X;
import com.tcl.id3v2.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 解析MP3 ID3v23
 * <p>
 * 4.20    AENC  音频加密  [[#sec4.20|Audio encryption]] <br/>
 * 4.15    APIC  图片附件  [#sec4.15 Attached picture] <br/>
 * 4.11    COMM  评论  [#sec4.11 Comments] <br/>
 * 4.25    COMR  商业帧  [#sec4.25 Commercial frame] <br/>
 * 4.26    ENCR  加密方法注册  [#sec4.26 Encryption method registration] <br/>
 * 4.13    EQUA  均衡  [#sec4.13 Equalization] <br/>
 * 4.6     ETCO  事件时间码  [#sec4.6 Event timing codes] <br/>
 * 4.16    GEOB  通用封装对象  [#sec4.16 General encapsulated object] <br/>
 * 4.27    GRID  Group身份登记  [#sec4.27 Group identification registration] <br/>
 * 4.4     IPLS  涉及人员列表  [#sec4.4 Involved people list] <br/>
 * 4.21    LINK  链接  [#sec4.21 Linked information] <br/>
 * 4.5     MCDI  Music CD 标识符  [#sec4.5 Music CD identifier] <br/>
 * 4.7     MLLT  MPEG位置查找表  [#sec4.7 MPEG location lookup table] <br/>
 * 4.24    OWNE  所有权  [#sec4.24 Ownership frame] <br/>
 * 4.28    PRIV  私有帧  [#sec4.28 Private frame] <br/>
 * 4.17    PCNT  播放计数器  [#sec4.17 Play counter] <br/>
 * 4.18    POPM  评级  [#sec4.18 Popularimeter] <br/>
 * 4.22    POSS  位置同步帧  [#sec4.22 Position synchronisation frame] <br/>
 * 4.19    RBUF  推荐缓冲区大小  [#sec4.19 Recommended buffer size] <br/>
 * 4.12    RVAD  相对音量调节  [#sec4.12 Relative volume adjustment] <br/>
 * 4.14    RVRB  混响  [#sec4.14 Reverb] <br/>
 * 4.10    SYLT  同步歌词/文本  [#sec4.10 Synchronized lyric/text] <br/>
 * 4.8     SYTC  同步节奏代码  [#sec4.8 Synchronized tempo codes] <br/>
 * 4.2.1   TALB  专辑、电影、显示标题  [#TALB Album/Movie/Show title] <br/>
 * 4.2.1   TBPM  每分钟打击次数  [#TBPM BPM (beats per minute)] <br/>
 * 4.2.1   TCOM  作曲家  [#TCOM Composer] <br/>
 * 4.2.1   TCON  内容类型  [#TCON Content type] <br/>
 * 4.2.1   TCOP  版权信息  [#TCOP Copyright message] <br/>
 * 4.2.1   TDAT  日期  [#TDAT Date] <br/>
 * 4.2.1   TDLY  发布信息前的音乐播放时间  [#TDLY Playlist delay] <br/>
 * 4.2.1   TENC  译成电码  [#TENC Encoded by] <br/>
 * 4.2.1   TEXT  歌词作者  [#TEXT Lyricist/Text writer] <br/>
 * 4.2.1   TFLT  文件类型  [#TFLT File type] <br/>
 * 4.2.1   TIME  时间  [#TIME Time] <br/>
 * 4.2.1   TIT1  内容组描述  [#TIT1 Content group description] <br/>
 * 4.2.1   TIT2  标题/歌曲名称/内容描述  [#TIT2 Title/song name/content description] <br/>
 * 4.2.1   TIT3  副标题/描述细化  [#TIT3 Subtitle/Description refinement] <br/>
 * 4.2.1   TKEY  初始密钥  [#TKEY Initial key] <br/>
 * 4.2.1   TLAN  语言  [#TLAN Language(s)] <br/>
 * 4.2.1   TLEN  长度  [#TLEN Length] <br/>
 * 4.2.1   TMED  媒体类型  [#TMED Media type] <br/>
 * 4.2.1   TOAL  初始的专辑、电影、显示标题  [#TOAL Original album/movie/show title] <br/>
 * 4.2.1   TOFN  初始的文件名  [#TOFN Original filename] <br/>
 * 4.2.1   TOLY  初始的歌词作者  [#TOLY Original lyricist(s)/text writer(s)] <br/>
 * 4.2.1   TOPE  初始的的艺术家、表演家  [#TOPE Original artist(s)/performer(s)] <br/>
 * 4.2.1   TORY  初始的发行年份  [#TORY Original release year] <br/>
 * 4.2.1   TOWN  文件拥有者  [#TOWN File owner/licensee] <br/>
 * 4.2.1   TPE1  主要演员(s) /独奏者(s)  [#TPE1 Lead performer(s)/Soloist(s)] <br/>
 * 4.2.1   TPE2    [#TPE2 Band/orchestra/accompaniment] <br/>
 * 4.2.1   TPE3    [#TPE3 Conductor/performer refinement] <br/>
 * 4.2.1   TPE4    [#TPE4 Interpreted, remixed, or otherwise modified by] <br/>
 * 4.2.1   TPOS    [#TPOS Part of a set] <br/>
 * 4.2.1   TPUB    [#TPUB Publisher] <br/>
 * 4.2.1   TRCK    [#TRCK Track number/Position in set] <br/>
 * 4.2.1   TRDA    [#TRDA Recording dates] <br/>
 * 4.2.1   TRSN    [#TRSN Internet radio station name] <br/>
 * 4.2.1   TRSO    [#TRSO Internet radio station owner] <br/>
 * 4.2.1   TSIZ    [#TSIZ Size] <br/>
 * 4.2.1   TSRC    [#TSRC ISRC (international standard recording code)] <br/>
 * 4.2.1   TSSE  用于编码的软件/硬件和设置  [#TSEE Software/Hardware and settings used for encoding] <br/>
 * 4.2.1   TYER  年  [#TYER Year] <br/>
 * 4.2.2   TXXX    [#TXXX User defined text information frame] <br/>
 * 4.1     UFID    [#sec4.1 Unique file identifier] <br/>
 * 4.23    USER    [#sec4.23 Terms of use] <br/>
 * 4.9     USLT  非同步歌词抄写  [#sec4.9 Unsychronized lyric/text transcription] <br/>
 * 4.3.1   WCOM    [#WCOM Commercial information] <br/>
 * 4.3.1   WCOP    [#WCOP Copyright/Legal information]  <br/>
 * 4.3.1   WOAF    [#WOAF Official audio file webpage] <br/>
 * 4.3.1   WOAR    [#WOAR Official artist/performer webpage] <br/>
 * 4.3.1   WOAS    [#WOAS Official audio source webpage] <br/>
 * 4.3.1   WORS    [#WORS Official internet radio station homepage] <br/>
 * 4.3.1   WPAY    [#WPAY Payment] <br/>
 * 4.3.1   WPUB    [#WPUB Publishers official webpage] <br/>
 * 4.3.2   WXXX  用户自定义URL链接帧  [#WXXX User defined URL link frame] <br/>
 * <p/>
 *
 * @author shuhao1.zhang
 * @version 1.0
 * @className ID3v23
 * @description 解析 ID3v23 frames
 * @date 2023/3/2 16:13
 */
public class ID3v23 implements ID3v2X {
    private static final Logger log = LoggerFactory.getLogger(ID3v23.class);
    private static final int FRAME_COUNT = 74;

    // 74个FrameID
    public static final String AENC = "AENC";
    public static final String APIC = "APIC";
    public static final String COMM = "COMM";
    public static final String COMR = "COMR";
    public static final String ENCR = "ENCR";
    public static final String EQUA = "EQUA";
    public static final String ETCO = "ETCO";
    public static final String GEOB = "GEOB";
    public static final String GRID = "GRID";
    public static final String IPLS = "IPLS";
    public static final String LINK = "LINK";
    public static final String MCDI = "MCDI";
    public static final String MLLT = "MLLT";
    public static final String OWNE = "OWNE";
    public static final String PRIV = "PRIV";
    public static final String PCNT = "PCNT";
    public static final String POPM = "POPM";
    public static final String POSS = "POSS";
    public static final String RBUF = "RBUF";
    public static final String RVAD = "RVAD";
    public static final String RVRB = "RVRB";
    public static final String SYLT = "SYLT";
    public static final String SYTC = "SYTC";
    public static final String TALB = "TALB";
    public static final String TBPM = "TBPM";
    public static final String TCOM = "TCOM";
    public static final String TCON = "TCON";
    public static final String TCOP = "TCOP";
    public static final String TDAT = "TDAT";
    public static final String TDLY = "TDLY";
    public static final String TENC = "TENC";
    public static final String TEXT = "TEXT";
    public static final String TFLT = "TFLT";
    public static final String TIME = "TIME";
    public static final String TIT1 = "TIT1";
    public static final String TIT2 = "TIT2";
    public static final String TIT3 = "TIT3";
    public static final String TKEY = "TKEY";
    public static final String TLAN = "TLAN";
    public static final String TLEN = "TLEN";
    public static final String TMED = "TMED";
    public static final String TOAL = "TOAL";
    public static final String TOFN = "TOFN";
    public static final String TOLY = "TOLY";
    public static final String TOPE = "TOPE";
    public static final String TORY = "TORY";
    public static final String TOWN = "TOWN";
    public static final String TPE1 = "TPE1";
    public static final String TPE2 = "TPE2";
    public static final String TPE3 = "TPE3";
    public static final String TPE4 = "TPE4";
    public static final String TPOS = "TPOS";
    public static final String TPUB = "TPUB";
    public static final String TRCK = "TRCK";
    public static final String TRDA = "TRDA";
    public static final String TRSN = "TRSN";
    public static final String TRSO = "TRSO";
    public static final String TSIZ = "TSIZ";
    public static final String TSRC = "TSRC";
    public static final String TSSE = "TSSE";
    public static final String TYER = "TYER";
    public static final String TXXX = "TXXX";
    public static final String UFID = "UFID";
    public static final String USER = "USER";
    public static final String USLT = "USLT";
    public static final String WCOM = "WCOM";
    public static final String WCOP = "WCOP";
    public static final String WOAF = "WOAF";
    public static final String WOAR = "WOAR";
    public static final String WOAS = "WOAS";
    public static final String WORS = "WORS";
    public static final String WPAY = "WPAY";
    public static final String WPUB = "WPUB";
    public static final String WXXX = "WXXX";
    // =======================================

    private boolean isUnSynchronisation; // 是否非同步
    private boolean isExtendedHeader; // 是否扩展头部
    private boolean isExperimentalIndicator; // 是否实验指示器

    /*
        扩展头部
        在id3v2.3.0中并未详细说明，基本上都不会有扩展头部
    */
    private byte[] extendedHeaderSizeByte = new byte[4];
    private byte[] extendedFlags = new byte[2];
    private byte[] sizeOfPadding = new byte[4];
    private int extendedHeaderSize;
    private byte[] extendedHeaderData;

    private final Map<String, FrameHeader> map = new HashMap<>();

    // 74个帧

    public ID3v23(byte[] frames, byte[] tagFlags) {

        isUnSynchronisation = (tagFlags[0] >> 7 & 1) == 1;
        log.info("是否非同步: " + isUnSynchronisation);
        isExtendedHeader = (tagFlags[0] >> 6 & 1) == 1;
        log.info("是否拥有扩展头部：" + isExtendedHeader);
        isExperimentalIndicator = (tagFlags[0] >> 5 & 1) == 1;
        log.info("是否是实验指示器: " + isExperimentalIndicator);

        ByteArrayInputStream bai = new ByteArrayInputStream(frames);

        // 处理扩展头部
        if (isExtendedHeader) {
            try {
                int len1 = bai.read(extendedHeaderSizeByte);
                log.info("extendedHeaderSizeByte, 读取长度：" + len1);
                int len2 = bai.read(extendedFlags);
                log.info("extendedFlags, 读取长度：" + len2);
                int len3 = bai.read(sizeOfPadding);
                log.info("sizeOfPadding, 读取长度：" + len3);
                extendedHeaderSize = Util.getFrameByteSize(extendedHeaderSizeByte);
                log.info("extendedHeaderSize 扩展头大小为：" + extendedHeaderSize);
                extendedHeaderData = new byte[extendedHeaderSize];
                int len4 = bai.read(extendedHeaderData);
                log.info("extendedHeaderData, 读取长度：" + len4);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // 处理74个帧
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
                    这里可以大做文章，因为每个FrameID可以出现多次！！！
                 */
                if (map.containsKey(frameID)) {
                    // 定义一个记录某个标签出现的次数的变量
                    int count = 1;
                    while (map.containsKey(frameID + count)){
                        count ++ ;
                    }
                    map.put(frameID + count, frameArray.get(i));
                } else {
                    map.put(frameID, frameArray.get(i));
                }
            }
            log.info("====================== 处理" + FRAME_COUNT + "个帧 ===============================");
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
     * USLT
     * 获得非同步歌词！！！
     *
     * @return 同步歌词
     */
    @Override
    public String getUnSynchronisedLyrics(){
        FrameHeader frameHeader = map.get(USLT);
        if (frameHeader == null) return "未知歌词！！！";
        FrameBodyUSLT frameBodyUSLT = new FrameBodyUSLT(frameHeader.frameContent);
        return frameBodyUSLT.getLyrics();
    }

    /**
     * 无法加载
     * APIC  图片附件  [#sec4.15 Attached picture]
     * 获取图片二进制数据
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
     * TIT2  标题/歌曲名称/内容描述  [#TIT2 Title/song name/content description]
     * 获取歌曲名称
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
     * TALB
     * 返回专辑名称
     *
     * @return 专辑名称
     */

    @Override
    public String getAlbumName() {
        FrameHeader frameHeader = map.get(TALB);

        if (frameHeader == null) return "未知专辑";

        return getTextInfo(frameHeader);
    }

    @Override
    public String getLyricistWriter() {
        FrameHeader frameHeader = map.get(TEXT);

        if (frameHeader == null) return "未知歌词作者";

        return getTextInfo(frameHeader);
    }

    @Override
    public String getComposer() {
        FrameHeader frameHeader = map.get(TCOM);

        if (frameHeader == null) return "未知作曲家";

        return getTextInfo(frameHeader);
    }

    @Override
    public String getCopyright() {
        FrameHeader frameHeader = map.get(TCOP);

        if (frameHeader == null) return "未知版权";

        return getTextInfo(frameHeader);
    }


    /**
     * <p>
     * TPE1 <br/>
     * The 'Lead artist(s)/Lead performer(s)/Soloist(s)/Performing group' is <br/>
     * used for the main artist(s). They are seperated with the "/" <br/>
     * character. <br/>
     * </p>
     *
     * @return 返回歌手名字
     */
    @Override
    public String getSingerName() {
        FrameHeader frameHeader = map.get(TPE1);

        if (frameHeader == null) return "未知歌手";

        return getTextInfo(frameHeader);
    }

    /**
     * <p>
     * 获得文本帧信息 <br/>
     * < Header for 'Text information frame', ID: "T000" - "TZZZ", excluding "TXXX" described in 4.2.2.> <br/>
     * Text encoding    $xx <br/>
     * Information    < text string according to encoding> <br/>
     * </p>
     *
     * @param frameHeader 帧体
     * @return 文本信息
     */
    private String getTextInfo(FrameHeader frameHeader) {
        byte encoding = frameHeader.frameContent[0];
        // 如果是0x00则是 ISO-8859-1 编码
        if (encoding == 0) {
            return new String(frameHeader.frameContent, 1, frameHeader.frameContent.length - 1, StandardCharsets.ISO_8859_1);
        } else {
            return new String(frameHeader.frameContent, 1, frameHeader.frameContent.length - 1, StandardCharsets.UTF_16);
        }
    }
}

