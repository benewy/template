package com.beneway.basic.system.token.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TokenDao {

    String getToken(@Param("type") String type);

    void update(@Param("token") String token, @Param("type") String type);

    void insert(@Param("token") String token, @Param("type") String type);
}
