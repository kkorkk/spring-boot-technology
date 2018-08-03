package com.demo.springbootshirodemo.dao;

import com.demo.springbootshirodemo.entity.SysPermission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysPermissionRepository extends CrudRepository<SysPermission,Integer> {
}
