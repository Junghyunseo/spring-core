package hello.core.member;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    // 수정 전: new 해서 했음. MemoryMemberRepository를 MemberServiceImpl이 직접 했었음.
    // 대신, AppConfig에서 만들어서 여기에 주입시킬 것이다.

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    // 가입을 하거나 회원을 찾으려면 앞서 만들었던 MemberRepository Interface가 필요하다.
    // 인터페이스만 있으면 null pointer exception 생김. 객체 생성해야 한다.
    // 이 생성자를 통해서 이 멤버 리포지토리에 구현체가 뭐가 들어갈지를 선택.
    // -> 이제 MemberServiceImpl에 MemoryMemberRepository에 대한 코드는 없다.
    // 오로지 MemberRepository라는 인터페이스만 있다.
    // -> 이제 MemberServiceImpl은 추상화에만 의존한다. (DIP 지킴)
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }
    // join에서 save를 호출하면 다형성에 의해
    // 왼쪽에 있는 MemberRepository interface가 아니라,
    // 오른쪽에 있는 MemoryMemberRepository 객체에 있는 save(오버라이드 된 save)가 호출된다.

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
