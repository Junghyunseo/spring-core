package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA: A 사용자 10000원 주문 원래는 멀티스레드 해야 재밌다고 한다..
        statefulService1.order("userA", 10000);
        //ThreadB: B 사용자 20000원 주문
        statefulService2.order("userB", 20000);

        //ThreadA: 사용자 A 주문 금액 조회
        int price = statefulService1.getPrice(); // A가 주문하고 금액을 조회하는 사이에 B가 중간에 끼어들어 order를 했다.
        System.out.println("price = " + price); // 이러면 2만원이 나오게 된다..
                                                // 1, 2 두 개가 이름만 다르지 어차피 같은 인스턴스를 쓴다.
                                                // == 비교하면 똑같음.
        assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

}