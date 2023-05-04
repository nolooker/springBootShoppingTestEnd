package mycomputer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainProgram04 {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(ComputerConfig.class) ;

        // 동일한 클래스로 여러 객체가 존재하면 getBean("객체이름은 문자열", 해당클래스이름);
        Computer04 mycom = context.getBean("helloWorld", Computer04.class);
        System.out.println(mycom.toString());

        System.out.println("=========================================================");

        // @Bean 어노테이션에 개발자가 직접 naming을 작성한 케이스
        Computer04 bluesky = context.getBean("bluesky", Computer04.class);
        System.out.println(mycom.toString());

        System.out.println("=========================================================");

        Programmer04 saram = context.getBean(Programmer04.class);
        System.out.println(saram.toString());

    }
}
