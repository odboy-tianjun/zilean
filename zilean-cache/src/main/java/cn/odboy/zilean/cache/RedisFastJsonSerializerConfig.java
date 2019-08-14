package cn.odboy.zilean.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

/**
 * @author: Odboy
 * @version: Jdk 1.8
 * @desc: TODO Redis序列化器之Fastjson版
 * @time: 2019/8/8 10:39
 * @blog: www.odboy.cn
 */
public class RedisFastJsonSerializerConfig<T> implements RedisSerializer<T> {
    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    private Class<T> clazz;

    static {
        // 解决fastJson autoType is not support错误
        // ParserConfig.getGlobalInstance().addAccept("cn.odboy.zilean.base");
        // ParserConfig.getGlobalInstance().addAccept("cn.odboy.zilean.bms.cn.odboy.zilean.entity");
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    }

    public RedisFastJsonSerializerConfig(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (null == t) {
            return new byte[0];
        }
        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (null == bytes || bytes.length <= 0) {
            return null;
        }
        String str = new String(bytes, DEFAULT_CHARSET);
        return JSON.parseObject(str, clazz);
    }
}
