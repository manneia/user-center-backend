package com.luo.usercenter.once;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lkx
 */
@Slf4j
public class TableListener implements ReadListener<XinQiuUserTableInfo> {

    @Override
    public void invoke(XinQiuUserTableInfo data, AnalysisContext context) {
        System.out.println(data.getUsername() + "  " + data.getPlanetCode());
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("已解析完成");
    }

}