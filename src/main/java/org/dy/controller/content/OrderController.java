package org.dy.controller.content;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.dy.bean.Business;
import org.dy.bean.Orders;
import org.dy.dto.BusinessDto;
import org.dy.dto.OrdersDto;
import org.dy.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrdersService ordersService;

    @RequestMapping
    public String init(Model model){
        return "content/orderList";
    }

    /**
     * 订单列表
     */
    @RequestMapping(value="/{pageNum}",method = RequestMethod.GET)
    public String ordersList(@PathVariable Integer pageNum,Model model){
        if(pageNum<=0 ||pageNum ==null)
            pageNum=1;
        PageHelper.startPage(pageNum,5);
        List<Orders> ordersList=ordersService.searchByPage(new OrdersDto());
        PageInfo pageInfo= new PageInfo(ordersList);
        List<OrdersDto> ordersDtoList=ordersService.searchByPageHelper(ordersList);
        model.addAttribute("ordersList",ordersDtoList);
        model.addAttribute("pagenum",pageInfo.getPages());
        return "content/orderList";
    }
    /*分页查询*/
    @RequestMapping(value ="/getlist/{pn}",
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public List<OrdersDto> getList(@PathVariable Integer pn){
        if(pn<=0 ||pn ==null)
            pn=1;
        PageHelper.startPage(pn,5);
        List<Orders> ordersList=ordersService.searchByPage(new OrdersDto());
        PageInfo pageInfo= new PageInfo(ordersList);
        return  ordersService.searchByPageHelper(ordersList);
    }
}
