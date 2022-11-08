package com.luo.usercenter.utils;

import com.luo.usercenter.common.BaseResponse;
import com.luo.usercenter.common.ErrorCode;

/**
 * 返回工具类
 *
 * @author lkx
 */
public class ResultUtils {
    /**
     * 成功的全局处理
     *
     * @param data 成功返回对象
     * @param <T>  泛型
     * @return 成功的返回
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(0, data, "OK", "");
    }

    /**
     * 失败的全局处理
     *
     * @param errorCode 错误码
     * @return 失败的返回
     */
    public static BaseResponse error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode);
    }

    /**
     * 失败的全局处理 允许自定义描述和消息
     *
     * @param errorCode 错误码
     * @return 失败的返回
     */
    public static BaseResponse error(ErrorCode errorCode, String message, String description) {
        return new BaseResponse(errorCode.getCode(), null, message, description);
    }

    /**
     *
     * @param code 错误码
     * @param message 信息
     * @param description 描述
     * @return 错误对象
     */
    public static BaseResponse error(int code, String message, String description) {
        return new BaseResponse(code, null, message, description);
    }
    /**
     * 失败的全局处理 允许自定义描述
     *
     * @param errorCode 错误码
     * @return 失败的返回
     */
    public static BaseResponse error(ErrorCode errorCode, String description) {
        return new BaseResponse(errorCode.getCode(), description);
    }
}
