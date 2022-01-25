package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {
    /**
     * @return 할인 대상 금액 인터페이스
     */
    int discount(Member member, int price);

}
