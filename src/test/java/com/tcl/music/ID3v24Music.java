package com.tcl.music;

import com.mpatric.mp3agic.*;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author shuhao1.zhang
 * @version 1.0
 * @className ID3v24Music
 * @description TODO
 * @date 2023/3/4 19:01
 */
public class ID3v24Music {
    public static void main(String[] args) throws InvalidDataException, UnsupportedTagException, IOException, NotSupportedException {
        File file = new File("src/main/resources/id3v24/不要说话1.mp3");
        Mp3File mp3File = new Mp3File(file);

        // if (mp3File.hasId3v2Tag()) mp3File.removeId3v2Tag();

        ID3v24Tag id3v2;
        if (mp3File.hasId3v2Tag()){
            id3v2 = (ID3v24Tag) mp3File.getId3v2Tag();
        }else {
            id3v2 = new ID3v24Tag();
        }

        id3v2.setGenreDescription("Hello World !!! 太好听了，身体放松！！！");
        id3v2.setArtistUrl("https://www.baidu.com");
        id3v2.setDate("2023.03.04");
        id3v2.setComment("😊😊😊😊《不要说话》是中国香港流行男歌手陈奕迅演唱的一首歌曲，由小柯填词、谱曲，Mac Chew编曲，收录在陈奕迅2008年6月30日由环球唱片发行的专辑《不想放手》中。该歌曲是陈奕迅为电影《时尚先生》灌录的主题曲。 [1-2]  \n" +
                "2009年，陈奕迅凭借《不要说话》该歌曲荣获第13届中华音乐人交流协会年度十大金曲。 [3] ");

        id3v2.setTrack("5");
        id3v2.setArtist("陈奕迅");
        id3v2.setTitle("不要说话");
        id3v2.setAlbum("不想放手");
        id3v2.setYear("2023");
        id3v2.setGenre(12);
        id3v2.setComment("真好听哇。。。😊😊😊😊😂😂");
        id3v2.setLyrics("[00:00.000] 作词 : 小柯\n" +
                "[00:01.000] 作曲 : 小柯\n" +
                "[00:02.000] 编曲 : Mac Chew\n" +
                "[00:18.77]深色的海面布满白色的月光\n" +
                "[00:25.11]我出神望着海 心不知飞哪去\n" +
                "[00:31.64]听到她在告诉你\n" +
                "[00:35.28]说她真的喜欢你\n" +
                "[00:39.48]我不知该 躲哪里\n" +
                "[00:47.17]爱一个人是不是应该有默契\n" +
                "[00:54.15]我以为你懂得每当我看着你\n" +
                "[01:00.07]我藏起来的秘密\n" +
                "[01:03.67]在每一天清晨里\n" +
                "[01:07.60]暖成咖啡 安静的拿给你\n" +
                "[01:14.33]愿意 用一支黑色的铅笔\n" +
                "[01:18.81]画一出沉默舞台剧\n" +
                "[01:22.95]灯光再亮 也抱住你\n" +
                "[01:28.53]愿意 在角落唱沙哑的歌\n" +
                "[01:33.06]再大声也都是给你\n" +
                "[01:37.24]请用心听 不要说话\n" +
                "[01:51.54]爱一个人是不是应该有默契\n" +
                "[01:58.36]我以为你懂得每当我看着你\n" +
                "[02:04.34]我藏起来的秘密\n" +
                "[02:08.22]在每一天清晨里\n" +
                "[02:11.47]暖成咖啡 安静的拿给你\n" +
                "[02:18.49]愿意 用一支黑色的铅笔\n" +
                "[02:22.92]画一出沉默舞台剧\n" +
                "[02:27.31]灯光再亮 也抱住你\n" +
                "[02:33.04]愿意 在角落唱沙哑的歌\n" +
                "[02:37.33]再大声也都是给你\n" +
                "[02:41.46]请用心听 不要说话\n" +
                "[03:15.81]愿意 用一支黑色的铅笔\n" +
                "[03:19.95]画一出沉默舞台剧\n" +
                "[03:24.43]灯光再亮 也抱住你\n" +
                "[03:29.82]愿意 在角落唱沙哑的歌\n" +
                "[03:34.19]再大声也都是给你\n" +
                "[03:38.48]请原谅我 不会说话\n" +
                "[03:44.11]愿意 用一支黑色的铅笔\n" +
                "[03:48.55]画一出沉默舞台剧\n" +
                "[03:52.68]灯光再亮 也抱住你\n" +
                "[03:58.35]愿意 在角落唱沙哑的歌\n" +
                "[04:02.84]再大声也都是给你\n" +
                "[04:06.97]爱是用心吗 不要说话\n" +
                "[04:08.97]播放到这里就结束了，拜拜大家，ヾ(•ω•`)o😘😘💕💕💕💕");

        id3v2.setComposer("Mac Chew");
        id3v2.setPublisher("发信时间2008年6月30日");
        id3v2.setOriginalArtist("陈奕迅");
        id3v2.setAlbumArtist("弹着吉他的少年");
        id3v2.setCopyright("Copyright by zsh");
        id3v2.setUrl("https://baike.baidu.com/item/%E4%B8%8D%E8%A6%81%E8%AF%B4%E8%AF%9D/2285732?fromModule=lemma_search-box");
        id3v2.setEncoder("UTF-16");
        BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(Paths.get("D:\\SoftWare\\JetBrains\\netbian\\动漫\\3\\灵笼 白月魁 相机 动漫壁纸.jpg")));
        id3v2.setAlbumImage(bis.readAllBytes(), "女孩壁纸图片！！！");

        mp3File.setId3v2Tag(id3v2);

        mp3File.save("src/main/resources/id3v24/不要说话2.mp3");
    }
}
