package net.brian.coding.java.web.ssm.studentdemo.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.brian.coding.java.web.ssm.studentdemo.dao.StudentDao;
import net.brian.coding.java.web.ssm.studentdemo.domain.Student;

@Controller
@RequestMapping(value="student")

public class StudentController {
	@Resource StudentDao dao;
	//查询学生信息
	@RequestMapping(value="query")
	public ModelAndView query(){
		List<Student> student = dao.find();
		ModelAndView model = new ModelAndView();
		model.addObject("student",student);
		model.setViewName("student");
		return model;
	}
	//插入
	@RequestMapping(value="insert")
	public String insert(Student student){
		dao.doInsert(student);
		return "redirect:/student/query";
		//return new ModelAndView("redirect:/query");
	}
	//删除
	@RequestMapping(value="delete")
	@ResponseBody
	public String delete(@RequestParam(value="id")String id){
		dao.delById(Integer.parseInt(id));
		return "ok";
	}
}
