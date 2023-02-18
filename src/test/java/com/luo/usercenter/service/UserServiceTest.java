package com.luo.usercenter.service;

import com.luo.usercenter.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 用户服务测试
 *
 * @author lkx
 */
@SpringBootTest
public class UserServiceTest {
    @Resource
    private UserService userService;

    @Test
    void testAddUser() {
        User user = new User();
        user.setUsername("testLuo");
        user.setUserAccount("123");
        user.setAvatarUrl("https://ts1.cn.mm.bing.net/th/id/R-C.46ae61f6b29af2e068531b464c209545?rik=imvMBoQlHGlvZQ&riu=http%3a" +
                "%2f%2fimgs.ppt118.com%2fppt%2fbeijing%2fdetail%2f2019%2f03%2f29%2f2496_detail-2.jpg_w800&ehk=5%2fd1TaxHtYOEyNJPvU7wp12Mxv5FUb" +
                "Mfw2DKIwP7SwE%3d&risl=&pid=ImgRaw&r=0");
        user.setGender(0);
        user.setUserPassword("123");
        user.setPhone("123");
        user.setEmail("456");
        boolean result = userService.save(user);
        System.out.println(user.getId());
        Assertions.assertTrue(result);
    }

    @Test
    void userRegister() {
        String userAccount = "gold";
        String userPassword = "123456789";
        String checkPassword = "123456789";
        String planetCode = "1";
        long result = userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
        Assertions.assertEquals(-1, result);
    }

    @Test
    void searchUsersByTags() {
        List<String> tagNameList = Arrays.asList("java","python");
        List<User> userList = userService.searchUsersByTags(tagNameList);
        Assert.assertNotNull(userList);
    }
}