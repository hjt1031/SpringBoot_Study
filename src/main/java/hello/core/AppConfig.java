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

