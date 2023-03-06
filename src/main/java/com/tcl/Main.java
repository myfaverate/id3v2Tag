package com.tcl;

import com.tcl.id3v2.ID3v2Tag;
import com.tcl.id3v2.ID3v2X;

import java.io.File;
import java.io.UnsupportedEncodingException;

/**
 * 解析 张pd - 二零三（翻自 毛不易）.mp3、张云雷 - 探清水河.mp3
 * ........
 */
public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        ID3v2Tag id3v2Tag = new ID3v2Tag(new File("src/test/resources/id3v24/没有理想的人不伤心2.mp3"));
        ID3v2X id3v2X = id3v2Tag.getID3v2X();
        System.out.println("歌手名字：" + id3v2X.getSingerName());
        System.out.println("专辑名称："+ id3v2X.getAlbumName());
        System.out.println("歌曲名字：" + id3v2X.getSongName());
        System.out.println("歌词作者：" + id3v2X.getLyricistWriter());
        System.out.println("作曲家：" + id3v2X.getComposer());
        System.out.println("版权：" + id3v2X.getCopyright());
        // 非同步歌词！！！歌词
        System.out.println("============= 非同步歌词！！！=======================");
        System.out.println(id3v2X.getUnSynchronisedLyrics());
        // 同步歌词的歌曲太少了！！！
    }
}
// java -jar .\java_music_player.jar D:\Language\Java\java_music_player\src\test\resources\id3v24\没有理想的人不伤心2.mp3
/*
以上步骤可以分阶段进行，具体的进度安排可以根据项目的复杂度和实际情况进行调整。可以按照以下方式进行:
2023.2.20-2023.2.28:明确课题研究内容，查找相关文献,了解课题研究现状,提交任务书;
2023.3.1-2023.3.7:可行性分析，系统设计，提交开题报告;2002.3.7-2023.4.15:推荐算法学习、设计与调试，数据采集及预处理;
2023.4.16-2023.5.15:进行平台设计，将整个系统进行实现;
2023.5.15-2023.6.1∶测试系统并不断优化，编写论文。
 */