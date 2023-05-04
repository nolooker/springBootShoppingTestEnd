package mycomputer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 이 파일은 스프링이 설정용 파일로 인식합니다.
public class ComputerConfig {
    // 자바   jsp       스프링
    // 객체   자바bean   bean

    // 해당 클래스에 대한 객체 생성이 이루어 지는데 메소드 이름이 객체 이름이 됩니다.
    // Computer04 makeComputer = new Computer04();

    // 하지만 @Bean 옆에 문자열로 이름을 부여하면 해당 이름이 객체 이름이 됩니다.
    @Bean("bluesky") // No qualifying bean of type 'mycomputer.Computer04' available
    public Computer04 makeComputer(){
        Computer04 mycom = new Computer04();
        mycom.setCpu("삼성 시피유");
        mycom.setHdd("퀀텀 하드디스크");
        mycom.setMainboard("현대 메인 보드");
        return mycom ;
    }

    @Bean
    public Computer04 helloWorld(){
        Computer04 mycom = new Computer04();
        mycom.setCpu("인텔 시피유");
        mycom.setHdd("시게이트 하드디스크");
        mycom.setMainboard("엘지 메인 보드");
        return mycom ;
    }

    @Bean
    public Programmer04 programmer(){
        return new Programmer04("이지현", "서대문구 홍은동", this.makeComputer()) ;
    }
}
