/**
 * 
 */
package com.mdh.vessel.aspect;

import java.util.Arrays;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Srini
 *
 */
@Component
@Aspect
public class Mdhaspectlog {
	
	Logger logger = LoggerFactory.getLogger(Mdhaspectlog.class);
	
	@Pointcut("within(com.mdh.vessel.service..*)")
	public void service() {
	}
	
	@Pointcut("within(com.mdh.vessel.controller..*)")
	public void controller() {
	}
	
	
	@Pointcut("execution(* *.*(..))")
	protected void allMethod() {
	}
	
	@Before("service() && allMethod()")
	public void beforeService(JoinPoint joinPoint) throws Throwable {
		logger.debug("Entering into Service "+joinPoint.getSignature());
		logger.debug("Available args :  " + Arrays.toString(joinPoint.getArgs()));

	}
	
	@AfterReturning(pointcut = "service() && allMethod()", returning = "result")
	public void servicelogAfterReturn(JoinPoint joinPoint, Object result) {
		String returnValue = this.getValue(result);
		logger.debug("logAfterReturn Service "+joinPoint.getSignature());
		logger.debug("....Method Return value : " + returnValue);
	}
	
	@AfterThrowing(pointcut = "service() && allMethod()", throwing = "exception")
	public void serviceAfterThrowing(JoinPoint joinPoint, Throwable exception) {
		logger.debug("An exception has been thrown in Service Layer \" "+joinPoint.getSignature());
		logger.debug("Cause : " + exception.getCause());
	}
	
	
	@Before("controller() && allMethod()")
	public void beforecontroller(JoinPoint joinPoint) throws Throwable {
		logger.debug("Entering into Controller "+joinPoint.getSignature());
		logger.debug("Available args :  " + Arrays.toString(joinPoint.getArgs()));

	}
	
	@AfterReturning(pointcut = "controller() && allMethod()", returning = "result")
	public void controllerlogAfterReturn(JoinPoint joinPoint, Object result) {
		String returnValue = this.getValue(result);
		logger.debug("logAfterReturn Controller "+joinPoint.getSignature());
		logger.debug("....Method Return value : " + returnValue);
	}
	
	@AfterThrowing(pointcut = "controller() && allMethod()", throwing = "exception")
	public void controllerAfterThrowing(JoinPoint joinPoint, Throwable exception) {
		logger.debug("An exception has been thrown in  Controller \" "+joinPoint.getSignature());
		logger.debug("Cause : " + exception.getCause());
	}
	
	private String getValue(Object result) {
		String returnValue = null;
		if (null != result) {
			if (result.toString().endsWith("@" + Integer.toHexString(result.hashCode()))) {
				returnValue = ReflectionToStringBuilder.toString(result);
			} else {
				returnValue = result.toString();
			}
		}
		return returnValue;
	}

}
