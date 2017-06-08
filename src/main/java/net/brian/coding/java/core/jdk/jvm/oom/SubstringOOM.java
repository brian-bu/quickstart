package net.brian.coding.java.core.jdk.jvm.oom;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * 引用都是存在栈内存中的，引用太多就会导致栈满了存不下更多的引用，就会出现栈溢出的情况
 * 对于实例变量而言，每创建一次实例就需要为实例变量分配一块内存空间，程序有几个实例，实例变量就需要几块内存空间
 * 对象都是存在堆中的，如果new的对象太多堆满了，那么就是堆溢出？
 *
 */
public class SubstringOOM {  
	  
    private static class StrA {  
        // 指定了String对象中的char数组以及长度等信息，这里使用的长度是100000，说明String的原生内容很长  
        private String str = new String(new char[100000]);  
          
        // 使用了有可能导致内存溢出的构造函数来截取子字符串  
        public String getSub(int begin, int end) {  
            return str.substring(begin, end);  
        }  
    }  
      
    private static class StrB {  
        // 指定了String对象中的char数组以及长度等信息，这里使用的长度是100000，说明String的原生内容很长，与StrA中一样  
        private String str = new String(new char[100000]);  
          
        // 虽然使用了有可能导致内存溢出的构造函数来截取子字符串，但是重新生成了String对象  
        public String getSub(int begin, int end) {  
            return new String(str.substring(begin, end));  
        }  
    }  
      
    public static void main(String[] args) {  
        List<String> strList = new ArrayList<String>();  
          
        for (int i = 0; i < 1000000; i++) {  
            StrA strA = new StrA();  
            StrB strB = new StrB();  
            strList.add(strA.getSub(1, 5));  
        }  
    }  
}
