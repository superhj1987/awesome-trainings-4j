package me.rowkey.trainings.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.ScheduledFuture;
import me.rowkey.trainings.netty.meta.Header;
import me.rowkey.trainings.netty.meta.MessageType;
import me.rowkey.trainings.netty.meta.NettyMessage;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

/**
 * Author: Bryant
 * Date: 14/11/7
 * Time: 下午5:07
 */
public class HeartBeatReqHandler extends ChannelInboundHandlerAdapter {
    Logger logger = Logger.getLogger(this.getClass());

    private volatile ScheduledFuture<?> heartBeat;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;

        if (message.getHeader() != null && message.getHeader().getType() == MessageType.LOGING_RES.getValue()) {
            heartBeat = ctx.executor().scheduleAtFixedRate(new HeartBeatTask(ctx), 0, 5000, TimeUnit.MILLISECONDS);
        } else if (message.getHeader() != null && message.getHeader().getType() == MessageType.HEARTBEAT_RES.getValue()) {
            logger.info("Client receive server heatbeat response message: " + message);
        } else {
            ctx.fireChannelRead(message);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (heartBeat != null) { //!!!释放资源
            heartBeat.cancel(true);
            heartBeat = null;
        }

        ctx.fireExceptionCaught(cause);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    class HeartBeatTask implements Runnable {
        private final ChannelHandlerContext ctx;

        public HeartBeatTask(final ChannelHandlerContext ctx) {
            this.ctx = ctx;
        }


        @Override
        public void run() {
            NettyMessage message = buildHeatBeat();

            logger.info("Client send heartBeat message to server : " + message);

            ctx.writeAndFlush(message);
        }

        private NettyMessage buildHeatBeat() {
            NettyMessage message = new NettyMessage();
            Header header = new Header();

            header.setType(MessageType.HEARTBEAT_REQ.getValue());
            message.setHeader(header);

            return message;
        }
    }
}
