package com.shop.ssm.service.impl;

import com.shop.ssm.mapper.LogMapper;
import com.shop.ssm.pojo.Log;
import com.shop.ssm.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2019/2/21.
 */
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    LogMapper logMapper;
    public void insertLog(Log log) {
        logMapper.insert(log);
    }
}
