package org.dy.dao;

import org.dy.bean.Orders;

import java.util.List;

public interface OrdersDao {

    /*新增*/
    int insert(Orders orders);

    /*根据主键查询*/
    Orders searchById(Long id);

    /*修改*/
    int update(Orders orders);

    /*条件查询*/
    List<Orders> select(Orders orders);
}
