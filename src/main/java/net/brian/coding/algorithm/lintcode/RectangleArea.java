package net.brian.coding.algorithm.lintcode;
/**
 * 
 * http://www.lintcode.com/zh-cn/problem/rectangle-area/
 * 
 * 这道题太简单了，没什么可以解释的，甚至连单元测试都不需要。
 *
 */
public class RectangleArea {
	/*
	 * Define two public attributes width and height of type int.
	 */
	// write your code here
	public final int width;
	public final int height;

	/*
	 * Define a constructor which expects two parameters width and height here.
	 */
	// write your code here
	public RectangleArea(int width, int height) {
		this.width = width;
		this.height = height;
	}

	/*
	 * Define a public method `getArea` which can calculate the area of the
	 * rectangle and return.
	 */
	// write your code here
	public int getArea() {
		return width * height;
	}
}
