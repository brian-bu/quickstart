/**
 * 
 * HttpServletRequest����Ĳ����ǲ��ɸı�ġ��⼫���������filter��Ӧ�÷�Χ��������һ���ʱ�����ϣ�����Ըı�׼�����͸�filter�Ķ���
 * �����HttpServletRequest���󵽴�Struts��action  servlet֮ǰ�����ǿ���ͨ��һ��filter���û�����Ķ���ո�ȥ�����ѵ����Ǹ�������
 * �����Ļ�����Ͳ��صȵ���Struts��action����֤�����вŽ���������ˡ����˵��ǣ������㲻�ܸı䲻�����������ȴ����ͨ��ʹ��װ��ģʽ���ı���״̬
 * ʹ��javax.servlet.http.HttpServletRequestWrapper����װ��HttpServletRequest����
 * ͨ������װ�����д˷���������Ըı䵱ǰHttpServletRequest�����״̬
 * Ҫ����HttpServletRequest��װ���࣬����Ҫ�̳�HttpServletRequestWrapper���Ҹ�����ϣ���ı�ķ���
 * Ҳ����ͨ��filter+HttpServletRequestWrapper��ϵķ�ʽ�����޸ġ�HttpServletRequest����
 * �и���ʵ�ʵ������жγ���ʹ��Http�ķ�ʽ������̽��������������Ĵ������ݡ�
 * �ҷ��Ĵ����Ѿ�������ڷ������������˺ܳ�ʱ�䣬��ʱ������ͻȻҪ���޸����ݴ���ķ�ʽ��Ҫ����ܺ��ٴ��䣬���ҷ���ԭ�еĴ��벻�ܸı䣬�Է�ֹ�����������⡣
 * �ʣ�����ڲ��޸��ҷ����еĴ����ǰ���£���������̵�Ҫ�󣿿��ܴ�Ҷ��뵽�ˣ�ֻҪ����һ��������Filter���Ϳ�������
 * ��ʵ���������ģ�����Filter+HttpServletRequestWrapper�Ϳ��Խ��������⡣
 * ���ȣ���filter�����ص����ܺ�����󣬽��������ܣ�Ȼ����װ��һ���µ��������󴮡�
 * Ȼ����дHttpServletRequestWrapper�е�getInputStream()���������䷵�ع���������������Ĵ����ɡ�
 * �������������¡�������д������һ��һ����servlet��һ������ֱ�ӽ��պ����̵��������󲢴�ӡ��
 * һ����������Filter�����ĺ����̵����󲢴�ӡ��Filter�н������̼��ܺ�Ĳ��������ٴ������Servlet����
 *
 */
package net.brian.coding.java.web.filter.httpservletrequestwrapperfilter;