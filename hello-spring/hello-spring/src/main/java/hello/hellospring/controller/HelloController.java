package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // 정적 컨텐츠 방식
    @GetMapping("hello") // localhost:8080 뒤에 /hello를 붙인 주소를 get
    public String hello(Model model){
        model.addAttribute("data","hello!!"); // model에서 data의 값을 hello!!로 두었다
        return "hello";  // templates에서 hello.html을 반환해준다
    }

    // mvc 방식
    @GetMapping("hello-mvc") // http://localhost:8080/hello-mvc?name=spring! -> name의 값이 spring!으로 바뀜 -> 모델에 들어감-> template으로 넘어감
    public String helloMvc(@RequestParam("name") String name, Model model){ // model이 name을 담으면 view에서 렌더링 할 떄 쓴다.
        //RequestParam 을 통해 외부에서 url 파라미터로 값(name)을 받아옴 (위의 hello 컨트롤러는 스프링이 직접 값(hello!!) 을 받아옴)
        model.addAttribute("name",name);
        return "hello-template"; //templates 밑의 hello-template로 감!
    }

    //api 방식 - 문자 반환
    @GetMapping("hello-string") // http://localhost:8080/hello-string?name=spring!
    @ResponseBody // http의 응답 body 부분에 "hello" + name 를 직접 넣겠다
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; // "hello spring!"라는 문자가 요청한 client에게 그대로 내려감.
    }

    //api 방식 - json data 반환
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // {"name":"spring!!!!!"}
    }

    static class Hello{
        private String name;

        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name=name;
        }
    }

}
