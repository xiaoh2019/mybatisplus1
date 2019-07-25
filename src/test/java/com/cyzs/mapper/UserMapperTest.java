package com.cyzs.mapper;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cyzs.entity.User;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * UserMapper全部20个方法测试
 * insert       insertAllColumn                                      2
 * deleteById   deleteByMap           delete      deleteBatchIds     4
 * updateById   updateAllColumnById   update      updateForSet       4
 * selectById   selectBatchIds        selectByMap selectOne          10
 * selectCount  selectList  selectMaps  selectObjs  selectPage  selectMapsPage
 * @author xiaoH
 * @create 2019-07-25-1:04
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:app-context.xml")
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;


    /**根据条件，分页
     * SELECT id AS id,`name`,nick_name AS nickName,flag FROM user LIMIT 20,5
     */
    @Test
    public void selectPage(){
        RowBounds rowBounds = new RowBounds(20,5);
        EntityWrapper<User> wrapper = new EntityWrapper<User>();
        List<User> users = userMapper.selectPage(rowBounds, wrapper);
        System.out.println(users);
    }


    @Test
    public void selectList(){
        EntityWrapper<User> wrapper = new EntityWrapper<User>();
        wrapper.eq("name","西门庆");

        List<User> users = userMapper.selectList(wrapper);

        System.out.println(users.size()+"========");
    }

    /**
     *根据条件查询记录数
     * SELECT COUNT(1) FROM user WHERE (name = ?)
     */
    @Test
    public void selectCount(){
        EntityWrapper<User> userEntityWrapper = new EntityWrapper<User>();
        userEntityWrapper.eq("name","西门庆");
        Integer integer = userMapper.selectCount(userEntityWrapper);
        System.out.println(integer);
    }

    /**
     * 查询一条记录，如果有多条记录会报错
     * SELECT id AS id,`name`,nick_name AS nickName,flag FROM user WHERE nick_name=?
     */
    @Test
    public void selectOne(){
        User user = new User();
        user.setName("王二");
        User user1 = userMapper.selectOne(user);
        System.out.println(user1);
    }



    /** 根据map条件查询
     *SELECT id AS id,`name`,nick_name AS nickName,flag FROM user WHERE nick_name = ?
     */
    @Test
    public void selectByMap(){
        HashMap<String, Object> map = new HashMap<String, Object>();
        //键为数据库的字段
        map.put("nick_name","小二");
        List<User> users = userMapper.selectByMap(map);
        System.out.println(users);
    }

    /**
     * SELECT id AS id,`name`,nick_name AS nickName,flag FROM user WHERE id IN ( ? , ? )
     */
    @Test
    public void selectBatchIds(){

        List<User> users = userMapper.selectBatchIds(Arrays.asList(105, 104));
        System.out.println(users);
    }

    /**
     * 根据id查询
     * SELECT id AS id,`name`,nick_name AS nickName,flag FROM user WHERE id=?
     */
    @Test
    public void selectById(){
        User user = userMapper.selectById(106);
        System.out.println(user);
    }



    /**
     *
     */
    @Test
    public void updateForSet(){

        //userMapper.updateForSet()
    }


    /**
     *
     */
    @Test
    public void update(){
        User user = new User();
        user.setName("西门庆");
        EntityWrapper<User> wrapper = new EntityWrapper<User>();
        wrapper.eq("id",407);
        userMapper.update(user,wrapper);
    }

    /**
     * 全部属性更新，未设置的设为null
     * UPDATE user SET `name`=null,nick_name='王四',flag=null WHERE id=106
     */
    @Test
    public void updateAllColumnById(){
        User user = new User();
        user.setId(407);
        user.setNickName("王四");
        user.setVersion(1);
        userMapper.updateAllColumnById(user);
    }

    /**
     * 只更新有的属性
     * UPDATE user SET nick_name=? WHERE id=?
     */
    @Test
    public void updateById(){
        User user = new User();
        user.setId(407);
        user.setNickName("王四");
        user.setName("王青山");
        Integer integer = userMapper.updateById(user);
        System.out.println(integer);

    }


    /**
     * 批量删除
     */
    @Test
    public void deleteBatchIds(){
        userMapper.deleteBatchIds(Arrays.asList(100,101,102));
    }

    /**
     *根据条件删除
     */
    @Test
    public void delete(){
        EntityWrapper wrapper = new EntityWrapper();

        userMapper.delete(wrapper);
    }

    /**
     * 根据map删除
     */
    @Test
    public void deleteByMap(){
        HashMap<String, Object> map = new HashMap();
        map.put("id",105);

        userMapper.deleteByMap(map);
    }

    /**
     * 根据Id删除
     */
    @Test
    public void deleteById(){
        Integer integer = userMapper.deleteById(106);
        System.out.println(integer);
    }

    /**
     * insert插入一条数据
     */
    @Test
    public void insert(){
        User user = new User();
        user.setName("王二");
        user.setNickName("小王");
        for(int i=0;i<200;i++){

            userMapper.insert(user);
        }

    }

    /**
     * 和insert区别不大，但是sql上会把所有属性插入
     */
    @Test
    public void insertAllColumn(){
        User user = new User();
        user.setName("王三");
        //user.setFlag(1);
        userMapper.insertAllColumn(user);
    }



}