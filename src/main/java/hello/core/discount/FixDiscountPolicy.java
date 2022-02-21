package hello.core.discount;


import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
public class FixDiscountPolicy implements DiscountPolicy{ //구현체 생성
    // 정액 할인 정책
    private int discountFizAmount = 1000; // 1000원 할인
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){ //enum 타입은 == 써야함
            return discountFizAmount;
        }else{
            return 0;
        }
    }
}
