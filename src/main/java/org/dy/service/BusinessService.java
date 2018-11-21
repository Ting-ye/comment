package org.dy.service;

import org.dy.bean.Business;
import org.dy.dto.BusinessDto;
import org.dy.dto.BusinessListDto;

import java.util.List;

public interface BusinessService {
    /**
    * 删除商家
    * */
    boolean remove(Long id);

    /**
     * 新增
     * @param businessDto 商户dto对象
     * @return 是否新增成功：true-成功;fale-失败
     */
    boolean add(BusinessDto businessDto);

    /**
     * 根据主键获取商户dto
     * @param id 主键
     * @return 商户dto
     */
    BusinessDto getById(Long id);

    /**
     * 分页搜索商户列表
     * @param businessDto 查询条件
     * @return 商户列表
     */
//    List<BusinessDto> searchByPage(BusinessDto businessDto);

    /**
     * 分页搜索商户列表
     * 先查询出business 然后再利用PageHelper插件 这样才可以得到正确的数值                
     * */
    List<Business> searchByPage(BusinessDto businessDto);

    List<BusinessDto> searchByPageHelper(List<Business> businessList);

    /**
     * 分页搜索商户列表(接口专用)
     * @param businessDto 查询条件
     * @return 商户列表Dto对象
     */
    BusinessListDto searchByPageForApi(BusinessDto businessDto,int pageNum);

    List<Business> getBusinessListTest();
    /**
     * 修改广告
     * @param bsDto
     * @return 是否修改成功：true-成功;fale-失败
     */
    boolean modify(BusinessDto bsDto);
}
