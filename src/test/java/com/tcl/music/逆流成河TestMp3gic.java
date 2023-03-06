package com.tcl.music;

import com.mpatric.mp3agic.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Itunes
 *
 * @author shuhao1.zhang
 * @version 1.0
 * @className Test
 * @description 测试！！！
 * @date 2023/2/28 19:43
 */
public class 逆流成河TestMp3gic {

    private final static Logger logger = LoggerFactory.getLogger(逆流成河TestMp3gic.class);

    public static void main(String[] args) throws InvalidDataException, UnsupportedTagException, IOException, NotSupportedException {
        File file = new File("src/main/resources/逆流成河.mp3");
        Mp3File mp3File = new Mp3File(file);

        if (mp3File.hasId3v1Tag()) mp3File.removeId3v1Tag();
        if (mp3File.hasId3v2Tag()) mp3File.removeId3v2Tag();

        ID3v2 id3v2;
        if (mp3File.hasId3v2Tag()){
            id3v2 = mp3File.getId3v2Tag();
        }else {
            id3v2 = new ID3v23Tag();
        }

        id3v2.setArtistUrl("https://www.baidu.com");
        id3v2.setDate("2023.03.04");
        id3v2.setComment("斑驳的夜色在说什么\n" +
                "谁能告诉我如何选择\n" +
                "每当我想起分离时刻\n" +
                "悲伤就逆流成河\n" +
                "你给的温暖属于谁呢\n" +
                "谁又会在乎我是谁呢\n" +
                "每当我想起你的选择\n" +
                "悲伤就逆流成河\n" +
                "失去了你也是种获得\n" +
                "一个人孤单未尝不可\n" +
                "每当我深夜辗转反侧\n" +
                "悲伤就逆流成河\n" +
                "离开你也是一种快乐\n" +
                "没人说一定非爱不可\n" +
                "想问你双手是否温热\n" +
                "悲伤就逆流成河\n" +
                "我想是因为我太天真\n" +
                "难过是因为我太认真\n" +
                "每当我想起你的眼神\n" +
                "悲伤就逆流成河");

        id3v2.setTrack("5");
        id3v2.setArtist("金南玲_张书豪");
        id3v2.setTitle("逆流成河_张书豪");
        id3v2.setAlbum("逆流成河 (信)_张书豪");
        id3v2.setYear("2023");
        id3v2.setGenre(12);
        id3v2.setComment("真好听哇。。。");
        id3v2.setLyrics("[00:01.000] 斑驳的夜色在说什么\n" +
                "[00:01.000] 谁能告诉我如何选择\n" +
                "[00:01.000] 每当我想起分离时刻\n" +
                "[00:01.000] 悲伤就逆流成河\n" +
                "[00:01.000] 你给的温暖属于谁呢\n" +
                "[00:01.000] 谁又会在乎我是谁呢\n" +
                "[00:01.000] 每当我想起你的选择\n" +
                "[00:01.000] 悲伤就逆流成河\n" +
                "[00:01.000] 失去了你也是种获得\n" +
                "[00:01.000] 一个人孤单未尝不可\n" +
                "[00:01.000] 每当我深夜辗转反侧\n" +
                "[00:01.000] 悲伤就逆流成河\n" +
                "[00:01.000] 离开你也是一种快乐\n" +
                "[00:01.000] 没人说一定非爱不可\n" +
                "[00:01.000] 想问你双手是否温热\n" +
                "[00:01.000] 悲伤就逆流成河\n" +
                "[00:01.000] 我想是因为我太天真\n" +
                "[00:01.000] 难过是因为我太认真\n" +
                "[00:01.000] 每当我想起你的眼神\n" +
                "[00:01.000] 悲伤就逆流成河\n" +
                "[00:00.000] 作词 : 姚若龙\n" +
                "[00:01.000] 作曲 : 李荣浩\n" +
                "[00:02.000] 编曲 : 屠颖\n" +
                "[00:14.960]谁影子那么重 拖在我脚步后头\n" +
                "[00:23.320]走不到要去的快乐\n" +
                "[00:30.590]重复做一个梦 怀疑时间凝固了\n" +
                "[00:37.800]把明天杀死了\n" +
                "[00:43.160]\n" +
                "[00:45.740]什么都没移动 屋子的气味变了\n" +
                "[00:53.380]弥漫着腐朽的空洞\n" +
                "[01:00.760]我拒绝不像我 却还奢求你爱我\n" +
                "[01:06.920]倔强让感情窒息了\n" +
                "[01:12.990]\n" +
                "[01:14.100]火烧的寂寞 冷冻的沉默\n" +
                "[01:21.500]没来由的激动 不能抱住你 手像半废了\n" +
                "[01:29.110]被大海淹没 从山顶滑落\n" +
                "[01:36.910]可怕的想念 还活着\n" +
                "[01:46.680]\n" +
                "[02:15.310]谁影子那么重 拖在我脚步后头\n" +
                "[02:23.450]走不到要去的快乐\n" +
                "[02:30.780]重复做一个梦 怀疑时间凝固了\n" +
                "[02:37.130]把明天杀死了\n" +
                "[02:44.240]\n" +
                "[02:45.670]什么都没移动 屋子的气味变了\n" +
                "[02:53.140]弥漫着腐朽的空洞\n" +
                "[03:00.820]我拒绝不想我 却还奢求你爱我\n" +
                "[03:06.990]倔强让感情窒息了\n" +
                "[03:12.750]\n" +
                "[03:13.890]火烧的寂寞 冷冻的沉默\n" +
                "[03:21.480]没来由的激动 不能抱住你 手像半废了\n" +
                "[03:29.400]被大海淹没 从山顶滑落\n" +
                "[03:36.990]可怕的想念 还活着\n" +
                "[03:42.900]\n" +
                "[03:43.960]火烧的寂寞 冷冻的沉默\n" +
                "[03:51.510]在坚持些什么 有时连自己也不是太懂\n" +
                "[03:59.130]我不想祈求 就只好承受\n" +
                "[04:06.940]可怕的想念 翻搅着\n");
        id3v2.setComposer("廖伟志");
        id3v2.setPublisher("出版发行商");
        id3v2.setOriginalArtist("Another Artist");
        id3v2.setAlbumArtist("贝多芬！！！");
        id3v2.setCopyright("Copyright by zsh");
        id3v2.setUrl("https://www.zhangshuhao.work/");
        id3v2.setEncoder("UTF-16");
        BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(Paths.get("C:\\Users\\zsh\\Pictures\\Saved Pictures\\女孩壁纸.jpg")));
        id3v2.setAlbumImage(bis.readAllBytes(), "女孩壁纸图片！！！");

        mp3File.setId3v2Tag(id3v2);

        mp3File.save("src/main/resources/逆流成河2.mp3");
    }
}
