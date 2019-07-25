package com.cyzs.test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author xiaoH
 * @create 2019-06-16-15:31
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:app-context.xml")
public class MybatisPlusTest01 {

    @Autowired
    DataSource dataSource;

    /**
     * 第一个测试，测试搭的环境是否正确,看看能否连接到数据库
     */
    @Test
    public void first() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

}

