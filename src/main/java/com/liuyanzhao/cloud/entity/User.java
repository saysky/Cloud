package com.liuyanzhao.cloud.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

import java.io.Serializable;



/**
 * @author 言曌
 * @date 2018/3/19 下午9:54
 */

@Entity
@Table(name = "user")
@Getter
@Setter
public class User implements  Serializable {

    private static final long serialVersionUID = 6147345506206285446L;

    @Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    private Integer id; // 用户的唯一标识

    private String username;

    private String password;
}