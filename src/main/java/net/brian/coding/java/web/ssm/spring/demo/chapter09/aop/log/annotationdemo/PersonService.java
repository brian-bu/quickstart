package net.brian.coding.java.web.ssm.spring.demo.chapter09.aop.log.annotationdemo;

public interface PersonService {

	public void save(String name);

	public void update(String name, Integer id);

	public String getPersonName(Integer id);

}
