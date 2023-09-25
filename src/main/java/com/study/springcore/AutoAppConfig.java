package com.study.springcore;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( // @Component가 붙은 클래스를 찾아서 다 스프링 빈으로 등록해줌
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
) // 이전까지 썼던 @Configuration 들을 제외시켜줌.
public class AutoAppConfig {

}
