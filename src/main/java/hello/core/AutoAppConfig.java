package hello.core;


import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) // AppConfig에 등록된 빈과 충돌되지않도록 제외. 실무에서는 딱히 제외하진 않지만 예제에서 피하기 위함
)
public class AutoAppConfig {

    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }


}
