package kr.or.ddit.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class ProfilingAspect {
	private static final Logger logger = LoggerFactory.getLogger(ProfilingAspect.class);

	@Pointcut("execution(* kr.or.ddit..service.*.*(..))")
	public void dummy() {}
	
	
//	public long startTime(JoinPoint joinpoint) {
//		long startTime = System.currentTimeMillis();
//		logger.debug("startTime : {}", startTime);
//
//		return startTime;
//	}
//
//	public long endTime(JoinPoint joinpoint) {
//		long endTime = System.currentTimeMillis();
//		logger.debug("endTime : {}", endTime);
//
//		long time = endTime - startTime(joinpoint);
//		logger.debug("time : {}", time);
//
//		return endTime;
//	}

	@Around("dummy()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();
		logger.debug("startTime : {}", startTime);
		
		
		logger.debug("method name 비즈니스 로직: {}", joinPoint.getSignature().getName());
		Object[] methodArgs = joinPoint.getArgs();
		Object returnObj = joinPoint.proceed(methodArgs);
		
		
		
		long endTime = System.currentTimeMillis();
		logger.debug("endTime : {}", endTime);
		
		long time = endTime - startTime;
		logger.debug("time : {}", time);
		
		return returnObj;
	}
}
