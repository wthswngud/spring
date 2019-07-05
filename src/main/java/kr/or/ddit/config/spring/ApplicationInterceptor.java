package kr.or.ddit.config.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration	//설정파일임을 알리는 어노테이션
public class ApplicationInterceptor extends WebMvcConfigurerAdapter{

	/*
	 	<!-- localeChangeInterceptor -->
		<mvc:interceptor>
			<mvc:mapping path="/**"/>
			<bean class="org.springframework.web.servlet.i18n.LocaleChangeInterceptor">
				<property name="paramName" value="lang"/>
			</bean>
		</mvc:interceptor>
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		
		registry.addInterceptor(lci).addPathPatterns("/**");
	}
}
