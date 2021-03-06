package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statusfulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);


        //ThreadA : A사용자 10000원 주문
        statefulService1.order("userA",10000);
        //ThreadB : B사용자 20000원 주문
        statefulService2.order("userB",20000);

        //ThreadA : 사용자A 주문 금액 조회 => 내가 원하는건 10000원 이지만 결과는 20000원 이 출력됨.
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);

        //이로 인해 항상 무상태로 설계 해야함.
        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

}