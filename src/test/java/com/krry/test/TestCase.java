package com.krry.test;

import com.krry.dao.IUserDao;
import com.krry.dao.impl.UserDaoImpl;
import com.krry.entity.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.util.List;

public class TestCase {
    ClassPathXmlApplicationContext ctx;

    @Before
    public void init() {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml", "springmvc.xml");
    }


    @Test
    public void test() {
        IUserDao userDao = ctx.getBean("userDao", UserDaoImpl.class);
        userDao.removeUser("5bc550064a42ac3100173b3b");
        List<User> list = userDao.findAll();
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void test01() {
        IUserDao userDao = ctx.getBean("userDao", UserDaoImpl.class);
        User user = new User("jack", "5555", "20180101");
        userDao.addUser(user);
    }

    @Test
    public void test02() throws IOException {
        IUserDao userDao = ctx.getBean("userDao", UserDaoImpl.class);
        GridFSDBFile fsdbFile = userDao.findFileByName("test2.xlsx");
        File f = new File("d:" + File.separator + "test3.xlsx");    // 声明File对象
        OutputStream out = new FileOutputStream(f);
        userDao.writeToOutputStream(out,fsdbFile);
        out.flush();
        out.close();
    }

    @Test
    public void test03(){
        IUserDao userDao = ctx.getBean("userDao", UserDaoImpl.class);
        File  file = new File("d:"+File.separator+"2.jpg");
        DBObject metadata = new BasicDBObject("userId", "1001");
        userDao.saveFile(file,"22.png","image/png",metadata);
    }

    @Test
    public void test04(){
        IUserDao userDao = ctx.getBean("userDao", UserDaoImpl.class);
        File file = new File("d:"+File.separator+"test.xlsx");
        userDao.saveFile2(file,"test2.xlsx");
    }
}
