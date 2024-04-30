package hello.core.beanfind;


import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class BeanFind {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        String[] beanNames = ac.getBeanDefinitionNames();

        for (String beanName : beanNames) {
            Object bean = ac.getBean(beanName);
            System.out.println("bean 이름 : " + beanName + "bean 정보 : " + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        String[] beanNames = ac.getBeanDefinitionNames();

        for (String beanName : beanNames) {
            BeanDefinition bean = ac.getBeanDefinition(beanName);

            if (bean.getRole() == BeanDefinition.ROLE_INFRASTRUCTURE) {
                System.out.println("bean 이름 : " + beanName + "bean 정보 : " + bean);
            }

        }
    }

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {

        MemberService memberService = ac.getBean("memberService", MemberService.class);
        System.out.println("memberService : " + memberService);
        System.out.println("memberService class : " + memberService.getClass());
        assertThat(memberService).isInstanceOf(MemberService.class);
    }


    @Test
    @DisplayName("타입으로 조회")
    void findBeanByType() {

        MemberService memberService = ac.getBean(MemberService.class);
        System.out.println("memberService : " + memberService);
        System.out.println("memberService class : " + memberService.getClass());
        assertThat(memberService).isInstanceOf(MemberService.class);
    }

    @Test
    @DisplayName("구체타입으로 조회")
    void findBeanByName2() {

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        System.out.println("memberService : " + memberService);
        System.out.println("memberService class : " + memberService.getClass());
        assertThat(memberService).isInstanceOf(MemberService.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX(){

        // 존재하지 않는 Bean 검색
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("XXXX", MemberService.class));

    }
}
