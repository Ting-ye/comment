package org.dy.service;

import org.dy.dto.AdDto;

import java.util.List;


public interface AdService {
    /**
     * 新增广告
     * @param adDto
     * @return 是否新增成功：true-成功;fale-失败
     */
    boolean add(AdDto adDto);
    /**
     * 分页搜索广告列表
     * @param adDto 查询条件(包含分页对象)
     * @return 广告列表
     */
    List<AdDto> searchByPage(AdDto adDto);
}
