package org.dy.service.impl;

import org.dy.bean.Ad;
import org.dy.dao.AdDao;
import org.dy.dto.AdDto;
import org.dy.service.AdService;
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
    public List<AdDto> searchByPage(AdDto adDto) {
        List<AdDto> result =new ArrayList<AdDto>();
        Ad condition =new Ad();
        BeanUtils.copyProperties(adDto,condition);
        List<Ad> adList=adDao.selectByPage(condition);
        for(Ad ad:adList){
            AdDto adDtoTemp=new AdDto();
            result.add(adDtoTemp);
            adDtoTemp.setImg(adImageUrl+ad.getImgFileName());
            BeanUtils.copyProperties(ad,adDtoTemp);
        }
        return result;
    }
}
