package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberSerivceTest { //테스트를 원하는 곳에서 command shift t -> 자동으로 테스트 코드파일을 만들어줌
/*
    MemberSerivce memberSerivce = new MemberSerivce();
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    외부에서 여는 리파지토리여서 Map이 static이 아니면 문제가 생길 수 있음
*/
    MemberSerivce memberSerivce;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){ //직접 new하지 않고 외부에서 넣어주는 것을 dependency injection(DI)라고 한다
        memberRepository = new MemoryMemberRepository();
        memberSerivce = new MemberSerivce(memberRepository);
    }
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }
    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberSerivce.join(member);

        //then
        Member findMember = memberSerivce.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
        //앞에와 뒤에가 같냐는 코드
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberSerivce.join(member1);
        assertThrows(IllegalStateException.class, ()-> memberSerivce.join(member2));
        // ()-> 함수가 실행하는데 illegallstateexception이 터져야하는 정상인 코드
/*
        try {
            memberSerivce.join(member2);
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/


        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}