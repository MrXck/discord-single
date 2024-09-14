package com.discord.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.discord.dto.operationLog.GetDateAndCountDTO;
import com.discord.dto.operationLog.OperationLogDTO;
import com.discord.dto.operationLog.PageDTO;
import com.discord.pojo.OperationLog;

public interface OperationLogService extends IService<OperationLog> {
    OperationLogDTO getByPage(PageDTO dto);

    void delete(Long id);

    OperationLogDTO getDateAndCountByUserId(GetDateAndCountDTO dto);
}
