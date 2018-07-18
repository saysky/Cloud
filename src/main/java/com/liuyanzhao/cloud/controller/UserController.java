package com.liuyanzhao.cloud.controller;

import com.alibaba.fastjson.JSON;
import com.liuyanzhao.cloud.entity.User;
import com.liuyanzhao.cloud.repository.UserRepository;
import com.liuyanzhao.cloud.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

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
    public List<User> getUserList() {
        return userRepository.findAll();
    }


    /**
     * 根据ID查询用户
     *
     * @param id
     * @param response
     * @return
     */
    @GetMapping("/user/{id}")
    public ResponseEntity<ResultVO> getUser(@PathVariable("id") Integer id, HttpServletResponse response) {
        User user = userRepository.findOne(id);
        if (user != null) {
            HashMap<String,User> map = new HashMap<>();
            map.put("user",user);
            return ResponseEntity.ok().body(new ResultVO(0, "操作成功", map));
        } else {
            response.setStatus(404);
            return ResponseEntity.ok().body(new ResultVO(10001, "用户不存在"));
        }
    }

    /**
     * 添加新用户
     *
     * @param user
     * @param response
     * @return
     */
    @PostMapping("/user")
    public ResponseEntity<ResultVO> saveUser(@RequestBody User user, HttpServletResponse response) {
        User result = null;
        try {
            result = userRepository.save(user);
        } catch (Exception e) {
            response.setStatus(500);
            ResponseEntity.ok().body(new ResultVO(10003, "添加失败！"));
        }
        HashMap<String,User> map = new HashMap<>();
        map.put("user",result);
        return ResponseEntity.ok().body(new ResultVO(0, "操作成功！", map));
    }

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    @PutMapping("/user/{id}")
    public ResponseEntity<ResultVO> updateUser(@PathVariable("id") Integer id, @RequestBody User user, HttpServletResponse response) {
        User originalUser = userRepository.findOne(id);
        if(originalUser == null) {
            response.setStatus(404);
            return ResponseEntity.ok().body(new ResultVO(10001, "用户不存在"));
        }
        User result = null;
        try {
            originalUser.setUsername(user.getUsername());
            originalUser.setPassword(user.getPassword());
            result = userRepository.save(user);
        } catch (Exception e) {
            response.setStatus(500);
            ResponseEntity.ok().body(new ResultVO(10003, "更新失败！"));
        }
        HashMap<String,User> map = new HashMap<>();
        map.put("user",result);
        return ResponseEntity.ok().body(new ResultVO(0, "操作成功！", map));
    }

    /**
     * 删除用户
     *
     * @param id
     * @param response
     * @return
     */
    @DeleteMapping("/user/{id}")
    public ResponseEntity<ResultVO> deleteUser(@PathVariable("id") Integer id, HttpServletResponse response) {
        try {
            userRepository.delete(id);
        } catch (Exception e) {
            response.setStatus(404);
            return ResponseEntity.ok().body(new ResultVO(10001,"用户不存在！"));
        }
        HashMap<String,Integer> map = new HashMap<>();
        map.put("id",id);
        return ResponseEntity.ok().body(new ResultVO(0,"请求成功",map));
    }


}
