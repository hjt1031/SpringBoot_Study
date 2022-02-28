package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest(){
        // ConfigurableApplicationContext => close 메서드를 사용하려면 필요.
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }
    @Configuration
    static class LifeCycleConfig {
        /*
            스프링 빈은 "객체생성 후 의존관계 주입이 일어 난다."
            스프링 빈은 객체를 생성하고, 의존관계 주입이 다 끝난 다음에야 필요한 데이터를 사용할 수 있는 준비가 완료된다.
            
            스프링은 스프링 컨테이너가 종료되기 직전에 소멸 콜백을 준다.
            * 스프링 빈의 이벤트 라이프 사이클
                => 스프링컨테이너 생성 - 스프링 빈 생성 - 의존관계 주입(수정자,필드 injection) - 초기화 콜백 - 사용 - 소멸전 콜백 - 스프링 종료

            생성자한테 파라미터를 넘겨줘서 실행하면 되지 않을까? 왜 분리? why?
                => 객체의 생성과 초기화를 분리하는게 좋다. (유지보수 관점에서 좋기 때문에)
         */

        //콜백 : 생성자자 빈 등록, 초기화, 소멸 메서드
//       @Bean(initMethod = "init", destroyMethod = "close")
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spirng-dev");
            return networkClient;
        }
    }
}
