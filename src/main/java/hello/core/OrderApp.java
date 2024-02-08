package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {

        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService();
        //OrderService orderService = appConfig.orderService();
        // AppConfig에서 OrderServiceImpl이
        // MemoryMemberRepository와 FixDiscoutPolicy를 참조하도록 완성시키고 완성된 OrderServiceImpl 객체를 반환한다.

        //MemberService memberService = new MemberServiceImpl(null);
        //OrderService orderService = new OrderServiceImpl(null,null);

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
        // -> @Bean에서 orderService에서 생성한 객체가 튀어나오는 거

        Long memberId =1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member); // 멤버 서비스를 통해 일단 메모리 객체에 넣어야 함.

        Order order = orderService.createOrder(memberId,"itmeA",20000);

        System.out.println("order= " + order);
    }
}
