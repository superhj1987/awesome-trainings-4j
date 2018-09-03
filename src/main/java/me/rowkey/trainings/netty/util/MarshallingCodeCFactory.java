package me.rowkey.trainings.netty.util;

import io.netty.handler.codec.marshalling.DefaultMarshallerProvider;
import io.netty.handler.codec.marshalling.DefaultUnmarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallerProvider;
import io.netty.handler.codec.marshalling.UnmarshallerProvider;
import me.rowkey.trainings.netty.encoder.NettyMarshallingDecoder;
import me.rowkey.trainings.netty.encoder.NettyMarshallingEncoder;
import org.jboss.marshalling.Marshaller;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

import java.io.IOException;

/**
 * Author: Bryant
 * Date: 14/11/7
 * Time: 上午11:34
 */
public class MarshallingCodeCFactory {

    public static NettyMarshallingDecoder buildMarshallingDecoder() {
        MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");

        MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        UnmarshallerProvider provider = new DefaultUnmarshallerProvider(marshallerFactory, configuration);

        NettyMarshallingDecoder decoder = new NettyMarshallingDecoder(provider, 1024);

        return decoder;
    }

    public static NettyMarshallingEncoder buildMarshallingEncoder() {
        MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");

        MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        MarshallerProvider provider = new DefaultMarshallerProvider(marshallerFactory, configuration);

        NettyMarshallingEncoder encoder = new NettyMarshallingEncoder(provider);


        return encoder;
    }

    public static Marshaller buildMarshalling() throws IOException {
        MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");

        MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);

        return marshallerFactory.createMarshaller(configuration);

    }
}
