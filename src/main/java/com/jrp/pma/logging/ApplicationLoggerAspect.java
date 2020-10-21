package com.jrp.pma.logging;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApplicationLoggerAspect {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Pointcut("within(com.jrp.pma.controllers..*)")
	public void definePackagePointcuts() {
		
	}
	@Before("definePackagePointcuts()")
	//@After("definePackagePointcuts()")
	public void logBefore(JoinPoint jp) {
		log.debug("\n");
		log.debug("-----------before method execution-----------");
		log.debug("{}.{} () with argument[s] = {}",
				jp.getSignature().getDeclaringTypeName(),
				jp.getSignature().getName(),
				Arrays.toString(jp.getArgs()));
		log.debug("---------------------------------------------\n");
		
	}
	
	@Around("definePackagePointcuts()")
	//@After("definePackagePointcuts()")
	public Object logAround(ProceedingJoinPoint jp) {
		log.debug("\n");
		log.debug("-----------before method execution-----------");
		log.debug("{}.{} () with argument[s] = {}",
				jp.getSignature().getDeclaringTypeName(),
				jp.getSignature().getName(),
				Arrays.toString(jp.getArgs()));
		log.debug("---------------------------------------------\n");
		
		Object o = null;
		try {
			o = jp.proceed();
		} catch(Throwable e) {
			e.printStackTrace();
		}
		
		log.debug("-----------after method execution-----------");
		log.debug("{}.{} () with argument[s] = {}",
				jp.getSignature().getDeclaringTypeName(),
				jp.getSignature().getName(),
				Arrays.toString(jp.getArgs()));
		log.debug("---------------------------------------------\n");
		
		return o;
	}
}
