package com.example.demo.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Student;
import com.github.pagehelper.Page;

@Repository
public interface StudentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);
    
    List<Student> selectAll();
    
    Page<Student> selectByPage();

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}