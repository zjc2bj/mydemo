package cn.zjc.soa;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.objenesis.strategy.StdInstantiatorStrategy;

import cn.zjc.soa.vo.base.AbstractReply;
import cn.zjc.soa.vo.base.AbstractRequest;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public abstract class AbstractServer {

    public static final String BUYER_REQUEST_KEY = "req";

    /**
     * 从HttpRequest中获取 Request
     * 
     * @param <T>
     * @param request
     * @param clazz
     * @return
     */
    public <T extends AbstractRequest> T getPolicyRequestFromRequest(HttpServletRequest request, Class<T> requestClass) {
        return (T) AbstractRequest.jsonToRequest(getJsonFromRequest(request), requestClass);
    }

    /**
     * 从HttpRequest中获取 Request
     * 
     * @param <T>
     * @param request
     * @param clazz
     * @return
     */
    public <T extends AbstractRequest> T getPolicyRequestFromRequest(ServletInputStream stream, Class<T> requestClass) {
        Input input = null;
        Kryo kryo = new Kryo();
        kryo.setReferences(true);
        kryo.setRegistrationRequired(false);
        kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
        input = new Input(stream);
        return (T) kryo.readObject(input, requestClass);
    }

    public String getJsonFromRequest(HttpServletRequest request) {
        return request.getParameter(BUYER_REQUEST_KEY);
    }

    /**
     * 返回成功结果到view
     * 
     * @param <T>
     * @param response
     * @param reply
     */

    public <T extends AbstractReply> void returnJsonResult(HttpServletResponse response, T reply) {
        try {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(reply.replyToJson());
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T extends AbstractReply> void returnZipResult(HttpServletResponse response, T reply) {
        try {
            String str = reply.replyToJson();
            response.setContentType("application/zip;charset=utf-8");
            response.getWriter().write(str);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T extends AbstractReply> void returnGZIPResult(HttpServletResponse response, T reply) {

        GZIPOutputStream gzipout = null;
        ObjectOutputStream oos = null;
        try {
            gzipout = new GZIPOutputStream(response.getOutputStream());
            oos = new ObjectOutputStream(gzipout);
            oos.writeObject(reply);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (gzipout != null) {
                    gzipout.flush();
                    gzipout.close();
                }
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public <T extends AbstractReply> void returnKryoResult(HttpServletResponse response, T reply) {
        Output output = null;
        try {
            output = new Output(response.getOutputStream());
            Kryo kryo = new Kryo();
            kryo.setReferences(true);
            kryo.setRegistrationRequired(false);
            kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
            kryo.writeObject(output, reply);
        } catch (IOException e) {
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
}