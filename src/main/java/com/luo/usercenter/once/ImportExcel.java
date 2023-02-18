package com.luo.usercenter.once;

import com.alibaba.excel.EasyExcel;

import java.util.List;

/**
 * 导入Excel数据
 *
 * @author lkx
 */
public class ImportExcel {
    /**
     * 读取数据
     */
    public static void main(String[] args) {
        String fileName = "D:\\Project\\UserCenter\\user-center-backend\\src\\main\\resources\\testExcel.xlsx";

//        readByListener(fileName);
        synchronousRead(fileName);

    }

    /**
     * 通过监听器读取
     * @param fileName 文件名称
     */
    public static void readByListener(String fileName) {
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, XinQiuUserTableInfo.class, new TableListener()).sheet().doRead();
    }

    /**
     * 同步读取
     * @param fileName 文件名称
     */
    public static void synchronousRead(String fileName) {
        List<XinQiuUserTableInfo> totalDataList = EasyExcel.read(fileName).head(XinQiuUserTableInfo.class).sheet().doReadSync();
        for (XinQiuUserTableInfo xinQiuUserTableInfo : totalDataList) {
            System.out.println(xinQiuUserTableInfo);
        }
    }
}
