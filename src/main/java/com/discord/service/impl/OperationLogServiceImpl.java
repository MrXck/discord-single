package com.discord.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.discord.dto.operationLog.GetDateAndCountDTO;
import com.discord.dto.operationLog.OperationLogDTO;
import com.discord.dto.operationLog.OperationLogVO;
import com.discord.dto.operationLog.PageDTO;
import com.discord.mapper.OperationLogMapper;
import com.discord.pojo.OperationLog;
import com.discord.service.OperationLogService;
import com.discord.utils.UserThreadLocal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements OperationLogService {
    @Override
    public OperationLogDTO getByPage(PageDTO dto) {
        Page<OperationLog> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        LambdaQueryWrapper<OperationLog> queryWrapper = new LambdaQueryWrapper<>();
        String keyword = dto.getKeyword();
        queryWrapper.orderByDesc(OperationLog::getUpdateTime);
        queryWrapper.orderByDesc(OperationLog::getCreateTime);
        queryWrapper.like(OperationLog::getPath, keyword);

        OperationLogDTO operationLogDTO = new OperationLogDTO();
        operationLogDTO.setPage(this.page(page, queryWrapper));
        return operationLogDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        this.removeById(id);
    }

    @Override
    public OperationLogDTO getDateAndCountByUserId(GetDateAndCountDTO dto) {
        List<OperationLogVO> list = this.baseMapper.selectOperationLogByTime(dto, UserThreadLocal.get());
        OperationLogDTO operationLogDTO = new OperationLogDTO();
        operationLogDTO.setList(list);
        return operationLogDTO;
    }
}
