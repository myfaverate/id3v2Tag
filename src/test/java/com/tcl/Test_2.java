package com.tcl;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.flac.metadatablock.MetadataBlockDataPicture;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.flac.FlacTag;
import org.jaudiotagger.tag.id3.ID3v23Frame;
import org.jaudiotagger.tag.id3.ID3v23Tag;
import org.jaudiotagger.tag.id3.framebody.FrameBodyAPIC;
import org.jaudiotagger.tag.id3.framebody.FrameBodyPIC;
import org.jaudiotagger.tag.id3.framebody.FrameBodySYLT;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

/**
 * @author shuhao1.zhang
 * @version 1.0
 * @className Test_2
 * @description TODO
 * @date 2023/3/3 10:38
 */
public class Test_2 {
    public static void main(String[] args) throws CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException, IOException {
        AudioFile f = AudioFileIO.read(new File("src/main/resources/轩东 - 碎银几两.flac"));
        FlacTag tag = (FlacTag) f.getTag();
        new ID3v23Frame();
        new ID3v23Tag();
        new FrameBodyAPIC();
        new FrameBodySYLT();
        MetadataBlockDataPicture image = tag.getImages().get(0);
        int pictureType = image.getPictureType();
        System.out.println("图片类型：" + pictureType);
        System.out.println("getMimeType(): " + image.getMimeType());
        System.out.println("getImageUrl: " + image.getImageUrl());
        System.out.println("图片描述：" + image.getDescription());
        System.out.println("图片宽度：" + image.getWidth());
        System.out.println("图片高度：" + image.getHeight());
        System.out.println("getColourDepth： " + image.getColourDepth());
        System.out.println("getIndexedColourCount: " + image.getIndexedColourCount());
        BufferedImage bi = ImageIO.read(ImageIO.createImageInputStream(new ByteArrayInputStream(image.getImageData())));
        ImageIO.write(bi, "jpg", new File("src/main/resources/轩东 - 碎银几两cover.jpg"));
    }
}
