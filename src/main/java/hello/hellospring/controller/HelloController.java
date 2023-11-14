package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String hellohelMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }

    @GetMapping("parameter")
    public String first(@RequestParam("parame") String param, Model model){
        model.addAttribute("parame",param);
        return "firstparam"; // 내 html 이름
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String hselloString(@RequestParam("name") String names){
        return "hello" + names;
    }

    @GetMapping("hello-api") // url 경로
    @ResponseBody // 객체를 보내면 json 형식으로 반환한다
    public Hello helloApi(@RequestParam("Age") String age,@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setAge(age);
        hello.setName(name);

        return hello;
    }  // @Requestparam("?") 이 부분이 실질적으로 값을 받는 변수 이름

    static class Hello {
        private String name;
        private String age;
        public String getName() {
            return name;
        }

        public String getAge(){ //controll enter
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
