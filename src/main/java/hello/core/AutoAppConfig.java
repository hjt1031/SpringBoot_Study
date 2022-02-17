package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
//컨포넌트 스캔을 사용하면 @Configuration 이 붙은 설정 정보도 자동으로 등록 되기때문에 기존 AppConfig 를 빼주기위해 Filter 사용
@ComponentScan(
        // 하위 패키지 검색
        basePackages = "hello.core.member",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}
