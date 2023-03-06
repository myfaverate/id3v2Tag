package com.tcl.id3v2.id3v24;

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
    private boolean isGroupingIdentity; // 该标志表示该帧是否与其他帧属于一个组
    private boolean isCompression; // 压缩
    private boolean isEncryption; // 加密
    private boolean isUnSynchronisation; // 该标志表示是否对该帧应用了不同步
    private boolean isDataLengthIndicator; // 该标志表示是否对该帧应用了不同步
    public FrameHeader() {
        frameSize = 0;
        frameContent = null;
    }
    
    public void getFrameSizeAndFrameContent(){
        frameSize = Util.getTagByteSize(frameSizeByte);
        frameContent = new byte[frameSize];
    }

    /**
     * 请保证frameFlag存有数据
     */
    public void analyseFlag(){
        isTagAlterPreservation = (frameFlag[0] >> 6 & 1) == 1;
        isFileAlterPreservation = (frameFlag[0] >> 5 & 1) == 1;
        isReadOnly = (frameFlag[0] >> 4 & 1) == 1;

        isCompression = (frameFlag[1] >> 6 & 1) == 1;
        isEncryption = (frameFlag[1] >> 3 & 1) == 1;
        isGroupingIdentity = (frameFlag[1] >> 2 & 1) == 1;
        isUnSynchronisation = (frameFlag[1] >> 1 & 1) == 1;
        isDataLengthIndicator = (frameFlag[1] & 1) == 1;
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

    public boolean isUnSynchronisation() {
        return isUnSynchronisation;
    }

    public boolean isDataLengthIndicator() {
        return isDataLengthIndicator;
    }
}