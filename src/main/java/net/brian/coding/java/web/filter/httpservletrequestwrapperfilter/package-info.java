/**
 * 
 * HttpServletRequest对象的参数是不可改变的。这极大地缩减了filter的应用范围。至少在一半的时间里，你希望可以改变准备传送给filter的对象
 * 如果在HttpServletRequest对象到达Struts的action  servlet之前，我们可以通过一个filter将用户输入的多余空格去掉，难道不是更美妙吗
 * 这样的话，你就不必等到在Struts的action表单验证方法中才进行这项工作了。幸运的是，尽管你不能改变不变对象本身，但你却可以通过使用装饰模式来改变其状态
 * 使用javax.servlet.http.HttpServletRequestWrapper类来装饰HttpServletRequest对象
 * 通过覆盖装饰类中此方法，你可以改变当前HttpServletRequest对象的状态
 * 要创建HttpServletRequest的装饰类，你需要继承HttpServletRequestWrapper并且覆盖你希望改变的方法
 * 也就是通过filter+HttpServletRequestWrapper结合的方式来“修改”HttpServletRequest对象
 * 有个很实际的需求：有段程序，使用Http的方式与合作商交互，而且是明文传输数据。
 * 我方的代码已经打包放在服务器上运行了很长时间，这时合作商突然要求修改数据传输的方式，要求加密后再传输，而我方的原有的代码不能改变，以防止引发其它问题。
 * 问：如何在不修改我方现有的代码的前提下，满足合作商的要求？可能大家都想到了，只要加上一个过滤器Filter不就可以了吗？
 * 事实就是这样的，采用Filter+HttpServletRequestWrapper就可以解决这个问题。
 * 首先：在filter中拦截到加密后的请求，将参数解密，然后组装成一个新的明文请求串。
 * 然后：重写HttpServletRequestWrapper中的getInputStream()方法，让其返回过滤器解析后的明文串即可。
 * 具体代码解释如下。首先我写了两个一摸一样的servlet，一个用来直接接收合作商的明文请求并打印；
 * 一个用来接收Filter处理后的合作商的请求并打印（Filter中将合作商加密后的参数解密再传给这个Servlet）。
 *
 */
package net.brian.coding.java.web.filter.httpservletrequestwrapperfilter;