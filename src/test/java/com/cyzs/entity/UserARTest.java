package com.cyzs.entity;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * ActiveRecord 测试
 * @author xiaoH
 * @create 2019-07-25-15:38
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:app-context.xml")
public class UserARTest {

    /**
     * INSERT INTO user ( `name`, nick_name, flag ) VALUES ( ?, ?, ? )
     */
    @Test
    public void insert(){
        User user = new User();
        user.setName("王二");
        user.setNickName("小王");
        user.setFlag(1);
        boolean insert = user.insert();
        System.out.println(insert);
    }

    /**要配置分页插件，不然是假分页，配置之后
     * SELECT id AS id,`name`,nick_name AS nickName,flag FROM user LIMIT 0,5    分页后的数据 5 1 2147483647 0 21 102
     *
     * 没配分页插件  SELECT id AS id,`name`,nick_name AS nickName,flag FROM user             5 1 5 0 0 0
     */
    @Test
    public void selectPage(){
        User user = new User();
        Page<User> page = user.selectPage(new Page<User>(1, 5), new EntityWrapper<User>());
        //当前页
        page.getCurrent();
        page.getLimit();
        //limit的起始位置
        page.getOffset();
        //总页数
        page.getPages();
        //总记录数
        page.getTotal();
        //是否有下一页
        page.hasNext();
        //是否有上一页
        page.hasPrevious();

        System.out.println(page.getSize()+" "+page.getCurrent()+" "+page.getLimit()
                +" "+page.getOffset()+" "+page.getPages()+" "+page.getTotal());

    }

    /**
     * 防止全表操作测试,测试失败
     */
    @Test
    public void sqlExplainInterceptorTest(){
        User user = new User();
        user.delete(new EntityWrapper());
    }

    /**
     * 测试乐观锁插件
     * UPDATE user SET `name`=?, nick_name=?, version=? WHERE id=? and version=?
     */
    @Test
    public void OptimisticLockerInterceptor(){
        User user = new User();
        user.setName("张三");
        user.setId(407);
        //必须要有，就是根据这个判定的
        user.setVersion(2);
        user.updateById();
    }

    /** 删除变成更新
     * 逻辑删除测试
     * UPDATE user SET deleted=0 WHERE id=?
     */
    @Test
    public void LogicSqlInjector(){
        User user = new User();
        user.deleteById(407);

    }

}