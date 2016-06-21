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
 * Time: 下午2:56
 */
public class LoginAuthRespHandler extends ChannelInboundHandlerAdapter {
    Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {

        NettyMessage message = (NettyMessage) msg;
        if (message.getHeader() != null && message.getHeader().getType() == MessageType.LOGIN_REQ.getValue()) {
            String address = ctx.channel().remoteAddress().toString();
            logger.info(address + " login successfully!");

            String body = (String) message.getBody();
            logger.info("Recevied message  from client is " + message);

            ctx.writeAndFlush(buildLoginResponse((byte) 0));
        } else {
            ctx.fireChannelRead(message);
        }
    }

    private NettyMessage buildLoginResponse(byte result) {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.LOGING_RES.getValue());
        message.setHeader(header);
        message.setBody(result);

        return message;
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
}
