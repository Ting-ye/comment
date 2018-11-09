package org.dy.controller.content;

import org.dy.constant.PageCodeEnum;
import org.dy.dto.AdDto;
import org.dy.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ad")
public class AdController {
    @Autowired
    private AdService adService;

    @RequestMapping
    public String init(){
        return "content/adList";
    }


    /**
     * 新增页初始化
     */
    @RequestMapping("/addInit")
    public String addInit() {
        return "/content/adAdd";
    }

    /**
     * 新增
     */
    @RequestMapping("/add")
    public String add(AdDto adDto, Model model){
        if(adService.add(adDto)){
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.ADD_SUCCESS);
        }else{
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.ADD_FAIL);
        }
        return "/content/adAdd";
    }

    /**
    * 查询
    */
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    @ResponseBody
    public String search(Model model,AdDto adDto){

        return "/content/adList";
    }
}
