package net.brian.coding.db.redis;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;

public class ProtostuffSerializerUtil {
	public static <T> byte[] serialize(final T source, final Schema<T> schema, final LinkedBuffer buffer) {
		return ProtostuffIOUtil.toByteArray(source, schema, buffer);
	}

	public static <T> T deserialize(final byte[] bytes, final T result, final Schema<T> schema) {
		ProtostuffIOUtil.mergeFrom(bytes, result, schema);
		return result;
	}
}
