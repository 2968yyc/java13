package com.erp.aop;

import com.erp.annotation.UpdateMethod;
import com.erp.bean.device.Info;
import com.erp.utils.PermissionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Author: yyc
 * @Date: 2019/5/20 13:20
 */
@Component
@Aspect
public class UpdateAspect  {

    @Pointcut("@annotation(com.erp.annotation.UpdateMethod))")
    public void updatePointcut(){}

    @Around("updatePointcut()")
    public Object permissionCheck(ProceedingJoinPoint proceedingJoinPoint)throws Throwable{
        Class<?> targetClass = proceedingJoinPoint.getTarget().getClass();
        Method[] methods = targetClass.getMethods();
        for (Method method : methods) {
            boolean isAnnnotated = method.isAnnotationPresent(UpdateMethod.class);
            if (isAnnnotated){
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                //判断注解方法的字段类型，做对应的处理
                String name = method.getName();
                if (name.contains("update")){
                    UpdateMethod updateMethod = method.getAnnotation(UpdateMethod.class);
                    String domainName = updateMethod.value();
                    Map<String, String> res = PermissionUtils.permissionCheck(domainName + ":edit", request);
                    if (!res.isEmpty())
                        return new Info(0,res.get("msg"),null);
                    else {
                        Object proceed = proceedingJoinPoint.proceed();
                        return proceed;
                    }
                }
                //如果有其他方法在这里补充
            }

        }
        return proceedingJoinPoint.proceed();
    }

}
