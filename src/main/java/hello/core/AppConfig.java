package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    private static MemoryMemberRepository memberRepository() { // memberRepository 역할도 드러나게 되었다.
        return new MemoryMemberRepository();
    }
    // 어디선가 AppConfig를 통해 멤버 서비스를 불러 쓴다. 멤버서비스 구현체인 객체가 생성된다.(MemberServiceImpl)
    // 그때, MemoryMemberRepository가 들어간다.
    // MemberServiceImpl을 보면, 생성자를 통해서 MemoryMemberRepository가 들어오고 memberRepository에 할당이 된다.

    // -> MemberRepository라는 역할도 보여야 하는데 안 보인다.
    // new MemoryMemberRepository 위 아래 중복도 있다.
    // Ctrl + Alt + M 하고, memberRepository  생성. return type: interface

    public OrderService orderService(){ // 이거 할 때 OrderServciceImpl에서 생성하는 new~ 부분 지우기.
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
