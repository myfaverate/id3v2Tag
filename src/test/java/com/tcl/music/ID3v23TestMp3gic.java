package com.tcl.music;

import com.mpatric.mp3agic.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Itunes
 *
 * @author shuhao1.zhang
 * @version 1.0
 * @className Test
 * @description 测试！！！
 * @date 2023/2/28 19:43
 */
public class ID3v23TestMp3gic {

    private final static Logger logger = LoggerFactory.getLogger(ID3v23TestMp3gic.class);

    public static void main(String[] args) throws InvalidDataException, UnsupportedTagException, IOException, NotSupportedException {
        Mp3File mp3file = new Mp3File("src/main/resources/id3v23/那英 - 默.mp3");
        if (mp3file.hasId3v2Tag()) {
            ID3v2 id3v2Tag = mp3file.getId3v2Tag();
            System.out.println("Track: " + id3v2Tag.getTrack());
            System.out.println("Artist: " + id3v2Tag.getArtist());
            System.out.println("Title: " + id3v2Tag.getTitle());
            System.out.println("Album: " + id3v2Tag.getAlbum());
            System.out.println("Year: " + id3v2Tag.getYear());
            System.out.println("Genre: " + id3v2Tag.getGenre() + " (" + id3v2Tag.getGenreDescription() + ")");
            System.out.println("Comment: " + id3v2Tag.getComment());
            System.out.println("Lyrics: " + id3v2Tag.getLyrics());
            System.out.println("Composer: " + id3v2Tag.getComposer());
            System.out.println("Publisher: " + id3v2Tag.getPublisher());
            System.out.println("Original artist: " + id3v2Tag.getOriginalArtist());
            System.out.println("Album artist: " + id3v2Tag.getAlbumArtist());
            System.out.println("Copyright: " + id3v2Tag.getCopyright());
            System.out.println("URL: " + id3v2Tag.getUrl());
            System.out.println("Encoder: " + id3v2Tag.getEncoder());
            byte[] albumImageData = id3v2Tag.getAlbumImage();
            if (albumImageData != null) {
                System.out.println("Have album image data, length: " + albumImageData.length + " bytes");
                System.out.println("Album image mime type: " + id3v2Tag.getAlbumImageMimeType());
            }
            if (mp3file.hasId3v2Tag()) {
                ID3v2 id3v2Tag1 = mp3file.getId3v2Tag();
                byte[] imageData = id3v2Tag.getAlbumImage();
                if (imageData != null) {
                    String mimeType = id3v2Tag1.getAlbumImageMimeType();
                    // Write image to file - can determine appropriate file extension from the mime type
                    RandomAccessFile file = new RandomAccessFile("src/main/resources/id3v23/默cover.jpg", "rw");
                    file.write(imageData);
                    file.close();
                }
            }
        }
    }
}
