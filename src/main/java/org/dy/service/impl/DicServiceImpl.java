package org.dy.service.impl;

import org.dy.bean.Dic;
import org.dy.dao.DicDao;
import org.dy.service.DicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DicServiceImpl implements DicService{
    @Resource
    private DicDao dicDao;

    @Override
    public List<Dic> getListByType(String type) {
        Dic dic=new Dic();
        dic.setType(type);
        List<Dic> result=dicDao.select(dic);
        return result;
    }
}
