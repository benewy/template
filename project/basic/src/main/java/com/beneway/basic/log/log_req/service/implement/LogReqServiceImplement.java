package com.beneway.basic.log.log_req.service.implement;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beneway.basic.log.log_req.dao.LogReqDao;
import com.beneway.basic.log.log_req.entity.logReq.LogReq;
import com.beneway.basic.log.log_req.service.LogReqService;
import org.springframework.stereotype.Service;

@Service
public class LogReqServiceImplement extends ServiceImpl<LogReqDao, LogReq> implements LogReqService {

}
