package net.brian.coding.java.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class CastUtil {
	
	private CastUtil() {}

	// FIXME: 字符串：整理成工具类
	public void stringCastingToPrimitiveTypes() {
		String str1 = String.valueOf(1);
		String str2 = Integer.toString(1);
		// 输出true，因为String.valueOf(i)也是调用Integer.toString(i)来实现的
		System.out.println("str1.equals(str2):: " + str1.equals(str2));
		String s = "9";
		byte b = Byte.parseByte(s);
		String bs = String.valueOf(b);
		System.out.println("Casted by java.lang.String.valueOf(int):: " + bs);
		short t = Short.parseShort(s);
		String ts = String.valueOf(t);
		System.out.println("Casted by java.lang.String.valueOf(int):: " + ts);
		int i = Integer.parseInt(s);
		String is = String.valueOf(i);
		System.out.println("Casted by java.lang.String.valueOf(int):: " + is);
		long l = Long.parseLong(s);
		String ls = String.valueOf(l);
		System.out.println("Casted by java.lang.String.valueOf(long):: " + ls);
		Float f = Float.parseFloat(s);
		String fs = String.valueOf(f);
		System.out.println("Casted by java.lang.String.valueOf(Object):: " + fs);
		Double d = Double.parseDouble(s);
		String ds = String.valueOf(d);
		System.out.println("Casted by java.lang.String.valueOf(Object):: " + ds);
		char[] c = s.toCharArray();
		String cs = String.valueOf(c);
		System.out.println("Casted by java.lang.String.valueOf(char[]):: " + cs);
	}

	public byte[] longToBytes(long x) {
		ByteBuffer buffer = ByteBuffer.allocate(8);
		buffer.putLong(x);
		return buffer.array();
	}
public long bytesToLong(byte[] bytes) {
		ByteBuffer buffer = ByteBuffer.allocate(8);
		buffer.put(bytes);
		buffer.flip();// need flip
		return buffer.getLong();
	}

 public static final byte[] intToByteArray(int value) {
    return new byte[] {
            (byte)(value >>> 24),
            (byte)(value >>> 16),
            (byte)(value >>> 8),
            (byte)value};
   }

static byte[] concatByteArray(byte[] a, byte[] b) {

		byte[] c = new byte[a.length + b.length];
		System.arraycopy(a, 0, c, 0, a.length);
		System.arraycopy(b, 0, c, a.length, b.length);
		return c;
	}
public static byte[] GetBytes(int value) {
		ByteBuffer buffer = ByteBuffer.allocate(4).order(ByteOrder.nativeOrder());
		buffer.putInt(value);
		return buffer.array();
	}
public static long getUnsignedInt(int x) {
return x & 0x00000000ffffffffL;	
}


	
	public void primitiveTypesCasting() {
		byte b = (byte) 169;
		Byte bo = new Byte(b);
		b = bo.byteValue();

		short t1 = 169;
		Short to1 = new Short(t1);
		t1 = to1.shortValue();

		int i = 169;
		b = bo.byteValue();

		short t2 = 169;
		Short to2 = new Short(t2);
		t2 = to2.shortValue();

		int i1 = 169;
		Integer io = new Integer(i1);
		i1 = io.intValue();

		long l = 169;
		Long lo = new Long(l);
		l = lo.longValue();

		float f = 169f;
		Float fo = new Float(f);
		f = fo.floatValue();

		double d = 169f;
		Double dObj = new Double(d);
		d = dObj.doubleValue();
	}


    public static String castString(Object obj) {
        return CastUtil.castString(obj, "");
    }

    public static String castString(Object obj, String defaultValue) {
        return obj != null ? String.valueOf(obj) : defaultValue;
    }

    public static double castDouble(Object obj) {
        return CastUtil.castDouble(obj, 0);
    }

    public static double castDouble(Object obj, double defaultValue) {
        double doubleValue = defaultValue;
        if (obj != null) {
            String strValue = castString(obj);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    doubleValue = Double.parseDouble(strValue);
                } catch (NumberFormatException e) {
                    doubleValue = defaultValue;
                }
            }
        }
        return doubleValue;
    }

    public static long castLong(Object obj) {
        return CastUtil.castLong(obj, 0);
    }

    public static long castLong(Object obj, long defaultValue) {
        long longValue = defaultValue;
        if (obj != null) {
            String strValue = castString(obj);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    longValue = Long.parseLong(strValue);
                } catch (NumberFormatException e) {
                    longValue = defaultValue;
                }
            }
        }
        return longValue;
    }

    public static int castInt(Object obj) {
        return CastUtil.castInt(obj, 0);
    }

    public static int castInt(Object obj, int defaultValue) {
        int intValue = defaultValue;
        if (obj != null) {
            String strValue = castString(obj);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    intValue = Integer.parseInt(strValue);
                } catch (NumberFormatException e) {
                    intValue = defaultValue;
                }
            }
        }
        return intValue;
    }

    public static boolean castBoolean(Object obj) {
        return CastUtil.castBoolean(obj, false);
    }

    public static boolean castBoolean(Object obj, boolean defaultValue) {
        boolean booleanValue = defaultValue;
        if (obj != null) {
            booleanValue = Boolean.parseBoolean(castString(obj));
        }
        return booleanValue;
    }
}