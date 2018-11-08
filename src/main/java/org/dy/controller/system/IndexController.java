package org.dy.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexController {
    @RequestMapping
    public String init(){
        return "/system/index";
    }
    @RequestMapping("/adAdd")
    public String adList(){
        return "/content/adAdd";
    }

}
