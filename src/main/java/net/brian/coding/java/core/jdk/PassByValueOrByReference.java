package net.brian.coding.java.core.jdk;

/**
 * Java一直是值传递。不幸的是，他们决定把指针叫做引用，因此新人总是被搞晕。因为这些引用也是通过值传递的
 * 
 * Java中只有按值传递，没有按引用传递。值传递还是引用传递多见于调用方法的时候传参的情况
 * 值传递的精髓是：传递的是存储单元中的内容，而非地址或者引用，即：
 * 方法外部声明的对象在传递给方法时，该对象的内容可以在被调用的方法中改变
 * 但该对象的引用(不是引用的副本)是永远不会改变的。比如 StringBuffer buffer = new StringBuffer("original");
 * 这个引用buffer在调用方法前后始终指向new StringBuffer("original")
 *
 */
public class PassByValueOrByReference {

	public static void main(String[] args) {
		// 调用该函数
		int num = 5;
		System.out.println(num);
		changePrimitive(num);
		System.out.println(num);

		StringBuffer buffer = new StringBuffer("original");
		// 输出的永远都是原对象new StringBuffer("original")的值
		// 传进去的buffer指向了一个新的对象但原对象没变化的
		changePointer(buffer);
		System.out.println(buffer);
		// 输出的永远都是原对象new StringBuffer("original")的值
		// 传进去的buffer还是指向原对象并通过append改变了其值
		changeOriginal(buffer);
		System.out.println(buffer);
	}

	/**
	 * 这里一共在内存中开辟了两块存储空间：
	 * 一块存方法调用的时候传进来的实参num，一块存方法压入栈的时候声明的形参int x 
	 * 这里无论怎么改x都不会影响到原来num的值
	 * 所以如果在方法外部调用num，那无论调用方法之前还是之后都不会改变num的值
	 * 
	 * 在方法参数上加final，并不能确保num不会被改变，而是java的值传递的机制保证了num在调用方法前后根本不会变
	 * 在方法形参上加final关键字，只是保证方法形参从调用方法开始赋值之后就不可重新赋值
	 * 
	 * 这让我想起一句话：final修饰的基本类型的变量一经初始化就不能再赋值了，引用类型不可以改变引用但是可以改变对象本身
	 * 但是基本类型本身就是一个值，如果改变这个值，就相当于改变了引用，比如Integer为1和为2的时候指向的是不同的对象
	 * 
	 * 但是如果这个形参最终还是会随着方法执行结束而消亡，如果不把它返回给方法调用方或者对方法的最后返回值产生影响那么就没必要设置为final
	 * 见下面这个示例：
	 * @see net.brian.coding.java.core.jdk.keywords.FinalClass.testFinalParam(int)
	 */
	public static void changePrimitive(int x) {
		x = x * 2;
	}

	/**
	 * 传递过去的引用进行操作使得原来的对象发生了变化，这时方法相当于没改变引用的指向的前提下改变了对象
	 * 相当于changeOriginal(buffer) --> StringBuffer bufferStr = buffer --> bufferStr.append(" pointer");
	 * 
	 * 这让我想起一句话：final修饰的引用变量一经初始化就不能改变引用对象了，但是对象本身可以改变 
	 */
	public static void changeOriginal(StringBuffer bufferStr) {
		bufferStr.append(" pointer");
	}

	/**
	 * 传递过去的引用指向了新的对象，那么原来的对象还是存在的且不变，调用方法前后原来的对象没有任何变化
	 * 相当于changeOriginal(buffer) --> StringBuffer bufferStr = buffer --> bufferStr = new StringBuffer("new");
	 * 也就是说方法被压入栈的时候有bufferStr和buffer两个引用同时指向了 new StringBuffer("original")这个对象
	 * 这个过程有点像：String a = "hello"; a = "world";虽然a的值改变了，但是常量池中"hello"和"world"两个对象是不变的
	 * a只不过是改变了对象的引用，并没有改变内存中对象的值
	 * 因此对于本方法：
	 * 在调用方法之后再次使用该对象和没调用该方法时一样，这里方法仅相当于改变了原有对象的引用，并没有改变原有的对象
	 */
	public static void changePointer(StringBuffer bufferStr) {
		bufferStr = new StringBuffer("new");
	}

}