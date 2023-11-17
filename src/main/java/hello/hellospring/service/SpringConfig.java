package hello.hellospring.service;

import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//컨테이너에 직접 넣어두는 것
//상황에 따라 구현 클래스를 변경해야 하는 설정들을 Bean을 통해 구현한다


@Configuration
public class SpringConfig {
    // private final DataSource dataSource; // JDBC를 이용할 때
    /*
    @Bean // -> @Service
    public MemberSerivce memberSerivce(){
        return new MemberSerivce(memberRepository());
    }
    */

    /*@Autowired
    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }*/

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em){
        this.em = em;
    }

    @Bean // -> @Repository
    public MemberRepository memberRepository(){
        //return new MemoryMemberRepository();
        return new JpaMemberRepository(em);
    }
}