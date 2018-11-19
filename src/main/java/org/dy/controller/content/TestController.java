package org.dy.controller.content;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.dy.bean.Business;
import org.dy.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    private BusinessService businessService;





    @RequestMapping("/akali/test")
    public String getBusinessList(Integer pn){
        if(pn<=0 ||pn ==null)
        pn=1;
        PageHelper.startPage(pn,5);
        List<Business> list=businessService.getBusinessListTest();
        PageInfo pageInfo= new PageInfo(list);
        System.out.println(pageInfo.toString());
        return "ss";
    }

    @RequestMapping("/akali/test2")
    @ResponseBody
    public List<Business> getBusinessList2(Integer pn){
        if(pn<=0 ||pn ==null)
            pn=1;
        PageHelper.startPage(pn,5);
        List<Business> list=businessService.getBusinessListTest();
        PageInfo pageInfo= new PageInfo(list);
        System.out.println(pageInfo.toString());
        return list;
    }
}
