package me.rowkey.trainings.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import me.rowkey.trainings.netty.meta.Header;
import me.rowkey.trainings.netty.meta.MessageType;
import me.rowkey.trainings.netty.meta.NettyMessage;
import org.apache.log4j.Logger;


/**
 * Author: Bryant
 * Date: 14/11/7
 * Time: 下午2:42
 */
public class LoginAuthReqHandler extends ChannelInboundHandlerAdapter {
    Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(buildLoginReq());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        NettyMessage message = (NettyMessage) msg;
        if (message.getHeader() != null && message.getHeader().getType() == MessageType.LOGING_RES.getValue()) {
            logger.info("Received from server response : " + message);
        }
        ctx.fireChannelRead(msg);
    }

    private NettyMessage buildLoginReq() {
        NettyMessage message = new NettyMessage();

        Header header = new Header();
        header.setType(MessageType.LOGIN_REQ.getValue());
        message.setHeader(header);


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
