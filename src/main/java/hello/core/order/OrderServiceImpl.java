package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor // final이 붙은 필수인 거를 가지고 셍성자를 만들어 줌
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository ;
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    } // OrderServiceImpl을 보면, MemberRepository, DiscountPolicy 인터페이스에만 의존하고 있다.
      // OrderSErvviceImpl의 입장: 누군가가 MemoryMemberRepository를 넣어줄지 DbMemberRepository를 넣어줄지 등을 전혀 모른다.
      // 마찬가지로 DiscoutnPolicy가 FixDiscountPolicy가 들어올지 RateDiscountPolicy가 들어올지 전혀 모른다.
      // -> DIP 매우 잘 지켜짐.

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); // 할인에 대한 정책은 쟤한테 맡김. 단일 책임 원칙 잘 지킴.

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }// 주문 생성 요청이 오면 회원 정보를 먼저 조회하고, 그 다음에 할인 정책에다가 회원을 넘김.
     // 등급만 넘겨도 되는데, 일단 멤버를 통으로 넘겼다.
     // 그리고 마지막으로 최종 생성된 주문을 반환.

    // 테스트 용도
    public MemberRepository getMemberRepository() { // MemberSErviceImpl, OrderServiImpl 두 군데에 같은 거 썼다. 싱글톤이 깨질까?
        return memberRepository;
    }
}
