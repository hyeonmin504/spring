package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberSerivce {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberSerivce(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
     * 회원가입
     */
    public Long join(Member member){ //jpa가 들어올 때 join이 들어오면 항상 트렌젝션에서 실행되야 함
        //같은 이름이 있는 중복 회원 x
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) { // findByName 을 쓸 때는 따로 메소드를 분리시키는게 리펙토링하기 좋다 control + t -> method
        //Optional<Member> result = memberRepository.findByName(member.getName()); optional로 뽑는건 권장 x
        // result.ifPresent(m->{
        memberRepository.findByName(member.getName()) // 어차피 반환값이 optional이라서 ifpresent를 바로 사용함
                .ifPresent(m -> { // optional에서 제공하는 함수 값이 있으면 아래를 리턴
                    throw new IllegalStateException("이미 존재하는 회원입니다");
        });
    }

    /*
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
