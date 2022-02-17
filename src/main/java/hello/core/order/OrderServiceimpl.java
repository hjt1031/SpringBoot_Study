package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceimpl implements OrderService{

    //고정 할인 정책
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //유동 할인 정책 하지만 OCP, DIP 규칙 위반 (Discountpolicy 에 의존 해야하지만, FixDIscountPilicy 에도 의존함)
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // 구체화에 의존하지 않고 인터페이스에서만 의존되게 update(if nullpointException 발생) 파라미터로 값 을 할당해줘야함


    //관심사의 분리를 해줘야함.
    private final MemberRepository memberRepository; //회원 select
    private final DiscountPolicy discountPolicy;

    
    //생성자 주입을 통해 객체 할당(DIP 를 위반안함.)
    @Autowired
    public OrderServiceimpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); //회원 select
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
