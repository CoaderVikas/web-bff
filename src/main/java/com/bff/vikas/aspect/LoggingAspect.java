package com.bff.vikas.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;



/**
 * Description: this class is responsible for logging method execution
 * @author vikas
 * @created on 26 Dec 2025
 * @version 1.0
 */
/*@Component
@Aspect
@Slf4j
public class LoggingAspect {

	
	//@Around("execution(* com.bff.vikas..*(..))")
	@Around("execution(* com.bff.vikas.feign.controller..*(..)) || execution(* com.bff.vikas.feign.service..*(..))")
	public Object trackMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
		String className = joinPoint.getTarget().getClass().getSimpleName();
		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		long startTime = System.currentTimeMillis();

		try {
			log.info("**** ENTER | {}.{}() | args={}", className, methodName, Arrays.toString(args));
			Object result = joinPoint.proceed();
			long endTime = System.currentTimeMillis();
			log.info("**** EXIT  | {}.{}() | time={} ms", className, methodName, (endTime - startTime));
			return result;
		} catch (Exception e) {
			log.error("**** ERROR | {}.{}() | exception={}", className, methodName, e.getMessage(), e);
			throw e;
		}
	}
	
	@AfterThrowing(pointcut = "execution(* com.vikas.auth.service.*.*(..))", throwing = "ex")
	public void logServiceError(JoinPoint joinPoint, Exception ex) {
	    // Service layer ke error logging ko hata dein ya comment kar dein
	    // Taaki sirf Controller wala error hi dikhe
	}
}*/
