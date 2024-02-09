package hello.core.singleton;

public class SingletonService { // 자기 자신을 내부의 프라이빗 스태틱으로 하나 가지고 있게
    // 자바가 딱 뜰 때 SingletonService의 static 영역에 자기가 내부적으로 실행해서 저 new 객체를 생성(자기를 생성)해서 instance에 참조를 넣어놓는다.
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    private SingletonService(){ // private 생성자. 외부에서 못 만들도록 함.

    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }

}
