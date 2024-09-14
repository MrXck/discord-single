package com.discord.dto.operationLog;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OperationLogVO {
    private LocalDateTime createTime;
    private Integer num;
}
