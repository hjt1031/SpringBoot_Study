package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
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

    /* 수동빈 vs 자동빈
        => 자동 으로 동일 name 으로 등록 시 오류 발생
        => 자동 and 수동 으로 동일 name 으로 등록 시 수동 빈 이 우선권을 갖게 되고 수동빈이 자동빈을 오버라이딩 해버린다.
     */
//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
}
