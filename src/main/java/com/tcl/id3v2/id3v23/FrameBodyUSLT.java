package com.tcl.id3v2.id3v23;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 非同步歌词！！！
 * @author shuhao1.zhang
 * @version 1.0
 * @className FrameBodyUSLT
 * @description TODO
 * @date 2023/3/4 15:51
 */
public class FrameBodyUSLT {
    private static final Logger log = LoggerFactory.getLogger(FrameBodyUSLT.class);
    private byte textCodingByte;
    private Charset textCoding;
    private byte[] languageByte = new byte[3];

    private String language;
    private byte[] contentDescriptorByte;
    private String contentDescriptor;
    private byte[] lyricsByte;
    private String lyrics;

    public FrameBodyUSLT(byte[] frameContent) {
        this.textCodingByte = frameContent[0];

        if (textCodingByte == 0) textCoding = StandardCharsets.ISO_8859_1;
        else textCoding = StandardCharsets.UTF_16;
        log.info("非同步歌词的编码为：" + textCoding);

        System.arraycopy(frameContent, 1, languageByte, 0, 3);
        language = new String(languageByte, StandardCharsets.ISO_8859_1);
        log.info("非同步歌词的语言为：" + language);

        // 内容描述符
        int i = 4;
        while (frameContent[i] == 0) i ++ ;
        i -- ;
        // 0 1 2 3 4 5 6
        contentDescriptorByte = new byte[i - 4];
        System.arraycopy(frameContent, 4, contentDescriptorByte, 0, i -4);
        contentDescriptor = new String(contentDescriptorByte, textCoding);
        log.info("非同步歌词的内容描述为为：" + contentDescriptor);

        // 歌词
        i ++ ;
        lyricsByte = new byte[frameContent.length - i];
        System.arraycopy(frameContent, i, lyricsByte, 0, frameContent.length - i);
        lyrics = new String(lyricsByte, textCoding);
        log.info("非同步歌词的内容为: " + lyrics);
    }

    public Charset getTextCoding() {
        return textCoding;
    }

    public String getLanguage() {
        return language;
    }

    public String getContentDescriptor() {
        return contentDescriptor;
    }

    public String getLyrics() {
        return lyrics;
    }
}
