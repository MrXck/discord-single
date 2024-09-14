package com.discord.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.discord.dto.operationLog.GetDateAndCountDTO;
import com.discord.dto.operationLog.OperationLogVO;
import com.discord.pojo.OperationLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OperationLogMapper extends BaseMapper<OperationLog> {

    @Select("select DATE(create_time) create_time, count(*) num from operation_log where user_id = #{userId} and create_time >= #{dto.startDate} and create_time <= #{dto.endDate} group by DATE(create_time)")
    List<OperationLogVO> selectOperationLogByTime(GetDateAndCountDTO dto, Long userId);
}
