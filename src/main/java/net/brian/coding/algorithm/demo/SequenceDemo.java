package net.brian.coding.algorithm.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Before;
import org.junit.Test;

public class SequenceDemo {
	List<Integer> lists = new ArrayList<Integer>();
	List<Integer> mark = null;
	
	@Before
	public void prepareForFinal() {
		for(int i = 0; i < 100; i++) {
			lists.add(i+1);
		}
		System.out.println("Before -- lists.size():: " + lists.size());
	}
	
	@Test
	public void getFinal() {
		while(lists.size() > 6) {
			lists = removeThirds();
		}
		removeTheLastOnes();
		System.out.println("Test -- lists.size():: " + lists.size());
		System.out.println("Test -- lists.get(1):: " + lists.get(1));
	}
	
	private void removeTheLastOnes() {
		
	}
	
	private List<Integer> removeThirds() {
		mark = new ArrayList<Integer>();
		for(int i = 0; i < lists.size(); i++){
			if(i % 3 == 2) {
				System.out.println("To be removed:: " + lists.get(i));
			} else {
				mark.add((Integer) lists.get(i));
			}
		}
		return mark;
	}
}
