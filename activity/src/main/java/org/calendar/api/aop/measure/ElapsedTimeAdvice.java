package org.calendar.api.aop.measure;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import static org.calendar.api.utils.Constants.TIME_TAKE_BY_METHOD_IS_MS;

@Aspect
@Slf4j
@Component
public class ElapsedTimeAdvice {

	@Around("@annotation(org.calendar.api.aop.measure.ElapsedTime)")
	public Object measureTime(final ProceedingJoinPoint point) throws Throwable {
		
		final var stopWatch = new StopWatch();
		stopWatch.start();

		final var object = point.proceed();
		stopWatch.stop();

		log.debug(TIME_TAKE_BY_METHOD_IS_MS, point.getSignature().getName(), stopWatch.getTotalTimeMillis());

		return object;
	}
}
