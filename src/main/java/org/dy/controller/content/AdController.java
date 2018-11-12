package org.dy.controller.content;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.dy.bean.Ad;
import org.dy.constant.PageCodeEnum;
import org.dy.dto.AdDto;
import org.dy.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/ad")
public class AdController {
    @Autowired
    private AdService adService;

    @RequestMapping
    public String init(Model model){
        List<AdDto> adList=adService.searchByPage(new AdDto());
        model.addAttribute("adlsit",adList);
        return "content/adList";
    }
    @RequestMapping("/{pn}")
    public String init(@PathVariable Integer pn, Model model){
        if(pn<=0 ||pn ==null||pn.equals(""))
            pn=1;
        PageHelper.startPage(pn,5);
        List<AdDto> adList=adService.searchByPage(new AdDto());
        PageInfo pageInfo= new PageInfo(adList);
        model.addAttribute("adlist",adList);
        model.addAttribute("pagenum",adList.size());
        return "content/adList";
    }
    /*查询*/
    @RequestMapping("/getlist/{pn}")
    @ResponseBody
    public List<AdDto> getList(@PathVariable Integer pn){
        if(pn<=0 ||pn ==null)
            pn=1;
        System.out.println(pn);
        PageHelper.startPage(pn,6);
        List<AdDto> adList=adService.searchByPage(new AdDto());
        PageInfo pageInfo= new PageInfo(adList);
        return adList;
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
    @RequestMapping(value = "/search/{param}",method = RequestMethod.GET)
    @ResponseBody
    public List<AdDto> search(@PathVariable(value = "param") String title){
        AdDto adDto= new AdDto();
      if(!title.equals("BACD6F4771952C9C5D254DE71C485B05"))
        adDto.setTitle(title);

      System.out.println("title="+adDto.getTitle());
       List<AdDto> adlist= adService.searchByPage(adDto);
       return  adlist;
    }
}
