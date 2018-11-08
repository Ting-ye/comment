package org.dy.controller.content;

import org.dy.dto.AdDto;
import org.dy.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ad")
public class AdController {
    @Autowired
    private AdService adService;

    @RequestMapping
    public String init(){
        return "content/adList";
    }


    @RequestMapping("/add")
    public String add(AdDto adDto){
        adService.add(adDto);
        return "/content/adAdd";
    }
}
