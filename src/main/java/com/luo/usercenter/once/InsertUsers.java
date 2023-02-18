package com.luo.usercenter.once;

import com.luo.usercenter.mapper.UserMapper;
import com.luo.usercenter.model.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;

/**
 * @author lkx
 * @version 1.0.0
 */
@Component
public class InsertUsers {
    @Resource
    private UserMapper userMapper;

    /**
     * @Scheduled(initialDelay = 5000, fixedRate = Long.MAX_VALUE)
     * 批量插入用户
     */
    public void doInsertUsers() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        int dataNum = 1000;
        for (int i = 0; i < dataNum; i++) {
            User user = new User();
            user.setUsername("假用户" + i);
            user.setUserAccount("test" + i);
            user.setAvatarUrl("https://dogefs.s3.ladydaily.com/~/source/unsplash/photo-1" +
                    "673082657820-3ac9d029ae2e?ixid=MnwxMjA3fDB8MXx0b3BpY3x8eEh4WVRNSExnT2N8fHx8f" +
                    "DJ8fDE2NzYzMzgyOTc&ixlib=rb-4.0.3&w=300&h=150&fmt=webp");
            user.setGender(0);
            user.setProfile("你好" + i);
            user.setUserRole(0);
            user.setUserPassword("123456789");
            user.setPhone("17835115649");
            user.setEmail("123@qq.com");
            user.setUserStatus(0);
            user.setTags("[]");
            user.setPlanetCode("111" + i);
            userMapper.insert(user);
        }
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }
}
