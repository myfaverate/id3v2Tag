package com.tcl.id3v2.id3v23;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * Attached picture frame.
 * <p>
 * This frame contains a picture directly related to the audio file.
 * Image format is the MIME type and subtype for the image. In
 * the event that the MIME media type name is omitted, "image/" will be
 * implied. The "image/png" or "image/jpeg" picture format
 * should be used when interoperability is wanted. Description is a
 * short description of the picture, represented as a terminated
 * textstring. The description has a maximum length of 64 characters,
 * but may be empty. There may be several pictures attached to one file,
 * each in their individual "APIC" frame, but only one with the same
 * content descriptor. There may only be one picture with the picture
 * type declared as picture type $01 and $02 respectively. There is the
 * possibility to put only a link to the image file by using the 'MIME
 * type' "-->" and having a complete URL instead of picture data.
 * The use of linked files should however be used sparingly since there
 * is the risk of separation of files.
 * <p><table border=0 width="70%">
 * <tr><td colspan=2> &lt;Header for 'Attached picture', ID: "APIC"&gt;</td></tr>
 * <tr><td>Text encoding  </td><td>$xx                            </td></tr>
 * <tr><td>MIME type      </td><td>&lt;text string&gt; $00        </td></tr>
 * <tr><td>Picture type   </td><td>$xx                            </td></tr>
 * <tr><td>Description    </td><td>&lt;text string according to encoding&gt; $00 (00)</td></tr>
 * <tr><td>Picture data   </td><td>&lt;binary data&gt;            </td></tr>
 * </table>
 * <p><table border=0 width="70%">
 * <tr><td rowspan=21 valign=top>Picture type:</td>
 * <td>$00 </td><td>Other                                </td></tr>
 * <tr><td>$01 </td><td>32x32 pixels 'file icon' (PNG only)  </td></tr>
 * <tr><td>$02 </td><td>Other file icon                      </td></tr>
 * <tr><td>$03 </td><td>Cover (front)                        </td></tr>
 * <tr><td>$04 </td><td>Cover (back)                         </td></tr>
 * <tr><td>$05 </td><td>Leaflet page                         </td></tr>
 * <tr><td>$06 </td><td>Media (e.g. lable side of CD)        </td></tr>
 * <tr><td>$07 </td><td>Lead artist/lead performer/soloist   </td></tr>
 * <tr><td>$08 </td><td>Artist/performer                     </td></tr>
 * <tr><td>$09 </td><td>Conductor                            </td></tr>
 * <tr><td>$0A </td><td>Band/Orchestra                       </td></tr>
 * <tr><td>$0B </td><td>Composer                             </td></tr>
 * <tr><td>$0C </td><td>Lyricist/text writer                 </td></tr>
 * <tr><td>$0D </td><td>Recording Location                   </td></tr>
 * <tr><td>$0E </td><td>During recording                     </td></tr>
 * <tr><td>$0F </td><td>During performance                   </td></tr>
 * <tr><td>$10 </td><td>Movie/video screen capture           </td></tr>
 * <tr><td>$11 </td><td>A bright coloured fish               </td></tr>
 * <tr><td>$12 </td><td>Illustration                         </td></tr>
 * <tr><td>$13 </td><td>Band/artist logotype                 </td></tr>
 * <tr><td>$14 </td><td>Publisher/Studio logotype            </td></tr>
 * </table>
 *
 * <p>For more details, please refer to the ID3 specifications:
 * <ul>
 * <li><a href="http://www.id3.org/id3v2.3.0.txt">ID3 v2.3.0 Spec</a>
 * </ul>
 *
 * @author shuhao1.zhang
 * @version 1.0
 * @className FrameBodyAPIC
 * @description TODO
 * @date 2023/3/3 16:42
 */
public class FrameBodyAPIC {
    private static final Logger log = LoggerFactory.getLogger(FrameBodyAPIC.class);
    private byte textCodingByte;
    private String textCoding;
    private byte[] mimeType;
    private byte pictureType;
    private byte[] descriptionByte;
    private String description;
    private byte[] imageByte;

    public FrameBodyAPIC(byte[] frameContent) {
        textCodingByte = frameContent[0];
        if (textCodingByte == 0)  textCoding = StandardCharsets.ISO_8859_1.name();
        else textCoding = "Unicode";

        int i = 1;
        while (i < frameContent.length){
            if (frameContent[i] == 0) break;
            i ++ ;
            log.info("i 死循环检测: " + i);
        }
        mimeType = new byte[i - 1];
        System.arraycopy(frameContent, 1, mimeType, 0, i - 1);
        log.info("mimeType类型是：" + new String(mimeType, StandardCharsets.ISO_8859_1));

        // pictureType
        ++ i;
        pictureType = frameContent[i];

        // description
        ++ i;
        int j = i;
        while (j < frameContent.length){
            if (frameContent[j] == 0) break;
            j ++ ;
            log.info("j 死循环检测: " + j);
        }

        descriptionByte = new byte[j - i];
        System.arraycopy(frameContent, i, descriptionByte, 0, j - i);
        try {
            description = new String(descriptionByte, textCoding);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        log.info("图片描述的内容是：" + description);

        // 1 2 3  APIC frameContent, 读取长度：33208 - 13
        // 0 1 2
        j ++ ;
        imageByte = new byte[frameContent.length - j];
        log.info("图片大小：" + (frameContent.length - j));
        System.arraycopy(frameContent, j, imageByte, 0, frameContent.length - j);
    }

    public String getTextCoding() {
        return textCoding;
    }

    public byte[] getMimeType() {
        return mimeType;
    }

    public byte getPictureType() {
        return pictureType;
    }

    public String getDescription() {
        return description;
    }

    public byte[] getImageByte() {
        return imageByte;
    }
}