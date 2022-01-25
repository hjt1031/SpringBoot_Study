package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceimpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository(); //회원 select

    //고정 할인 정책
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //유동 할인 정책 하지만 OCP, DIP 규칙 위반 (Discountpolicy 에 의존 해야하지만, FixDIscountPilicy 에도 의존함)
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // 구체화에 의존하지 않고 인터페이스에서만 의존되게 update(if nullpointException 발생) 파라미터로 값 을 할당해줘야함
    private DiscountPolicy discountPolicy;
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); //회원 select
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
