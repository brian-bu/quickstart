package net.brian.coding.algorithm.lintcode.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
/**
 * 
 * ��ĿҪ�󣺸���һ���ַ�������S���ҵ��������е������ַ���(Anagram)�����һ���ַ����������ַ�������ô������һ����ĸ������ͬ����˳��ͬ���ַ���Ҳ��S�С�
 * ע��������е��ַ�����ֻ����Сд��ĸ���ַ�������ַ�����Ҫ������������ 
 * ��Ŀ���ӣ�http://www.lintcode.com/zh-cn/problem/anagrams/
 * 
 * ˼·������ 
 * ����ȷ�������ַ����Ƿ����ͨ���ߵ�˳���Ϊ��ͬ���ַ����������ж����������֣�
 * a.���԰��ַ�����ɢ��char��������Ȼ��ת�����ַ���������ͱ���ַ���ת����Ľ����ͬ�����ظ��ַ�������������Ҫһ�������ͨ��hashmap��key����arraylist��contains�����ж��Ƿ��Ѿ��д��ڵ�key��Ԫ��
 * ������List������Set��ԭ����������п��ܳ���������ͬ���ַ���������Ӧ��һͬ����ӡ�����ս���ȷ��ÿ���ַ�����ֻ��ֺ�����һ�Σ�Ȼ����Խ���ֽ����Դ�ַ���һ�𷵻ػ򵥶���������һ��
 * b.ͨ��дһ���������ж������ַ����Ƿ�����Ȼ����������ÿһ���ַ��������������ַ�����ȿ��Ƿ���ͬ
 * �������ְ취ÿ�αȽϵ�ʱ����Ҫ���²���ַ���Ϊ���鲢��������һ���ַ������ܱ�������������Σ�Ҳ����ֻ�������һ��
 * 
 * �������Լ�дһ���ȽϷ�����������hashmap��key�ֻ���arraylist��contains����Щ����������;ͬ�飬������һ��Ԫ����Ϊ��׼�ͱ��Ԫ�رȽ�
 * ���������ַ�������һ����ͬ�����⣺����ѡ�еı������Ƚϵ��Ǹ���׼�ַ����޷���ӵ����ս����
 * ���Ҫ��ֻ��һ�������ַ�������ô���Կ�������һ��flag������ȶԽ������true�Ļ���֤���������϶���Ӧ�ó�����������У�Ȼ��ǰ����Ϊflag�����ͺ����Ԫ�رȽϣ�����ֱ�ӷŵ���������С�
 * ��������ж��������ַ����Ͳ��������ˣ���Ҫ���ö��ٸ�flag�Ź����أ�
 * 
 * ��ʱ�뵽�Ľ���취���Ƕ���һ��Set���������е������ַ�����key������һ�����Ž�Map�������ڱȽϵ�key-value��������ͨ��Set�е�keyȡ������
 * ���������������������������ݽṹ��������������Ԫ�ص�˳���ͱ�����˳��һ��
 * 
 * ʱ�临�Ӷȣ�
 *
 */
public class AnagramsFunction {
	/*
     * @param strs: A list of strings
     * @return: A list of strings
     */
    public List<String> anagrams(String[] strs) {
        // write your code here
    	if(strs == null) return Collections.emptyList();
    	List<String> result = new ArrayList<String>();
    	Map<String, String> flagMap = new HashMap<String, String>();
    	Set<String> flagSet = new HashSet<String>();
    	for(int i = 0; i < strs.length; i++) {
    		String str = strs[i];
    		if(str == null) continue;
    		char[] strChar = str.toCharArray();
    		Arrays.sort(strChar);
    		String resultStr = new String(strChar);
    		if(null != flagMap.get(resultStr)) {
    			flagSet.add(resultStr);
    		} else {
    			flagMap.put(resultStr, str);
    		}
    		if(flagSet.contains(resultStr)) result.add(str);
    	}
    	for(String key : flagSet) {
    		if(flagMap.get(key) != null) result.add(flagMap.get(key));
    	}
    	return result;
    }
    
    @Test
	public void testCase() {
    	System.out.println(anagrams(new String[] {"", ""}));
    	System.out.println(anagrams(new String[] {"lint","intl","inlt","code", null}));//  ["lint","inlt","intl"]
    	System.out.println(anagrams(new String[] {"lint", "lint","intl","inlt","code", null}));//  ["lint","lint","inlt","intl"]
    	System.out.println(anagrams(new String[] {"l int","in tl","i nlt","code"}));//  ["l int","i nlt","in tl"]
    	System.out.println(anagrams(new String[] {"l int","in tl","i nlt","code", "deco"}));//  ["l int","in tl","i nlt","code", "deco"]
	}
}
