package org.dy.service.impl;

import org.dy.bean.Ad;
import org.dy.dao.AdDao;
import org.dy.dto.AdDto;
import org.dy.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class AdServiceImpl implements AdService{

    @Autowired
    private AdDao adDao;

    @Value("${adImage.savePath}")
    private String adImageSavePath;

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
}
