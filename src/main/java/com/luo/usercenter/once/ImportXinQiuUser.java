package com.luo.usercenter.once;

import com.alibaba.excel.EasyExcel;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 将用户导入数据库
 *
 * @author lkx
 */
public class ImportXinQiuUser {
    public static void main(String[] args) {
        String fileName = "D:\\Project\\UserCenter\\user-center-backend\\src\\main\\resources\\testExcel.xlsx";
        List<XinQiuUserTableInfo> userInfoList = EasyExcel.read(fileName).head(XinQiuUserTableInfo.class).sheet().doReadSync();
        System.out.println("总数 = " + userInfoList.size());
        Map<String, List<XinQiuUserTableInfo>> listMap = userInfoList.stream()
                .filter(userInfo -> StringUtils.isNotEmpty(userInfo.getUsername()))
                .collect(Collectors.groupingBy(XinQiuUserTableInfo::getUsername));
        for (Map.Entry<String,List<XinQiuUserTableInfo>> stringListEntry : listMap.entrySet()){
            if (stringListEntry.getValue().size()>1){
                System.out.println("username= " + stringListEntry.getKey());
            }
        }
        System.out.println("不重复的昵称数量 = " + listMap.keySet().size());
    }
}
