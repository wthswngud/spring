package kr.or.ddit.config.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/*
 	<!-- Controller, @Service, @Repository, @Component -->
	<context:component-scan base-package="kr.or.ddit" use-default-filters="false">
		<context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
		<context:include-filter type="annotation" expression="org.springframework.web.bind.annotation.ControllerAdvice"/>
	</context:component-scan>
 */

// @Service, @Repository 만 스캔 할 것이다.
@ImportResource({"classpath:kr/or/ddit/config/spring/application-scheduler.xml",
				"classpath:kr/or/ddit/config/spring/application-batch.xml"})
@Configuration
@ComponentScan(basePackages = "kr.or.ddit",
			   useDefaultFilters = false,
			   includeFilters = @Filter(type=FilterType.ANNOTATION,
					   					classes = {Service.class, Repository.class})
			  )
public class RootContext {
	
}
