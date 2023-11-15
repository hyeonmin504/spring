package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; //자동으로 부여되는 id
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //null이 반환될 가능성이 있으면 optional을 통해 감싼다 -> 클라이언트에서 뭘 한다
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) // name과 같은 이름을 store에서 찾는다
                .findAny();
    }

    @Override
    public List<Member> findAll() { // 실무에선 리스트 많이 쓴다
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
