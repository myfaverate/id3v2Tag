package com.tcl;

import com.tcl.id3v2.ID3v2Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Itunes
 *
 * @author shuhao1.zhang
 * @version 1.0
 * @className Test
 * @description 测试！！！
 * @date 2023/2/28 19:43
 */
public class ID3v24Test {

    private final static Logger logger = LoggerFactory.getLogger(ID3v24Test.class);

    public static void main(String[] args){
        new ID3v2Tag(new File(""));
    }
}
