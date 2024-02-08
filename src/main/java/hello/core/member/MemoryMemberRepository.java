package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{ // 메모리에서만 하기 때문에 그냥 테스트용으로만 써야 한다.

    private static Map<Long, Member> store = new HashMap<>(); // 실무에서는 Concurrent 해시맵 사용

    @Override
    public void save(Member member) {
        store.put((member.getId()), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
