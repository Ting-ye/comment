package org.dy.dto;

import org.dy.bean.Orders;

public class OrdersDto extends Orders{
    private String img;
    private String title;
    /*已售数量*/
    private Integer count;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
