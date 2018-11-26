package org.dy.service.impl;

import org.dy.bean.Orders;
import org.dy.constant.CommentStateConst;
import org.dy.dao.OrdersDao;
import org.dy.dto.OrdersDto;
import org.dy.service.OrdersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService{

    @Resource
    private OrdersDao ordersDao;

    @Value("${businessImage.url}")
    private String businessImageUrll;

    @Override
    public boolean add(OrdersDto ordersDto) {
        Orders orders=new Orders();
        BeanUtils.copyProperties(ordersDto,orders);
        orders.setCommentState(CommentStateConst.NOT_COMMENT);
        ordersDao.insert(orders);
        return true;
    }

    @Override
    public OrdersDto getById(Long id) {
        OrdersDto result=new OrdersDto();
        Orders orders=ordersDao.searchById(id);
        BeanUtils.copyProperties(orders,result);
        return result;
    }

    @Override
    public List<OrdersDto> getListByMemberId(Long memberId) {
        List<OrdersDto> result=new ArrayList<OrdersDto>();
        Orders ordersForServlet=new Orders();
        ordersForServlet.setMemberId(memberId);
        List<Orders> ordersList=ordersDao.select(ordersForServlet);
        for(Orders orders:ordersList){
            OrdersDto ordersDtoTemp=new OrdersDto();
            result.add(ordersDtoTemp);
            BeanUtils.copyProperties(orders,ordersDtoTemp);
            ordersDtoTemp.setImg(businessImageUrll+orders.getBusiness().getImgFileName());
            ordersDtoTemp.setTitle(orders.getBusiness().getTitle());
            ordersDtoTemp.setCount(orders.getBusiness().getSellnumber());
        }
        return result;
    }
}
