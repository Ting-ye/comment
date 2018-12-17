package org.dy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.dy.bean.Business;
import org.dy.bean.Comment;
import org.dy.bean.Orders;
import org.dy.constant.CommentStateConst;
import org.dy.dao.CommentDao;
import org.dy.dao.OrdersDao;
import org.dy.dto.CommentDto;
import org.dy.dto.CommentForSubmitDto;
import org.dy.dto.CommentListDto;
import org.dy.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private OrdersDao ordersDao;
    @Override
    public boolean add(CommentForSubmitDto commentForSubmitDto) {
        Comment comment=new Comment();
        BeanUtils.copyProperties(commentForSubmitDto,comment);
        comment.setId(null);
        comment.setOrdersId(commentForSubmitDto.getId());
        comment.setCreateTime(new Date());
        // 保存评论
        commentDao.insert(comment);
        Orders orders=new Orders();
        orders.setId(commentForSubmitDto.getId());
        orders.setCommentState(CommentStateConst.HAS_COMMENT);
        //更新评论状态
        ordersDao.update(orders);
        return true;
    }

    @Override
    public CommentListDto getListByBusinessId(Long businessId,int pageNum) {
        CommentListDto result=new CommentListDto();

        //组织查询条件
        Comment comment=new Comment();
        Orders orders=new Orders();
        Business business=new Business();
        // 评论里包含了订单对象
        comment.setOrders(orders);
        orders.setBusiness(business);
        business.setId(businessId);

        PageHelper.startPage(pageNum+1,5);
        List<Comment> list=commentDao.selectByPage(comment);
        // TODO
        // 设置hasMore
        PageInfo pageInfo= new PageInfo(list);
        result.setHasMore(pageInfo.getPageNum()<pageInfo.getPages());

        List<CommentDto> data=new ArrayList<CommentDto>();
        for(Comment commentTemp:list){
            CommentDto commentDto=new CommentDto();
            data.add(commentDto);
            BeanUtils.copyProperties(commentTemp,commentDto);
            // 隐藏手机号中间4位
            StringBuffer phoneBuffer = new StringBuffer(String.valueOf(commentTemp.getOrders().getMember().getPhone()));
            commentDto.setUsername(phoneBuffer.replace(3,7,"****").toString());
        }
        result.setData(data);
        return result;
    }
}
