package com.luo.usercenter.service;

import com.luo.usercenter.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author lkx
 * @version 1.0.0
 */
@SpringBootTest
public class InsertUsersTest {
    @Resource
    private UserService userService;
    // CPU 密集型:  分配的核心线程数 = cpu - 1
    // IO  密集型:  分配的核心线程数可以大于 cpu 核数
    private ExecutorService executorService = new ThreadPoolExecutor(40, 1000, 10000, TimeUnit.MINUTES, new ArrayBlockingQueue<>(10000));

    @Test
    public void doInsertUsers() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        int dataNum = 100000;
        List<User> userList = new ArrayList<>();
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
            userList.add(user);
        }
        userService.saveBatch(userList, 1000);
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }

    /**
     * 并发批量插入用户
     */
    @Test
    public void doConcurrencyInsertUsers() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        // 分十组
        int batchSize = 10000;
        int j = 0;
        List<CompletableFuture<Void>> futureList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            List<User> userList = new ArrayList<>();
            while (true) {
                j++;
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
                userList.add(user);
                if (j % batchSize == 0) {
                    break;
                }
            }
            // 异步执行
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                System.out.println("threadName: " + Thread.currentThread().getName());
                userService.saveBatch(userList, batchSize);
            }, executorService);
            futureList.add(future);
        }
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[]{})).join();
        // 20 秒 10 万条
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }

}
