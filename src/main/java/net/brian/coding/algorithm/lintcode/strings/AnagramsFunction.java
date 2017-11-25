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
 * 题目要求：给出一个字符串数组S，找到其中所有的乱序字符串(Anagram)。如果一个字符串是乱序字符串，那么他存在一个字母集合相同，但顺序不同的字符串也在S中。
 * 注意事项：所有的字符串都只包含小写字母。字符串里的字符不需要连续或者有序。 
 * 题目链接：http://www.lintcode.com/zh-cn/problem/anagrams/
 * 
 * 思路分析： 
 * 首先确定两个字符串是否可以通过颠倒顺序成为相同的字符串，具体判定方法有两种：
 * a.可以把字符串打散成char数组排序然后转换成字符串，如果和别的字符串转换后的结果相同就算重复字符串，这样就需要一遍遍历，通过hashmap的key或者arraylist的contains方法判定是否已经有存在的key或元素
 * 我们用List而不用Set的原因就是数组中可能出现两个相同的字符串，他们应该一同被打印到最终结果里，确保每个字符串都只拆分和排序一次，然后可以将拆分结果和源字符串一起返回或单独返回其中一个
 * b.通过写一个方法先判断两个字符串是否乱序，然后用数组中每一个字符串和其它所有字符串相比看是否相同
 * 但是这种办法每次比较的时候都需要重新拆分字符串为数组并进行排序，一个字符串可能被反复拆分排序多次，也可能只拆分排序一次
 * 
 * 无论是自己写一个比较方法，还是用hashmap的key抑或用arraylist的contains，这些方法都是殊途同归，即：用一个元素作为基准和别的元素比较
 * 但是这两种方法都有一个共同的问题：最先选中的被用来比较的那个基准字符串无法添加到最终结果里
 * 如果要是只有一组乱序字符串，那么可以考虑设置一个flag，如果比对结果返回true的话就证明它们俩肯定都应该出现在最后结果中，然后前者作为flag继续和后面的元素比较，后者直接放到最后结果集中。
 * 但是如果有多组乱序字符串就不能这样了，你要设置多少个flag才够用呢？
 * 
 * 暂时想到的解决办法就是多用一个Set，放置所有的乱序字符串的key，而第一个被放进Map里面用于比较的key-value对最后可以通过Set中的key取出来。
 * 但是这样的问题是用了三个数据结构，而且输出结果中元素的顺序会和遍历的顺序不一样
 * 
 * 时间复杂度：
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
