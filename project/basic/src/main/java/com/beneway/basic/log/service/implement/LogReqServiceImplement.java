package com.beneway.basic.log.service.implement;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beneway.basic.log.dao.LogReqDao;
import com.beneway.basic.log.entity.logReq.LogReq;
import com.beneway.basic.log.service.LogReqService;
import org.springframework.stereotype.Service;

@Service
public class LogReqServiceImplement extends ServiceImpl<LogReqDao, LogReq> implements LogReqService {

}
