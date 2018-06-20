package com.example.demo;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import com.github.pagehelper.Page;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentTest {

	@Autowired 
	StudentService studentService;
	
	@Test
	public void testSave() {
		for(int i=0;i<10;i++) {
			Student stu = new Student();
			stu.setBirthday(new Date());
			stu.setName("mybatis-name");
			stu.setPassword("123");
			studentService.save(stu);
			System.out.println("添加成功！添加的数据为:"+stu.toString());
		}
	}
	
	@Test
	public void testDelete() {
		studentService.delete(1);
	}
	
	@Test
	public void testUpdate() {
		Student stu = new Student();
		stu.setBirthday(new Date());
		stu.setName("mybatis-name");
		stu.setPassword("123");
		studentService.save(stu);
		System.out.println("-------------------------修改前的stu:"+stu.toString());
		stu.setName("修改后的name");
		System.out.println("-------------------------修改后的stu"+stu.toString());
	}
	
	@Test
	public void testSelectAll() {
		List<Student> stuList = studentService.selectAll();
		for(Student stu:stuList) {
			System.out.println(stu.toString());
		}
	}
	
	@Test
	public void testSelect() {
		Student stu = studentService.selectByPrimaryKey(2);
		System.out.println(stu.toString());
	}
	
	@Test
	public void testSelectByPage() {
		Page<Student> stuList = studentService.selectByPage(2,3);
		for(Student stu : stuList) {
			System.out.println(stu.toString());
		}
	}
}
