package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {

//        AppConfig appConfig = new AppConfig();
////        MemberService memberService = new MemberServiceImpl();
//        MemberService memberService = appConfig.memberService();

        // AppConfig의 환경설정 정보를 가지고 Spring이 @Bean이 붙은 클래스를 객체로 관리해준다
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        // bean이름은 기본적으로 메서드이름으로 등록된다
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        // cmd + option + v
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName());
    }
}
