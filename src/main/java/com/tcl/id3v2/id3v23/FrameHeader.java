package com.tcl.id3v2.id3v23;

import com.tcl.id3v2.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FrameHeader {

    public static final Logger log = LoggerFactory.getLogger(FrameHeader.class);

    public final byte[] frameID = new byte[4];
    public final byte[] frameSizeByte = new byte[4];
    public final byte[] frameFlag = new byte[2];
    public int frameSize;
    public byte[] frameContent;

    // frameFlag
    private boolean isTagAlterPreservation; // 标记更改保留
    private boolean isFileAlterPreservation; // 文件更改保留
    private boolean isReadOnly;
    private boolean isCompression; // 压缩
    private boolean isEncryption; // 加密
    private boolean isGroupingIdentity; // 该标志表示该帧是否与其他帧属于一个组
    public FrameHeader() {
        frameSize = 0;
        frameContent = null;
    }
    
    public void getFrameSizeAndFrameContent(){
        frameSize = Util.getFrameByteSize(frameSizeByte);
        frameContent = new byte[frameSize];
    }

    /**
     * 请保证frameFlag存有数据
     */
    public void analyseFlag(){
        isTagAlterPreservation = (frameFlag[0] >> 7 & 1) == 1;
        isFileAlterPreservation = (frameFlag[0] >> 6 & 1) == 1;
        isReadOnly = (frameFlag[0] >> 5 & 1) == 1;

        isCompression = (frameFlag[1] >> 7 & 1) == 1;
        isEncryption = (frameFlag[1] >> 6 & 1) == 1;
        isGroupingIdentity = (frameFlag[1] >> 5 & 1) == 1;
    }

    public boolean isTagAlterPreservation() {
        return isTagAlterPreservation;
    }

    public boolean isFileAlterPreservation() {
        return isFileAlterPreservation;
    }

    public boolean isReadOnly() {
        return isReadOnly;
    }

    public boolean isCompression() {
        return isCompression;
    }

    public boolean isEncryption() {
        return isEncryption;
    }

    public boolean isGroupingIdentity() {
        return isGroupingIdentity;
    }
}