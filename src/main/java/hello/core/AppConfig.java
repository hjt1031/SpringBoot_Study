package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceimpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
    제어의 역전 : 제어 흐름을 직접 제어하는 것이 아니라 외부에서 관리하는 것을 말함.
    구현 객체는 자신의 로직을 실행하는 역할만 담당. 제어흐름은 Appconfig 에서 가져간다. ex) 'OrderServiceImpl' 은 필요한 인터페이스들을 호출 하지만 어떤 구현객체들이 실행 될지 모름


    IoC컨테이너, DI(dependency injection) 컨테이너
    - AppConfig 처럼 객체를 생성하고 관리하면서 의존관계를 연결해 주는 것을 말함.
 */




/*   순수 java code
public class AppConfig {
    //  구현객체 생성 하고 연결하는 책임 클래스 (애플리케이션 전반 책임) ==> 관심사 분리
    public MemberService memberService(){
        // MemberServiceImpl 객체를 생성함과 동시에 MemoryMemberRepository 객체도 생성 (생성자 주입)
        return new MemberServiceImpl(memberRepository());
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService(){
        return new OrderServiceimpl(new MemoryMemberRepository(), discountPolicy());
    }
    //구성 영역만 변경하면 기존 코드를 변경 할 필요 없다.
    public DiscountPolicy discountPolicy() {
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
 */


// 스프링 Configuration => 설정정보(=구성정보) 싱글톤 보장
@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.MemberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceimpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}