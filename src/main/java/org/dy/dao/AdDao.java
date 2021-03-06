package org.dy.dao;

import org.dy.bean.Ad;

import java.util.List;

public interface AdDao {

    /**
     * 根据主键插入数据*/
    int insert(Ad ad);
    /**
     * 根据查询条件分页查询
     * @param ad 查询条件：包括广告表的查询字段和分页信息
     * @return 广告列表
     */
    List<Ad> selectByPage(Ad ad);

    /**
     * 根据主键查询广告对象
     * @param id 主键值
     * @return 广告对象
     */
    Ad selectById(Long id);


    /**
     * 根据主键删除数据*/
    int delete(Long id);


    /**
     * 根据主键修改Ad
     * */
    int update(Ad ad);
}
