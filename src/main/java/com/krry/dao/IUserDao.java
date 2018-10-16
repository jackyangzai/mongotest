package com.krry.dao;

import com.krry.entity.User;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;

import java.io.File;
import java.io.OutputStream;
import java.util.List;


/**
 * @author
 */
public interface IUserDao {

    boolean saveFile2(File file, String fileName);
    boolean saveFile(File file, String fileName, String contentType, DBObject metaData);
    boolean writeToOutputStream(OutputStream out, GridFSDBFile gfsFile);

    GridFSDBFile findFileByName(String fileName);

    /**
     * 添加
     *
     * @param User
     */
    void addUser(User User);


    /**
     * 删除
     *
     * @param id
     */
    void removeUser(String id);


    /**
     * 保存或修改
     *
     * @param User
     */
    void saveOrUpdateUser(User User);


    /**
     * 根据id查询单个
     *
     * @param id
     * @return
     */
    User findById(String id);

    /**
     * 根据用户名查询
     *
     * @param username
     * @return
     */
    User findByUsername(String username);


    /**
     * 查询所有
     *
     * @return
     */
    public List<User> findAll();


}
