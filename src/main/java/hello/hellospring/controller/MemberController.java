package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//Dependency injection 컨테이너에서 의존성을 주입해주는 느낌
//메인페이지와 동일한 레벨의 패키지나 하위 페키지에 싱글톤으로 적용
@Controller // 스프링 컨테이너 통에 멤버 컨트롤러(membercontroller) 객체를 생성해서 넣어둔다
public class MemberController {
    private final MemberSerivce memberSerivce;

    @Autowired //컨테이너에 컨트롤러 객체를생성한다음 생성자에 autowired가 있으면 membercontroller가 컨테이너에 있는 memberservice와 연결해준다
    public MemberController(MemberSerivce memberSerivce) {
        this.memberSerivce = memberSerivce;
    }

    @GetMapping("/members/new")//return 경로로 이동 |조회
    public String reateForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new") //post방식으로 여기로 이동 |등록
    public String create(MemberForm form){ //spring이 memberForm에 있는 name에 이름을 저장한다
        Member member = new Member();
        member.setName(form.getName());

        memberSerivce.join(member);

        return "redirect:/";
    }
    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberSerivce.findMembers();
        model.addAttribute("members",members); //모델에 members를 담아서 보낸다 return으로 보낸다
        return "members/memberList";
    }
}
