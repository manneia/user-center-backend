package com.luo.usercenter.service;

import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author lkx
 * @version 1.0.0
 */
@SpringBootTest
public class RedissonTest {
    @Resource
    private RedissonClient redissonClient;

    @Test
    void test() {
        // list 数据存在本地JVM内存中
//        List<String> list = new ArrayList<>();
//        list.add("lkx");
//        String s = list.get(0);
//        list.remove(0);
        // 数据存在redis的内存中
//        RList<String> clientList = redissonClient.getList("test-list");
//        clientList.add("0");
//        String s1 = clientList.get(0);
//        System.out.println(s1);
//        clientList.remove(0);
        // map
        RMap<String, Integer> clientMap = redissonClient.getMap("test-map");
//        clientMap.put("王文沛", 10);
//        Integer integer = clientMap.get("王文沛");
//        System.out.println(integer);
//        clientMap.remove("王文沛");
        // set
        RSet<String> set = redissonClient.getSet("test-set");
        set.add("王文倩");
        // stack
    }

    @Test
    void testWatchDog() {

        RLock lock = redissonClient.getLock("luo:precachejob:docache:lock");
        try {
            // 只有一个线程能获取到锁
            if (lock.tryLock(0, -1, TimeUnit.MILLISECONDS)) {
                Thread.sleep(300000);
                System.out.println("getlock:" + Thread.currentThread().getId());
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            // 只能释放自己的锁
            if (lock.isHeldByCurrentThread()) {
                System.out.println("unlock:" + Thread.currentThread().getId());
                lock.unlock();
            }
        }
    }
}
