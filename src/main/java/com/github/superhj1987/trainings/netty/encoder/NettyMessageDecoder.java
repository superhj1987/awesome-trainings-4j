package com.github.superhj1987.trainings.netty.encoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import com.github.superhj1987.trainings.netty.meta.Header;
import com.github.superhj1987.trainings.netty.meta.NettyMessage;
import com.github.superhj1987.trainings.netty.util.MarshallingCodeCFactory;

/**
 * Author: Bryant
 * Date: 14/11/7
 * Time: 下午2:28
 */
public class NettyMessageDecoder extends LengthFieldBasedFrameDecoder {
    NettyMarshallingDecoder marshallingDecoder;


    public NettyMessageDecoder(int maxFrameLength,
                               int lengthFieldOffset, int lengthFieldLength,
                               int lengthAdjustment, int initialBytesToStrip) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);

        marshallingDecoder = MarshallingCodeCFactory.buildMarshallingDecoder();
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        ByteBuf buf = (ByteBuf) super.decode(ctx, in);
        if (buf == null) {
            return null;
        }

        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setCrcCode(buf.readInt());
        header.setLength(buf.readInt());
        header.setSessionID(buf.readLong());
        header.setType(buf.readByte());
        header.setPriority(buf.readByte());

        if (buf.readableBytes() > 0) {
            message.setBody(marshallingDecoder.decode(ctx, buf));
        }

        message.setHeader(header);

        return message;
    }
}
