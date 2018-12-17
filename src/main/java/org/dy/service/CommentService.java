package org.dy.service;

import org.dy.bean.Comment;
import org.dy.dto.CommentForSubmitDto;
import org.dy.dto.CommentListDto;

import java.util.List;

public interface CommentService {
    /*保存评论*/
    boolean add(CommentForSubmitDto commentForSubmitDto);

    /**
     * 分页搜索商户列表(接口专用)
     * @param  businessId 查询条件
     * @return 商户列表Dto对象
     */
    CommentListDto getListByBusinessId(Long businessId,int pageNum);

    /**
     * 分页搜索商户列表
     * 先查询出comment 然后再利用PageHelper插件 这样才可以得到正确的数值                
     * */
}
