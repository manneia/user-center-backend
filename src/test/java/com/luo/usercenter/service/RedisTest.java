package com.luo.usercenter.service;

import com.luo.usercenter.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author lkx
 * @version 1.0.0
 */
@SpringBootTest
@Slf4j
public class RedisTest {
//    @Resource
//    private RedisTemplate<String, java.io.Serializable> redisTemplate;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    /**
     * 重点用户
     */
    private final List<Long> mainUserList = Arrays.asList(1L);
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private UserService userService;
    @Resource
    private RedissonClient redissonClient;

    @Test
    void test() {
//        ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
//        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        // 增
//        valueOperations.set("yupiString", "1");
//        valueOperations.set("yupiInt", 1);
//        valueOperations.set("yupiDouble", 2.0);
        User user = new User();
        user.setId(0L);
        user.setUsername("赵金麦母狗");
        user.setUserAccount("gold");
        user.setAvatarUrl("");
        user.setGender(0);
        user.setProfile("主人雒凯璇");
        user.setUserRole(0);
        user.setUserPassword("123456789");
        user.setPhone("123456");
        user.setEmail("111@qq.com");
        user.setUserStatus(0);
        user.setTags("");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setIsDelete(0);
        user.setPlanetCode("123");
//        valueOperations.set("yupiUser", user);
        // 查
//        Object string = stringStringValueOperations.get("yupiString");
//        assert string != null;
//        Assertions.assertEquals("1", string);
//        System.out.println(stringStringValueOperations.get("yupiString"));
//        Object yupiInt = stringStringValueOperations.get("yupiInt");
//        Assertions.assertEquals(1, yupiInt);
//        Object yupiDouble = stringStringValueOperations.get("yupiDouble");
//        Assertions.assertEquals(2.0, yupiDouble);
//        Object yupiUser = stringStringValueOperations.get("yupiUser");
//        Assertions.assertEquals(user, yupiUser);
//        System.out.println(stringStringValueOperations.get("yupiUser"));
        redisTemplate.delete("yupiString");
    }


}
