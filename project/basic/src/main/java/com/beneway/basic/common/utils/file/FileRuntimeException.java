package com.beneway.basic.common.utils.file;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;

/**
 * Create by zhy on 2022/3/3 13:59
 *
 * 对文件异常进行封装
 */
public class FileRuntimeException extends RuntimeException{

    public FileRuntimeException(Throwable e) {
        super(ExceptionUtil.getMessage(e), e);
    }

    public FileRuntimeException(String message) {
        super(message);
    }

    public FileRuntimeException(String messageTemplate, Object... params) {
        super(StrUtil.format(messageTemplate, params));
    }

    public FileRuntimeException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public FileRuntimeException(Throwable throwable, String messageTemplate, Object... params) {
        super(StrUtil.format(messageTemplate, params), throwable);
    }

    /**
     * 导致这个异常的异常是否是指定类型的异常
     *
     * @param clazz 异常类
     * @return 是否为指定类型异常
     */
    public boolean causeInstanceOf(Class<? extends Throwable> clazz) {
        final Throwable cause = this.getCause();
        return null != clazz && clazz.isInstance(cause);
    }

}
