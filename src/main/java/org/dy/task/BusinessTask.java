package org.dy.task;

import org.dy.dao.BusinessDao;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;

import javax.annotation.Resource;

/*商户相关的定时任务*/
@Component("BusinessTask")
public class BusinessTask {

    private static final Logger logger= LoggerFactory.getLogger(BusinessTask.class);

    @Resource
    private BusinessDao businessDao;

    /*同步已售数量*/
    public void synNumber(){
        logger.info("synStar start");
        Integer updateRes;
        updateRes=businessDao.updateNumber();
        if(updateRes==1 &&updateRes !=null)
        logger.info("synStar end");
    }
}
