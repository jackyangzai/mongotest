package com.krry.dao.impl;

import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.krry.dao.IUserDao;
import com.krry.entity.User;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @author
 */
@Repository("userDao")
public class UserDaoImpl implements IUserDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MongoDbFactory mongoDbFactory;



    public boolean saveFile(File file, String fileName, String contentType, DBObject metaData) {
        GridFSInputFile gfsInput;
        DB db = mongoDbFactory.getDb();
        try {
            gfsInput = new GridFS(db, "fs").createFile(file);
            gfsInput.setFilename(fileName);
            gfsInput.setContentType(contentType);
            gfsInput.setMetaData(metaData);
            gfsInput.save();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public boolean saveFile2(File file, String fileName) {
        GridFSInputFile gfsInput;
        DB db = mongoDbFactory.getDb();
        try {
            gfsInput = new GridFS(db, "fs").createFile(file);
            gfsInput.setFilename(fileName);
            gfsInput.save();
        } catch (IOException e) {
            return false;
        }
        return true;
    }


    //获取文件并输出到本地
    public GridFSDBFile findFileByName(String fileName) {
        GridFSDBFile gfsFile;
        DB db = mongoDbFactory.getDb();
        try {
            gfsFile = new GridFS(db, "fs").findOne(fileName);
        } catch (Exception e) {
            return null;
        }
        return gfsFile;
    }


    public boolean writeToOutputStream(OutputStream out, GridFSDBFile gfsFile) {
        try {
            gfsFile.writeTo(out);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public void addUser(User User) {
        //1.如果没有指定集合，则默认添加到和对象名称相同的集合中，没有则创建一个
        //2.也可以指定集合 mongoTemplate.save(User, "User_db");
        mongoTemplate.save(User);
    }

    public void removeUser(String id) {

        User User = findById(id);
        mongoTemplate.remove(User);
    }

    public void saveOrUpdateUser(User User) {

        mongoTemplate.save(User);
    }

    public User findById(String id) {

        return mongoTemplate.findById(id, User.class);
    }

    public List<User> findAll() {

        return mongoTemplate.findAll(User.class);
    }

    public User findByUsername(String username) {
        //根据username查询
        Query sql = new Query(Criteria.where("username").is(username));

        return mongoTemplate.findOne(sql, User.class);
    }
}