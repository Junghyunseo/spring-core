package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(  //-> 스프링 빈을 쫙 긁어가지고 자동으로 스프링빈으로 끌어올려야 함.
                //->@Component 붙은 클래스를 찾아가지고 다 자동으로 스프링 빈으로 등록해줌.
        basePackages = "hello.core.member", // 이러면 member와 그 하위를 찾음. member만 대상이 됨. discoutn, order에 대한 거 등록 안 됨.
        basePackageClasses = AutoAppConfig.class,
        excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, classes = Configuration.class)
        // -> Component scan으로 다 뒤져서 스프링 빈으로 등록하는데 그 중에서 뺄 거 지정, 쟤는 수동으로 등록하는거니까 빼줌.
)
public class AutoAppConfig {

}
