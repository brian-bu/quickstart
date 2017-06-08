package net.brian.coding.java.web.ssm.studentdemo.dao;
import org.springframework.stereotype.Service;

import net.brian.coding.java.web.ssm.studentdemo.domain.Student;

import java.util.List;

@Service
public interface StudentDao {
	public Student findById();
	public List<Student> find();
	public void doInsert(Student student);
	public void doUpdate(Student student);
	public void delById(int id);
}
