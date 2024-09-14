package com.discord.aspect;

import com.discord.pojo.OperationLog;
import com.discord.service.OperationLogService;
import com.discord.utils.UserThreadLocal;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

@Aspect
@Component
public class OperationLogAspect {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OperationLogService operationLogService;

    @Around("execution(* com.discord.controller..*.*(..)) && @annotation(com.discord.utils.Log)")
    public Object recordOperationLog(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        OperationLog operationLog = new OperationLog();
        operationLog.setMethod(request.getMethod());
        operationLog.setPath(request.getRequestURI());

        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();

        Object[] args = joinPoint.getArgs();

        String response;

        if (args.length > 0) {
            if (args[0] instanceof MultipartFile) {
                operationLog.setParam("file");
                response = "file";
            } else {
                String string = objectMapper.writeValueAsString(Arrays.stream(args).filter(arg -> !(arg instanceof HttpServletResponse)));
                response = result instanceof HttpServletResponse ? "file" : objectMapper.writeValueAsString(result);
                operationLog.setParam(string);
            }
        } else {
            response = objectMapper.writeValueAsString(result);
        }
        operationLog.setConsuming(end - start);
        operationLog.setResponse(response);

        operationLog.setUserId(UserThreadLocal.get());
        operationLog.setCreateTime(LocalDateTime.now());
        operationLog.setUpdateTime(LocalDateTime.now());
        CompletableFuture.runAsync(() -> operationLogService.save(operationLog));
        return result;
    }
}
