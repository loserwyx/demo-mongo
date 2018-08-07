package com.shenma.demomongo;

import com.shenma.demomongo.bean.User;
import com.shenma.demomongo.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoMongoApplication.class)
public class TestMongo {

    @Autowired
    private UserDao userDao;

    /**
     * 插入一条记录
     */
    @Test
    public void TestSave() {
        User user = new User(1000L, "wangyx", 23, "男");
        userDao.insert(user);
        System.err.println("保存成功！");
    }

    /**
     * 查询全部User实体
     */
    @Test
    public void TestFindAll() {
        List<User> users = userDao.findAll();
        System.err.println("size: " + users.size() + "," + users.get(0).getName());
    }


    /**
     * 根据name查询User
     */
    @Test
    public void TestFindByName() {
        List<User> users = userDao.findByName("wangyx");
        System.err.println("size: " + users.size());
    }

    /**
     * 根据id删除对应User实体
     */
    @Test
    public void TestDelete() {
        userDao.delete(1000L);
        System.err.println("删除成功！");
    }

    /**
     * 删除集合
     */
    @Test
    public void TestDeleteCollection() {
        System.err.println("删除成功！");
    }
}
