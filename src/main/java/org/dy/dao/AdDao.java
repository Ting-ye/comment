package org.dy.dao;

import org.dy.bean.Ad;

import java.util.List;

public interface AdDao {
    int insert(Ad ad);
    /**
     * 根据查询条件分页查询
     * @param ad 查询条件：包括广告表的查询字段和分页信息
     * @return 广告列表
     */
    List<Ad> selectByPage(Ad ad);
}
