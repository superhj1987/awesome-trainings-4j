package com.github.superhj1987.trainings.netty.encoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.marshalling.MarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallingEncoder;

/**
 * Author: Bryant
 * Date: 14/11/7
 * Time: 上午11:47
 */
public class NettyMarshallingEncoder extends MarshallingEncoder {


    /**
     * Creates a new encoder.
     *
     * @param provider the {@link io.netty.handler.codec.marshalling.MarshallerProvider} to use
     */
    public NettyMarshallingEncoder(MarshallerProvider provider) {
        super(provider);
    }

    @Override
    public void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        super.encode(ctx, msg, out);
    }
}
