package com.tcl.id3v2.util;

/**
 * @author shuhao1.zhang
 * @version 1.0
 * @className Util
 * @description 工具类
 * @date 2023/3/2 19:22
 */
public class Util {
    public static int getTagByteSize(byte[] byteArray){
        if (byteArray.length == 4) return (byteArray[0] & 0x7F) << 21 | (byteArray[1] & 0x7F) << 14 | (byteArray[2] & 0x7F) << 7 |
                byteArray[3] & 0x7F;
        else
            throw new RuntimeException("class com.tcl.id3v2.util.Util public static int getByteSize(byte[] byteArray) 参数字节数组长度不为4！！！");
    }
    public static int getFrameByteSize(byte[] byteArray){
        if (byteArray.length == 4) return  Byte.toUnsignedInt(byteArray[0]) << 24
                | Byte.toUnsignedInt(byteArray[1]) << 16 | Byte.toUnsignedInt(byteArray[2]) << 8
                | Byte.toUnsignedInt(byteArray[3]);
        else
            throw new RuntimeException("class com.tcl.id3v2.util.Util public static int getByteSize(byte[] byteArray) 参数字节数组长度不为4！！！");
    }
}
