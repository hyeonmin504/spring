package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); //id를 가져올 때 null을 반환하기보다 optional로 감싸서 반환
    Optional<Member> findByName(String name);
    List<Member> findAll();

}
