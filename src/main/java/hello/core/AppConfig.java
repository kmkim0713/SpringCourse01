package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

// 객체의 생성은 AppConfig에서 한다
// 서비스단에서는 구체 클래스를 몰라도 된다
public class AppConfig {

    // DIP를 지킨다.
    // MemberServiceImpl에서 인터페이스를 사용하고 구현체를 직접 선택했던 부분을 바꾸고
    // 인터페이스만 생성하고 생성자 주입을 통해 구현체를 주입하는 것으로 변경한다
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    // OrderServiceImpl 입장에서는 생성자에 어떤 할인 레포지토리가 들어올지 알 수 없다
    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }

}
