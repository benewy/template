package com.beneway.basic.system.sys_message.modules;

import cn.hutool.core.thread.ExecutorBuilder;
import com.beneway.basic.enums.UserTypeEnum;
import com.beneway.basic.system.sys_message.modules.enums.MessageModuleEnum;
import com.beneway.basic.utils.AppUtils;
import com.beneway.basic.utils.SpringContextHolder;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022/4/27 17:32
 *
 * 发送消息模块工具类
 */
@Log4j2
@Component("messageModuleUtils")
public class MessageModuleUtils implements DisposableBean {

    @Resource
    private AppUtils appUtils;

    /**
     * 保存请求日志线程池
     */
    private ExecutorService executor;

    /**
     * 构造函数 初始化保存请求日志线程池
     */
    public MessageModuleUtils() {
        log.info(getClass().getName() + " 构建初始化线程池");
        executor = ExecutorBuilder.create()
                .setCorePoolSize(3)
                .setMaxPoolSize(10)
                .setWorkQueue(new LinkedBlockingQueue<>(10))
                .build();
    }

    /**
     * 发送消息
     * @param problemId
     * @param lookUserList
     * @param userType
     * @param content
     */
    public void sendMsg(String problemId, Collection<String> lookUserList, UserTypeEnum userType, String content){
        boolean prod = appUtils.isProd();
        // 获取消息执行模块枚举数组
        MessageModuleEnum[] moduleEnums = MessageModuleEnum.values();
        for (MessageModuleEnum moduleEnum : moduleEnums) {
            boolean prodSend = moduleEnum.isProdSend();
            UserTypeEnum userTypeEnum = moduleEnum.getUserTypeEnum();
            if (prod == prodSend && userTypeEnum.equals(userType)) {
                // 执行发送消息
                Class aClass = moduleEnum.getaClass();
                MessageModuleService messageModuleService = (MessageModuleService) SpringContextHolder.getBean(aClass);
                String desc = moduleEnum.getDesc();
                try {
                    Future<?> submit = executor.submit(() -> {
                        String logStr = "发送 " + desc + " 模块消息";
                        log.info(logStr + " 开始");
                        messageModuleService.sendMsg(problemId, lookUserList, content);
                        log.info(logStr + " 结束");
                    });
                    submit.get();
                } catch (Exception e) {
                    log.error("模块消息发送失败", e);
                }
            }
        }
    }

    /**
     * 对象销毁 关闭线程池
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        executor.shutdown();
    }

}
