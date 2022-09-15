package com.beneway.basic.common.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;

public interface MyIService<T> extends IService<T> {

    default boolean isExist(Wrapper<T> queryWrapper){
        return count(queryWrapper) > 0;
    }

}
