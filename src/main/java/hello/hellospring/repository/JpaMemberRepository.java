package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional //jpa를 쓰려면 항상 트렌젝션이 있어야 한다. 데이터를 저장 ,변경할 때 필요
public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em){ //엔티티메니저를 쓰려면 주입받아야 한다.
        this.em = em;
    }
    @Override
    public Member save(Member member) {
        em.persist(member); //영구 저장하다.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) { //조회
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name",name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() { // 멤퍼 엔티티를 대상으로 쿼리를 날린다 , 원래 m대신 * 를 쓰는데 멤버 객체 자체를 셀릭트 해서 m을 쓴다.
        return em.createQuery("select m from Member m",Member.class)
                .getResultList();

    }
}
