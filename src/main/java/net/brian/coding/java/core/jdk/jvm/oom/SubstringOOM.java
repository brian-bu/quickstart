package net.brian.coding.java.core.jdk.jvm.oom;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * ���ö��Ǵ���ջ�ڴ��еģ�����̫��ͻᵼ��ջ���˴治�¸�������ã��ͻ����ջ��������
 * ����ʵ���������ԣ�ÿ����һ��ʵ������ҪΪʵ����������һ���ڴ�ռ䣬�����м���ʵ����ʵ����������Ҫ�����ڴ�ռ�
 * �����Ǵ��ڶ��еģ����new�Ķ���̫������ˣ���ô���Ƕ������
 *
 */
public class SubstringOOM {  
	  
    private static class StrA {  
        // ָ����String�����е�char�����Լ����ȵ���Ϣ������ʹ�õĳ�����100000��˵��String��ԭ�����ݺܳ�  
        private String str = new String(new char[100000]);  
          
        // ʹ�����п��ܵ����ڴ�����Ĺ��캯������ȡ���ַ���  
        public String getSub(int begin, int end) {  
            return str.substring(begin, end);  
        }  
    }  
      
    private static class StrB {  
        // ָ����String�����е�char�����Լ����ȵ���Ϣ������ʹ�õĳ�����100000��˵��String��ԭ�����ݺܳ�����StrA��һ��  
        private String str = new String(new char[100000]);  
          
        // ��Ȼʹ�����п��ܵ����ڴ�����Ĺ��캯������ȡ���ַ�������������������String����  
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
