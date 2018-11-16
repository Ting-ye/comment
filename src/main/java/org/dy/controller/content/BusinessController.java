package org.dy.controller.content;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.dy.dto.BusinessDto;
import org.dy.service.BusinessService;
import org.dy.service.DicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/business")
public class BusinessController {

    @Resource
    private DicService dicService;

    @Resource
    private BusinessService businessService;

//    @RequestMapping(method = RequestMethod.POST)
//    public String search(Model model, BusinessDto dto){
//        List<BusinessDto> list=businessService.searchByPage(dto);
//        model.addAttribute("list",list);
//        model.addAttribute("searchParam",dto);
//        return "content/businessList";
//    }
    /**
     * 商户列表
     */
    @RequestMapping(value ="/{pn}",method = RequestMethod.GET)
    public String init(@PathVariable Integer pn, Model model){
        if(pn<=0 ||pn ==null||pn.equals(""))
            pn=1;
        PageHelper.startPage(pn,5);
        List<BusinessDto> adList=businessService.searchByPage(new BusinessDto());
        PageInfo pageInfo= new PageInfo(adList);
        model.addAttribute("adlist",adList);
        model.addAttribute("pagenum",pageInfo.getTotal());
        return "content/businessList";
    }
    /*分页查询*/
    @RequestMapping(value ="/getlist/{pn}",
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public List<BusinessDto> getList(@PathVariable Integer pn){
        if(pn<=0 ||pn ==null)
            pn=1;
        PageHelper.startPage(pn,5);
        List<BusinessDto> adList=businessService.searchByPage(new BusinessDto());
        PageInfo pageInfo= new PageInfo(adList);
        return adList;
    }
}
