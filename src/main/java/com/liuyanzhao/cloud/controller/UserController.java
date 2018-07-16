package com.liuyanzhao.cloud.controller;

import com.alibaba.fastjson.JSON;
import com.liuyanzhao.cloud.entity.User;
import com.liuyanzhao.cloud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author 言曌
 * @date 2018/7/16 10:31
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * 获得用户列表
     *
     * @return
     */
    @GetMapping("/user")
    public String getUserList() {
        return JSON.toJSONString(userRepository.findAll());
    }


    /**
     * 根据ID查询用户
     * @param id
     * @param response
     * @return
     */
    @GetMapping("/user/{id}")
    public String getUser(@PathVariable("id") Integer id, HttpServletResponse response) {
        User user = userRepository.findOne(id);
        if (user != null) {
            return JSON.toJSONString(userRepository.findOne(id));
        } else {
            response.setStatus(404);
            return JSON.toJSONString("用户不存在!");
        }
    }

    /**
     * 添加新用户
     * @param user
     * @param response
     * @return
     */
    @PostMapping("/user")
    public String saveUser(@RequestBody User user, HttpServletResponse response) {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            response.setStatus(500);
            return "添加失败！";
        }
        return JSON.toJSONString("用户添加成功！");
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @PutMapping("/user")
    public String updateUser(@RequestBody User user) {
        return JSON.toJSONString(userRepository.save(user));
    }

    /**
     * 删除用户
     * @param id
     * @param response
     * @return
     */
    @DeleteMapping("/user/{id}")
    public String updateUser(@PathVariable("id") Integer id, HttpServletResponse response) {
        try {
            userRepository.delete(id);
        } catch (Exception e) {
            response.setStatus(404);
            return JSON.toJSONString("用户不存在！");
        }
        return JSON.toJSONString("删除成功！");
    }


}
