package hello.core.member;

public interface MemberRepository {

    void save(Member member); // 회원 저장하는 것

    Member findById(Long memberId); // 회원의 id로 회원을 찾는 기능
}
