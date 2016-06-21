package com.github.superhj1987.trainings.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import com.github.superhj1987.trainings.netty.encoder.NettyMessageDecoder;
import com.github.superhj1987.trainings.netty.encoder.NettyMessageEncoder;
import com.github.superhj1987.trainings.netty.handler.LoginAuthRespHandler;
import com.github.superhj1987.trainings.netty.util.Constant;

/**
 * Author: Bryant
 * Date: 14/11/7
 * Time: 下午3:00
 */
public class NettyServer {
    public void bind() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1025)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new NettyMessageDecoder(1024 * 1024, 4, 4, -8, 0));
                        ch.pipeline().addLast(new NettyMessageEncoder());
                        ch.pipeline().addLast(new ReadTimeoutHandler(50));
                        ch.pipeline().addLast(new LoginAuthRespHandler());
                        //ch.pipeline().addLast(new HeartBeatRespHandler());
                    }
                });

        b.bind(Constant.SERVER_IP, Constant.SERVER_PORT).sync();

        System.out.println("Netty server start ok: " + (Constant.SERVER_IP + ":" + Constant.SERVER_PORT));
    }

    public static void main(String[] args) throws InterruptedException {
        new NettyServer().bind();
    }
}
