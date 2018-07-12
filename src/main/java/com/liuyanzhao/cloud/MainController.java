package com.liuyanzhao.cloud;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 言曌
 * @date 2018/7/12 18:26
 */
@Controller
public class MainController {

    @GetMapping
    public String index() {
        return "index";
    }
}
