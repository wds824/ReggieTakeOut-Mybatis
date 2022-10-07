package com.wds;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.alibaba.druid.sql.ast.SQLKeep;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
//        ops.set("zhangsan", "123");
//        ops.set("lisi", "123");
//        System.out.println(ops.get("zhangsan"));

//        RedisUtil.clearCache("ddd", template);
//        System.out.println(template.keys("*"));
    }

    @Autowired
    private DataSource dataSource;

    @Test
    void cleanTest() throws SQLException {
        ArrayList<String> files = new ArrayList<>();
//        Connection connection = sqlSession.getConnection();
//        Statement statement = connection.createStatement();
        DruidDataSource dataSource = (DruidDataSource) this.dataSource;
        DruidPooledConnection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select image from dish");
        while (resultSet.next()) {
            files.add(resultSet.getString(1));
        }


        System.out.println("files = " + files);
        sqlSession.close();
    }


}
