package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService(); // MemberService 달라고 하면 appconfig에서 멤버 서비스 인터페이스를 준다.
        // 그러면 MemberService에는 memberServiceImpl이 들어있겠지.
        // MemberService memberService = new MemberServiceImpl(); 기존에 MemberServiceImpl을 메인 메소드에서 직접 생성해줬다.
        // 그리고 MemberServiceImpl에서 또 MemoryMemberRepository를 생성했음.
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.printf("find Member = " + findMember.getName());

    }
}
