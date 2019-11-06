package com.mycrawler.mycrawler.nio.netty.privateprotocol.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: mycrawler
 * @description: 正常还需要crc校验包的完整性
 * @author: chenjiale
 * @create: 2019-11-06 09:35
 **/
@Data
public class Header {
    private int crcCode=0xabef0101;

    private int length;//消息长度

    private long sessionID;//会话ID

    private byte type;//消息类型

    private byte priority;//消息优先级

    private Map<String,Object> attachment=new HashMap<String, Object>();//附件

    public int getCrcCode() {
        return crcCode;
    }

    public void setCrcCode(int crcCode) {
        this.crcCode = crcCode;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public long getSessionID() {
        return sessionID;
    }

    public void setSessionID(long sessionID) {
        this.sessionID = sessionID;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte getPriority() {
        return priority;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }

    public Map<String, Object> getAttachment() {
        return attachment;
    }

    public void setAttachment(Map<String, Object> attachment) {
        this.attachment = attachment;
    }
}
