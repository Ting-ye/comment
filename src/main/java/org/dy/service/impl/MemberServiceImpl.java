package org.dy.service.impl;

import org.dy.bean.Member;
import org.dy.cache.CodeCache;
import org.dy.cache.TokenCache;
import org.dy.dao.MemberDao;
import org.dy.service.MemberService;
import org.dy.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{

    @Resource
    private MemberDao memberDao;

    private final static Logger logger = LoggerFactory.getLogger(MemberService.class);

    @Override
    public boolean exists(Long phone) {
        Member member=new Member();
        member.setPhone(phone);
        List<Member> list=memberDao.select(member);
        return list !=null&& list.size()==1;
    }

    @Override
    public boolean saveCode(Long phone, String code) {
        // TODO 在真实环境中，改成借助第三方实现 redis
        // TODO 可以解决1.用户短时间内重复提交请求验证码 利用原子性只让第一次请求能有效
        // TODO 可以解决2.在集群化部署单机内缓存不共享 请求验证码和请求登录两次请求不是发给同一个机器name验证码对于不同机器是无效的 除非保证每次请求发给同一个机器
        // TODO 可以解决3.缓存失效 例如：让验证码有效时间只有10分钟
        CodeCache codeCache=CodeCache.getInstance();
        return codeCache.save(phone, MD5Util.getMD5(code));
    }

    @Override
    public boolean sendCode(Long phone, String content) {
        //真实环境下是直接调用短信通道的
        logger.info(phone+"|"+content);
        return true;
    }

    @Override
    public String getCode(Long phone) {
        // TODO 在真实环境中，改成借助第三方实现 redis
        // TODO 可以解决1.用户短时间内重复提交请求验证码 利用原子性只让第一次请求能有效
        // TODO 可以解决2.在集群化部署单机内缓存不共享 请求验证码和请求登录两次请求不是发给同一个机器name验证码对于不同机器是无效的 除非保证每次请求发给同一个机器
        // TODO 可以解决3.缓存失效 例如：让验证码有效时间只有10分钟
        CodeCache codeCache=CodeCache.getInstance();
        return codeCache.getCode(phone);
    }

    @Override
    public void saveToken(String token, Long phone) {
        TokenCache tokenCache=TokenCache.getInstance();
        tokenCache.save(token,phone);
    }

    @Override
    public Long getPhone(String token) {
        TokenCache tokenCache=TokenCache.getInstance();
        return tokenCache.getPhone(token);
    }

    @Override
    public Long getIdByPhone(Long phone) {
        Member member =new Member();
        member.setPhone(phone);
        List<Member> list=memberDao.select(member);
        if (list != null && list.size()==1){
            return list.get(0).getId();
        }
        return null;
    }
}
