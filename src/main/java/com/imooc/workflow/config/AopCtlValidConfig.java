package com.imooc.workflow.config;

import com.imooc.workflow.utils.ResultCode;
import com.imooc.workflow.utils.ResultVO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/**
 * @PackageName: com.imooc.workflow.config
 * @CalssName: AopCtlValidConfig
 * @Description: 针对Controller入参校验的Aop配置
 * @Auther: dcm
 * @Date: 2018-6-11 17:29
 */

@Aspect
@Component
public class AopCtlValidConfig {

    private static final Logger log = LoggerFactory.getLogger(AopCtlValidConfig.class);

    @Before("execution(* com.imooc.workflow.controller..*Ctl.*(..)) && @annotation(com.imooc.workflow.config.CtlValidAnnotation)")
    @Order(11) // 序号越小的优先级越高，先执行
    public Object handleCtlValid(JoinPoint joinPoint) {

        ResultVO<Object> result = new ResultVO();
        BindingResult validResult ;
        Object form = new Object();

        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof BindingResult) {
                validResult = (BindingResult) arg;
                if (validResult.hasErrors()){
                    result.setStatus(ResultCode.PMP_400);
                    result.setMessage(ResultCode.PMP_BAD_REQUEST + ": " + validResult.getFieldError().getDefaultMessage());
                }
            }
            if (arg.getClass().getName().contains("com.sdcm.common.entity")) {
                form = arg;
            }
        }

        return result;
    }

}
