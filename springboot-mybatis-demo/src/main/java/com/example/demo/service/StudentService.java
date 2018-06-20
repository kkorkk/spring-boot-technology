package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.mapper.StudentMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class StudentService {

	@Autowired 
	StudentMapper studentMapper;
	
	public void save(Student stu) {
		studentMapper.insertSelective(stu);
	}
	
	public void delete(int id) {
		studentMapper.deleteByPrimaryKey(id);
	}
	
	public void update(Student stu) {
		studentMapper.updateByPrimaryKey(stu);
	}
	
	public Student selectByPrimaryKey(int id) {
		return studentMapper.selectByPrimaryKey(id);
	}
	
	public Page<Student> selectByPage(int pageNum,int pageSize){
		PageHelper.orderBy("id DESC");
		PageHelper.startPage(pageNum, pageSize);
		return studentMapper.selectByPage();
	}
	
	public List<Student> selectAll(){
		return studentMapper.selectAll();
	}
	
}
