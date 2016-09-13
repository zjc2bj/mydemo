package com.lt.kryo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.objenesis.strategy.StdInstantiatorStrategy;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.TaggedFieldSerializer;

public class KryoDemo {
    public static void main(String[] args) {
        KyroBean1 b1 = new KyroBean1("zjc", "26", "2015-04");
        b1.setAdress("hn");
        try {
            OutputStream out = new FileOutputStream("C://kero");
            returnKryoResult(out, b1);
            InputStream in = new FileInputStream("C://kero");
            KyroBean2 bean2 = getReplyFromPolicyByKryo(in, KyroBean2.class);
            System.out.println(bean2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static <T> void returnKryoResult(OutputStream out, T reply) {
        Output output = null;
        try {
            output = new Output(out);
            Kryo kryo = new Kryo();
            kryo.setReferences(true);
            kryo.register(reply.getClass(), new TaggedFieldSerializer<T>(kryo, reply.getClass()));
            kryo.setRegistrationRequired(false);
            kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
            kryo.writeObject(output, reply);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (output != null) {
                    output.flush();
                    output.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected static <T> T getReplyFromPolicyByKryo(InputStream in, Class<T> replyClass) {
        Input input = null;
        T reply = null;
        Kryo kryo = new Kryo();
        kryo.setReferences(true);
        kryo.register(replyClass, new TaggedFieldSerializer<T>(kryo, replyClass));
        kryo.setRegistrationRequired(false);
        kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
        input = new Input(in);
        reply = (T) kryo.readObject(input, replyClass);
        return reply;
    }
}
