package com.demo.springbootshirodemo.dao;

import com.demo.springbootshirodemo.entity.UserInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.Mapping;

@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo,Integer> {

    UserInfo findByUsername(String username);

    @Query("select max(u.uid) from UserInfo u")
    Integer getMaxId();

}
