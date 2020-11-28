package com.linzch3.step01;

import com.linzch3.step01.mapper.RoleMapper;
import com.linzch3.step01.pojo.Role;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class Step01Test {

  @Test
  public void execute(){
    String resource = "com/linzch3/step01/resources/mybatis-config.xml";
    InputStream inputStream = null;
    try {
      inputStream = Resources.getResourceAsStream(resource);
    } catch (IOException e) {
      e.printStackTrace();
    }
    SqlSessionFactory sqlSessionFactory;
    sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    SqlSession sqlSession = null;
    try {
      sqlSession = sqlSessionFactory.openSession();
      RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
      Role role = roleMapper.getRole(1L);
      log.info("get role: {}", role);
      assertEquals(1L, role.getId());
      assertEquals("张三", role.getRoleName());
      assertEquals("张三的备注", role.getNote());
      sqlSession.commit();

    } catch (Exception e) {
      if (sqlSession != null) {
        sqlSession.rollback();
      }
      e.printStackTrace();
    } finally {
      if (sqlSession != null) {
        sqlSession.close();
      }
    }
  }
}
