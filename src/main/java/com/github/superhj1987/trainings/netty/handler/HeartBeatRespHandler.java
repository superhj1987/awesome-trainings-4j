package com.github.superhj1987.trainings.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import com.github.superhj1987.trainings.netty.meta.Header;
import com.github.superhj1987.trainings.netty.meta.MessageType;
import com.github.superhj1987.trainings.netty.meta.NettyMessage;
import org.apache.log4j.Logger;


/**
 * Author: Bryant
 * Date: 14/11/7
 * Time: 下午5:26
 */
public class HeartBeatRespHandler extends ChannelInboundHandlerAdapter {
    static Logger logger = Logger.getLogger(HeartBeatRespHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;

        if (message.getHeader() != null && message.getHeader().getType() == MessageType.HEARTBEAT_REQ.getValue()) {
            logger.info("receive client hearbeat message : " + message);

            NettyMessage hb = buildHeatBeat();
            logger.info("send heartbeat response to client : " + hb);
            ctx.writeAndFlush(hb);
        } else {
            ctx.fireChannelRead(message);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }

    private NettyMessage buildHeatBeat() {
        NettyMessage message = new NettyMessage();
        Header header = new Header();

        header.setType(MessageType.HEARTBEAT_RES.getValue());
        message.setHeader(header);

        return message;
    }
}
