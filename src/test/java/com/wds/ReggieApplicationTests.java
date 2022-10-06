package com.wds;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class ReggieApplicationTests {
    @Autowired
    private SqlSession sqlSession;

    @Test
    void contextLoads() {

//        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
//        List<Employee> all = mapper.getAll();
//        System.out.println(all);
    }

    @Test
    void getByIdTest() {
//        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
//        Employee employee = mapper.getById(1L);
//        System.out.println("employee = " + employee);
    }

    @Test
    void IDTest() {
//        System.out.println(IdUtil.getSnowflakeNextId());
    }


    @Autowired
    private RedisTemplate<Object, Object> template;


    // redis test
    @Test
    void redisTest() {
//        ValueOperations<Object, Object> ops = template.opsForValue();
//        ops.set("zhangsan", "123",30000);
//        System.out.println(ops.get("zhangsan"));
    }


}
