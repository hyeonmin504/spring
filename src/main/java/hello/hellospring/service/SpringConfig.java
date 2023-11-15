package hello.hellospring.service;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//컨테이너에 직접 넣어두는 것
//상황에 따라 구현 클래스를 변경해야 하는 설정들을 Bean을 통해 구현한다
/*
@Configuration
public class SpringConfig {
    @Bean // -> @Service
    public MemberSerivce memberSerivce(){
        return new MemberSerivce(memberRepository());
    }

    @Bean // -> @Repository
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
*/