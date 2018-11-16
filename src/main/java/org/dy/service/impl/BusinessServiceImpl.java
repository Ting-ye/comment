package org.dy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.dy.bean.Business;
import org.dy.constant.CategoryConst;
import org.dy.dao.BusinessDao;
import org.dy.dto.AdDto;
import org.dy.dto.BusinessDto;
import org.dy.dto.BusinessListDto;
import org.dy.service.BusinessService;
import org.dy.util.CommonUtil;
import org.dy.util.FileUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService{

    @Resource
    private BusinessDao businessDao;

    @Value("${businessImage.savePath}")
    private String savePath;

    @Value("${businessImage.url}")
    private String url;

    @Override
    public boolean remove(Long id) {
        Business business=businessDao.searchById(id);
        int deleteRow= businessDao.delete(id);
        return deleteRow==1;
    }

    @Override
    public boolean add(BusinessDto businessDto) {
        Business business =new Business();
        BeanUtils.copyProperties(businessDto,business);
        if(businessDto.getImgFile()!=null&&businessDto.getImgFile().getSize()>0){
            try{
                String fileName= FileUtil.save(businessDto.getImgFile(),savePath);
                business.setImgFileName(fileName);
                business.setSellnumber(0);
                business.setStarTotalNum(0L);
                business.setCommentTotalNum(0L);
                businessDao.insert(business);
                return true;
            }catch (IllegalStateException | IOException e){
               //TODO需要添加日志
                return false;
            }
        }else {
            return false;
        }
    }

    @Override
    public BusinessDto getById(Long id) {
        BusinessDto result=new BusinessDto();
        Business business=businessDao.searchById(id);
        BeanUtils.copyProperties(business,result);
        result.setImg(url+business.getImgFileName());
        return result;
    }

    @Override
    public List<BusinessDto> searchByPage(BusinessDto businessDto) {
        List<BusinessDto> result =new ArrayList<BusinessDto>();
        Business condition=new Business();
        BeanUtils.copyProperties(businessDto,condition);
        List<Business> businessList=businessDao.searchByPage(condition);
        for(Business business:businessList){
            BusinessDto busiDto=new BusinessDto();
            result.add(busiDto);
            busiDto.setImg(url+business.getImgFileName());
            BeanUtils.copyProperties(business,busiDto);
        }
        return null;
    }

    @Override
    public BusinessListDto searchByPageForApi(BusinessDto businessDto) {
        BusinessListDto result =new BusinessListDto();

        // 组织查询条件
        Business businessForSelect=new Business();
        BeanUtils.copyProperties(businessDto,businessForSelect);
        // 当关键字不为空时，把关键字的值分别设置到标题、副标题、描述中
        // TODO 改进做法：全文检索
        if(!CommonUtil.isEmpty(businessDto.getKeyword())){
            businessForSelect.setTitle(businessDto.getKeyword());
            businessForSelect.setSubtitle(businessDto.getKeyword());
            businessForSelect.setDesc(businessDto.getKeyword());
        }
        // 当类别为全部(all)时，需要将类别清空，不作为过滤条件
        if(businessDto.getCategory()!=null|| CategoryConst.ALL.equals(businessDto.getCategory())){
            businessForSelect.setCategory(null);
        }
//        // 前端app页码从0开始计算，这里需要+1
//        int currentPage = businessForSelect.getPage().getCurrentPage();
//        businessForSelect.getPage().setCurrentPage(currentPage + 1);

        PageHelper.startPage(1,5);
        List<Business> list=businessDao.searchLikeByPage(businessForSelect);
        // TODO
        // 设置hasMore
        PageInfo pageInfo= new PageInfo(list);
        result.setHasMore(pageInfo.getPageNum()<pageInfo.getTotal());



        return result;
    }
}
