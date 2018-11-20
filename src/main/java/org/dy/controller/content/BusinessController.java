package org.dy.controller.content;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.dy.bean.Business;
import org.dy.constant.DicTypeConst;
import org.dy.constant.PageCodeEnum;
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

    @RequestMapping
    public String init(Model model){
        return "content/businessList";
    }
    /**
     * 商户列表
     */
    @RequestMapping(value ="/{pn}",method = RequestMethod.GET)
    public String init(@PathVariable Integer pn, Model model){
        if(pn<=0 ||pn ==null)
            pn=1;
        PageHelper.startPage(pn,5);
        List<Business> bsList=businessService.searchByPage(new BusinessDto());
        PageInfo pageInfo= new PageInfo(bsList);
        List<BusinessDto> list=  businessService.searchByPageHelper(bsList);
        model.addAttribute("bsList",list);
        model.addAttribute("pagenum",pageInfo.getPages());
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
        List<Business> bsList=businessService.searchByPage(new BusinessDto());
        PageInfo pageInfo= new PageInfo(bsList);
        return  businessService.searchByPageHelper(bsList);
    }
    /**
     模糊查询*/
    @RequestMapping(value = "search/{param}")
    @ResponseBody
    public List<BusinessDto> search(@PathVariable(value = "param") String title){
        BusinessDto businessDto=new BusinessDto();
        if(!title.equals("BACD6F4771952C9C5D254DE71C485B05"))
            businessDto.setTitle(title);
        int pn=1;
        PageHelper.startPage(pn,5);
        List<Business> bsList=businessService.searchByPage(businessDto);
        PageInfo pageInfo=new PageInfo(bsList);
        return businessService.searchByPageHelper(bsList);
    }

    /**删除用户*/
    @RequestMapping(value = "/remove/{id}",method = RequestMethod.DELETE)
    public String remove(@PathVariable Long id,Model model){
        if(businessService.remove(id)){
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.REMOVE_SUCCESS);
        }else{
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.REMOVE_FAIL);
        }
        return "redirect:/business/1";
    }
    /**
     * 添加用户 初始化页面
     * */
    @RequestMapping(value = "/addPage")
    public String addInit(Model model){
        model.addAttribute("cityList", dicService.getListByType(DicTypeConst.CITY));
        model.addAttribute("categoryList", dicService.getListByType(DicTypeConst.CATEGORY));
        return "/content/businessAdd";
    }

    /**
     * 新增
     * */
    @RequestMapping(value = "/addBusiness",method = RequestMethod.POST)
    public String add(BusinessDto businessDto,Model model){
        if(businessService.add(businessDto)){
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.ADD_SUCCESS);
            return "redirect:/business/1";
        }else {
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.ADD_FAIL);
            return "redirect:/business/addPage";
        }

    }

    /**
     * 修改页面初始化
     * */
    @RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
    public String modifyInit(@PathVariable Long id,Model model){
        model.addAttribute("cityList", dicService.getListByType(DicTypeConst.CITY));
        model.addAttribute("categoryList", dicService.getListByType(DicTypeConst.CATEGORY));
        BusinessDto businessDto=businessService.getById(id);
        model.addAttribute("modifyObj",businessDto);
        return "content/businessModify";
    }

    /**
     * 商户修改
     */
    @RequestMapping(value = "/modify/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String modify(@PathVariable("id") Long id, BusinessDto dto,Model model) {
        if(businessService.modify(dto)){
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.MODIFY_SUCCESS);
        }else {
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.MODIFY_FAIL);
        }
        return "content/adModify";
    }
}
