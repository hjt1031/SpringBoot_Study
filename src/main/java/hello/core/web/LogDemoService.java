package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
// RequiredArgsConstructor => 생성자가 1개이면 자동으로 의존관계 주입
public class  LogDemoService {
    // ObjectProvider 스프링 컨테이너에게 요청하는 것 을 지연 시킴.
    // private final ObjectProvider<MyLogger> myLoggerProvider;

    // 프록시 모드
    private final MyLogger myLogger;

    public void logic(String id) {
        // ObjectProvider 를 사용했을때
        // MyLogger myLogger = myLoggerProvider.getObject();

        // 프록시 모드
        myLogger.log("service id =" + id);
    }
}
