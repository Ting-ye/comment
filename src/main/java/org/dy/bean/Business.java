package org.dy.bean;

public class Business {
    private Long id;
    private String imgFileName;
    private String title;
    private String subtitle;
    private Double price;
    private Integer distance;
    private Integer sellnumber;
    private String desc;
    private String city;
    private String category;
    private Long starTotalNum;
    private Long commentTotalNum;

    private String cityDic;
    private String categoryDic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgFileName() {
        return imgFileName;
    }

    public void setImgFileName(String imgFileName) {
        this.imgFileName = imgFileName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getSellnumber() {
        return sellnumber;
    }

    public void setSellnumber(Integer sellnumber) {
        this.sellnumber = sellnumber;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCityDic() {
        return cityDic;
    }

    public void setCityDic(String cityDic) {
        this.cityDic = cityDic;
    }

    public String getCategoryDic() {
        return categoryDic;
    }

    public void setCategoryDic(String categoryDic) {
        this.categoryDic = categoryDic;
    }
    public Long getStarTotalNum() {
        return starTotalNum;
    }

    public void setStarTotalNum(Long starTotalNum) {
        this.starTotalNum = starTotalNum;
    }

    public Long getCommentTotalNum() {
        return commentTotalNum;
    }

    public void setCommentTotalNum(Long commentTotalNum) {
        this.commentTotalNum = commentTotalNum;
    }

}