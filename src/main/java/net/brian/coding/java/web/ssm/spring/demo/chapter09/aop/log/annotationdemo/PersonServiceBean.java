package net.brian.coding.java.web.ssm.spring.demo.chapter09.aop.log.annotationdemo;

public class PersonServiceBean implements PersonService {

	@Override
	public void save(String name) {

		System.out.println("����save����");
		// throw new RuntimeException();
	}

	@Override
	public void update(String name, Integer id) {

		System.out.println("����update()����");
	}

	@Override
	public String getPersonName(Integer id) {

		System.out.println("����getPersonName()����");
		return "xxx";
	}

}
