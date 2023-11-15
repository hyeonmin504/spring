package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MemoryMemberRepositoryTest { // 전체 테스트는 독립적으로 실행함으로 테스트간 의존성을 없애야한다
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach  // 테스트 후에 리퍼지토리를 지워줘야함 | 매서드(테스트)마다 끝날 때 마다 실행해주는 역할
    public void afterEach(){
        repository.clearStore();
    }
    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        System.out.println("result =" + (result == member)); // 이렇게 일일이 확인하기 힘들어서 assertions를 사용
        //Assertions.assertEquals(member,result); // (기댓 값 / 변수 이름)같을 때 오류 없이 진행됨
        assertThat(member).isEqualTo(member); // 더 쉽게 읽히는 코드
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); // shift + F6 을 누르면 같은 이름 한번에 편집
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get(); // .get()을 통해 optional을 풀어준다

        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll(); // 다 가져온다.

        assertThat(result.size()).isEqualTo(2);
    }
}
