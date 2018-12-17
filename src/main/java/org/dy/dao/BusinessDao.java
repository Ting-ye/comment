package org.dy.dao;

import org.dy.bean.Business;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BusinessDao {
    /**根据主键删除数据*/
    int delete(Long id);

    /**新增*/
    int insert(Business business);

    /**根据主键查询商户*/
    Business searchById(Long id);

    /**根据查询条件分页查询商户列表*/
    List<Business> searchByPage(Business business);

    /**修改用户*/
    int update(Business business);

    /**
     *  根据查询条件分页查询商户列表 :
     *  标题、副标题、描述三个过滤条件为模糊查询
     *  并且这三个过滤条件之间为或者的关系，用 OR 连接
     *  这三个过滤条件与其他过滤条件依然是并且关系，用 AND 连接
     */
    List<Business> searchLikeByPage(Business business);

    /**
     * 更新商户的【统计评论星星总数】、【统计评论总次数】，商户的【星级】用这两个字段数据计算得出
     */
    int updateStar(Map<String,Date> map);

    /**test pagehelper*/
    List<Business> searchtest();
}
