package hello.core.singleton;

public class SingletonService {
    // 자기 자신 class 를 선언하면 클래스 레벨에 올라감.
    private static final SingletonService instance = new SingletonService();
    // 이 객체 인스턴스가 필요하면 오직 getInstance 메서드를 통해서만 조회할 수 있다.
    public static SingletonService getInstance(){
        return instance;
    }

    private SingletonService(){

    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
