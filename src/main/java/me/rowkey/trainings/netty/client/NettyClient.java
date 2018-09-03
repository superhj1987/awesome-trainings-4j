package me.rowkey.trainings.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import me.rowkey.trainings.netty.encoder.NettyMessageDecoder;
import me.rowkey.trainings.netty.encoder.NettyMessageEncoder;
import me.rowkey.trainings.netty.handler.LoginAuthReqHandler;
import me.rowkey.trainings.netty.util.Constant;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Author: Bryant
 * Date: 14/11/7
 * Time: 下午3:09
 */
public class NettyClient {
    EventLoopGroup group = new NioEventLoopGroup();
    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    public static void main(String[] args) throws InterruptedException {
        new NettyClient().connect(Constant.SERVER_IP, Constant.SERVER_PORT);
    }

    public void connect(String host, int port) throws InterruptedException {
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyMessageDecoder(1024 * 1024, 4, 4, -8, 0));
                            ch.pipeline().addLast(new NettyMessageEncoder());
                            ch.pipeline().addLast(new ReadTimeoutHandler(50));
                            ch.pipeline().addLast(new LoginAuthReqHandler());
                            //ch.pipeline().addLast(new HeartBeatReqHandler());
                        }
                    });

            ChannelFuture future = b.connect(new InetSocketAddress(host, port)).sync();

            future.channel().closeFuture().sync();
        } finally {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(5);
                        try {
                            connect(Constant.SERVER_IP, Constant.SERVER_PORT);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
