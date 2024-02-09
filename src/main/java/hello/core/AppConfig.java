package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration // 설정 정보, 구성 정보
public class AppConfig {

    // @Bean -> memberService -> new MemoryMemberRepository()
    // @Bean -> orderService -> new MemoryMemberRepository() --> 두 번 호출됨. 싱글톤 깨진건가...?

    // call AppConfig.memberService  -> 예상:  이거 다 순서 보장은 안 됨. 예시임.
    // call AppConfig.memberRepository
    // call AppConfig.memberRepository
    // call AppConfig.orderService
    // call AppConfig.memberRepository

    // 해 본 결과
    // call AppConfig.memberService
    // call AppConfig.memberRepository -> 3번 호출 될 줄 알았으나 한 번만 호출되었다!!!
    // call AppConfig.orderService

    @Bean // 메소드들이 스프링 컨테이너에 등록됨.
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository()); // 이거 객체 생성되면 memberRepository 호출.
                                                            // 거기에서 new MenoryMemberRepository 생성
    }

    @Bean
    public MemberRepository memberRepository() { // memberRepository 역할도 드러나게 되었다. // 이거 왜 static으로 바뀌어져 있었지,,,
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    // 어디선가 AppConfig를 통해 멤버 서비스를 불러 쓴다. 멤버서비스 구현체인 객체가 생성된다.(MemberServiceImpl)
    // 그때, MemoryMemberRepository가 들어간다.
    // MemberServiceImpl을 보면, 생성자를 통해서 MemoryMemberRepository가 들어오고 memberRepository에 할당이 된다.

    // -> MemberRepository라는 역할도 보여야 하는데 안 보인다.
    // new MemoryMemberRepository 위 아래 중복도 있다.
    // Ctrl + Alt + M 하고, memberRepository  생성. return type: interface

    @Bean
    public OrderService orderService(){ // 이거 할 때 OrderServciceImpl에서 생성하는 new~ 부분 지우기.
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
