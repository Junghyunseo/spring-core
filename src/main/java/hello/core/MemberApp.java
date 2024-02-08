package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {

        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService(); // MemberService 달라고 하면 appconfig에서 멤버 서비스 인터페이스를 준다.
        // 그러면 MemberService에는 memberServiceImpl이 들어있겠지.
        // MemberService memberService = new MemberServiceImpl(); 기존에 MemberServiceImpl을 메인 메소드에서 직접 생성해줬다.
        // 그리고 MemberServiceImpl에서 또 MemoryMemberRepository를 생성했음.

        // AppConfig 사용 대신 Spring 사용하는 버전. 스프링 컨테이너라고 보면 됨.
        // 객체들을 다 관리해 줄 친구. @Bean 이런 애들을
        // AppConfig에 있는 환경설정 정보를 가지고 스프링이 @Bean 붙은 것들 객체 생성한 거 다 컨테이너에 집어넣어서 관리해 줌.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);// memberService 객체(@Bean 메소드 이름)를 찾을 것이다. 두 번째 인자는 반환 타입
        // spring container한테 이름 주고 꺼내는 느낌

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.printf("find Member = " + findMember.getName());

    }
}
