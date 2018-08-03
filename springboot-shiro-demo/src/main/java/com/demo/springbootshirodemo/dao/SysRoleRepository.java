package com.demo.springbootshirodemo.dao;

import com.demo.springbootshirodemo.entity.SysRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysRoleRepository extends CrudRepository<SysRole,Integer> {
}
