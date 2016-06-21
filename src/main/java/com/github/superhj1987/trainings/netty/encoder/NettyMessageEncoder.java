package com.github.superhj1987.trainings.netty.encoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import com.github.superhj1987.trainings.netty.meta.NettyMessage;
import com.github.superhj1987.trainings.netty.util.MarshallingCodeCFactory;

import java.util.List;

/**
 * Author: Bryant
 * Date: 14/11/7
 * Time: 上午10:50
 */
public class NettyMessageEncoder extends MessageToMessageEncoder<NettyMessage> {
    NettyMarshallingEncoder marshallingEncoder;

    public NettyMessageEncoder() {
        marshallingEncoder = MarshallingCodeCFactory.buildMarshallingEncoder();
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, NettyMessage nettyMessage, List<Object> objects) throws Exception {
        if (nettyMessage == null || nettyMessage.getHeader() == null)
            throw new Exception("message is null");

        ByteBuf sendBuf = Unpooled.buffer();

        sendBuf.writeInt(nettyMessage.getHeader().getCrcCode());
        sendBuf.writeInt(nettyMessage.getHeader().getLength());
        sendBuf.writeLong(nettyMessage.getHeader().getSessionID());
        sendBuf.writeByte(nettyMessage.getHeader().getType());
        sendBuf.writeByte(nettyMessage.getHeader().getPriority());

        if (nettyMessage.getBody() != null) {
            try {
                marshallingEncoder.encode(channelHandlerContext, nettyMessage.getBody(), sendBuf);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        sendBuf.setInt(4, sendBuf.readableBytes());

        objects.add(sendBuf);
    }
}
