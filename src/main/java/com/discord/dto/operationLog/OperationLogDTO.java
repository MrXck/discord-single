package com.discord.dto.operationLog;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.discord.pojo.OperationLog;
import lombok.Data;

import java.util.List;

@Data
public class OperationLogDTO {

    private Page<OperationLog> page;

    private List<OperationLogVO> list;
}
