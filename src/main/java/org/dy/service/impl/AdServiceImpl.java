package org.dy.service.impl;

import org.dy.bean.Ad;
import org.dy.dao.AdDao;
import org.dy.dto.AdDto;
import org.dy.service.AdService;
import org.dy.util.FileUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdServiceImpl implements AdService{

    @Autowired
    private AdDao adDao;

    @Value("${adImage.savePath}")
    private String adImageSavePath;

    @Value("${adImage.url}")
    private String adImageUrl;

    @Override
    public boolean add(AdDto adDto) {
        Ad ad=new Ad();
        ad.setTitle(adDto.getTitle());
        ad.setLink(adDto.getLink ());
        ad.setWeight(adDto.getWeight());
        if(adDto.getImgFile()!=null&adDto.getImgFile().getSize()>0){
            String fileName=System.currentTimeMillis()+"_"+adDto.getImgFile().getOriginalFilename();
            File file=new File(adImageSavePath + fileName);
            File fileFolder=new File(adImageSavePath);
            if(!fileFolder.exists()){
//                创建多级文件目录
                fileFolder.mkdirs();
            }
            try {
                adDto.getImgFile().transferTo(file);
                System.out.println(file);
                System.out.println(adImageSavePath);
                ad.setImgFileName(fileName);
                adDao.insert(ad);
                return true;
            } catch (IOException e) {
               return false;
            }
        }else {
            return false;
        }

    }

    @Override
    public List<Ad> searchByPage(AdDto adDto) {
//        List<AdDto> result =new ArrayList<AdDto>();
//        Ad condition =new Ad();
//        BeanUtils.copyProperties(adDto,condition);
//        List<Ad> adList=adDao.selectByPage(condition);
//        for(Ad ad:adList){
//            AdDto adDtoTemp=new AdDto();
//            result.add(adDtoTemp);
//            adDtoTemp.setImg(adImageUrl+ad.getImgFileName());
//            BeanUtils.copyProperties(ad,adDtoTemp);
//        }
//        return result;

        Ad condition=new Ad();
        BeanUtils.copyProperties(adDto,condition);
        List<Ad> adList=adDao.selectByPage(condition);
        return adList;
    }

    @Override
    public List<AdDto> searchByPageHelper(List<Ad> adDtoList) {
        List<AdDto> result=new ArrayList<AdDto>();
        for(Ad ad:adDtoList){
            AdDto adDtoTemp=new AdDto();
            result.add(adDtoTemp);
            adDtoTemp.setImg(adImageUrl+ad.getImgFileName());
            BeanUtils.copyProperties(ad,adDtoTemp);
        }
        return result;
    }

    @Override
    public boolean remove(Long id) {
        Ad ad=adDao.selectById(id);
        int deleteRows=adDao.delete(id);
      FileUtil.delete(adImageSavePath+ad.getImgFileName());
        return deleteRows==1;
    }

    @Override
    public AdDto getById(Long id) {
        AdDto result=new AdDto();
        Ad ad=adDao.selectById(id);
        BeanUtils.copyProperties(ad,result);
        result.setImg(adImageUrl+ad.getImgFileName());
        return result;
    }

    @Override
    public boolean modify(AdDto adDto) {
        Ad ad = new Ad();
        BeanUtils.copyProperties(adDto, ad);
        String fileName = "";
        if (adDto.getImgFile() != null && adDto.getImgFile().getSize() > 0) {
            try {
                fileName = FileUtil.save(adDto.getImgFile(), adImageSavePath);
                ad.setImgFileName(fileName);
                System.out.println(ad.getImgFileName());
            } catch (IllegalStateException | IOException e) {
                // TODO 需要添加日志
                return false;
            }
        }
        int updateCount = adDao.update(ad);
        if (updateCount != 1) {
            return false;
        }
//        //删除原来的图片文件
//        if (fileName != null) {
//            return FileUtil.delete(adImageSavePath + adDto.getImgFileName());
//        }
        return true;
    }
}
