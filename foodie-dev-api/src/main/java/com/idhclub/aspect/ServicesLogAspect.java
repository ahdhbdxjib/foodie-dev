package com.idhclub.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServicesLogAspect {
    public static final Logger log = LoggerFactory.getLogger(ServicesLogAspect.class);
    /**
     * 1.前置通知：执行前
     * 2.后置通知：正常执行之后
     * 3.环绕通知：方法执行前后都会通知
     * 4.异常通知：方法在调用中发生异常，则通知
     * 5.最终通知：不管怎样，都会通知
     *
     */

    /**
     * 切面表达式
     * execution代表所要执行的表达式主体
     * 第一处*代表方法返回类型*代表所有类型
     * 第二处包名代表aop监控的类所在的包
     * 第三处，.代表该包以及其子包下的所有类方法
     * 第四处*代表类名，*代表所有类
     * 第五处*（.）*代表类中的方法名，（..）表示方法中的任何参数
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.idhclub.services.impl..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {

//        方法名和方法时间
        log.info("==========开始执行{}.{}===========",
                joinPoint.getTarget().getClass(),
                joinPoint.getSignature().getName());
        long begin = System.currentTimeMillis();
//        执行目标方法
        Object result =joinPoint.proceed();
//      结束时间
        long end = System.currentTimeMillis();

        long taskTime = end = begin;

        if(taskTime > 3000){
            log.error("============执行结束，耗时：{}毫秒==============",taskTime);

        }else if(taskTime > 2000){
            log.warn("============执行结束，耗时：{}毫秒==============",taskTime);

        }else {
            log.info("============执行结束，耗时：{}毫秒==============",taskTime);

        }

        return result;

    }
}
