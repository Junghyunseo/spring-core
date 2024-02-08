package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;

public class MemberServiceTest {

    //MemberService memberService = new MemberServiceImpl();
    MemberService memberService;

    @BeforeEach // 각 테스트 실행 전에 무조건 실행되는 것
    public void beforeEach(){
        AppConfig appConfig = new AppConfig(); // 테스트 실행 전에 appConfig 만들고,
        memberService = appConfig.memberService(); // 멤버 서비스 할당, 그리고 테스트 돌아감.
    }


    @Test
    void join(){
        //given
        Member member =new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember);

    }
}
