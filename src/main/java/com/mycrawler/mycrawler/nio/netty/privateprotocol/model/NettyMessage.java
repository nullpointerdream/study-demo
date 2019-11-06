package com.mycrawler.mycrawler.nio.netty.privateprotocol.model;

import lombok.Data;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-11-06 09:37
 **/
@Data
public class NettyMessage {
    private Header header;

    private Object body;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
