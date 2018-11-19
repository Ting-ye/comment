package org.dy.controller.content;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.dy.bean.Ad;
import org.dy.constant.PageCodeEnum;
import org.dy.dto.AdDto;
import org.dy.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/ad")
public class AdController {
    @Autowired
    private AdService adService;

    @Value("${ad.pageNum}")
    private int adPagenum;

    @RequestMapping
    public String init(Model model){
        List<Ad> adList=adService.searchByPage(new AdDto());
        model.addAttribute("adlsit",adList);
        return "content/adList";
    }
    @RequestMapping(value ="/{pn}")
    public String init(@PathVariable Integer pn, Model model){
        if(pn<=0 ||pn ==null||pn.equals(""))
            pn=1;
        PageHelper.startPage(pn,5);
        List<Ad> adList=adService.searchByPage(new AdDto());
        PageInfo pageInfo= new PageInfo(adList);
        List<AdDto> list=adService.searchByPageHelper(adList);
        model.addAttribute("adlist",list);
        model.addAttribute("pagenum",pageInfo.getPages());
        return "content/adList";
    }
    /*分页查询*/
    @RequestMapping(value ="/getlist/{pn}",
                    produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public List<AdDto> getList(@PathVariable Integer pn){
        if(pn<=0 ||pn ==null)
            pn=1;
        PageHelper.startPage(pn,5);
        List<Ad> adList=adService.searchByPage(new AdDto());
        PageInfo pageInfo= new PageInfo(adList);
        return adService.searchByPageHelper(adList);
    }
    /**
     * 模糊查询
     */
    @RequestMapping(value = "/search/{param}")
    @ResponseBody
    public List<AdDto> search(@PathVariable(value = "param") String title){
        AdDto adDto= new AdDto();
        if(!title.equals("BACD6F4771952C9C5D254DE71C485B05"))
            adDto.setTitle(title);
            int pn=1;
        PageHelper.startPage(pn,5);
        List<Ad> adList= adService.searchByPage(adDto);
        PageInfo pageInfo= new PageInfo(adList);
        return  adService.searchByPageHelper(adList);
    }

    /**
     * 新增页初始化
     */
    @RequestMapping(value ="/addInit")
    public String addInit() {
        return "/content/adAdd";
    }

    /**
     * 新增
     */
    @RequestMapping(value ="/add",method = RequestMethod.POST)
    public String add(AdDto adDto, Model model){
        if(adService.add(adDto)){
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.ADD_SUCCESS);
        }else{
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.ADD_FAIL);
        }
        return "/content/adAdd";
    }


    /**
     * 删除
     */
    @RequestMapping("/remove")
    public String  delete(@RequestParam Long id,Model model){
        if(adService.remove(id)){
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.REMOVE_SUCCESS);
        }else {
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.REMOVE_FAIL);
        }
        return "forward:/ad/1";
    }
    /**
     * 修改页面初始化
     */
    @RequestMapping("/modifyInit")
    public String modifyInit(Model model,@RequestParam(required = true) Long id){
        Ad ad=adService.getById(id);
        model.addAttribute("modifyObj",ad);
        return "/content/adModify";
    }
    /**
     * 修改
     */
    @RequestMapping("/modify")
    public String modify(Model model,AdDto adDto){
        model.addAttribute("modifyObj",adDto);
        if(adService.modify(adDto)){
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.MODIFY_SUCCESS);
        }else {
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.MODIFY_FAIL);
        }
        return "/content/adModify";
    }
}
