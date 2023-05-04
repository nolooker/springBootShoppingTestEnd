package mycomputer;

public class MainProgram03 {

    public static void main(String[] args) {
        // 생성자를 이용하여 Constructor Injection을 수행하고 있습니다.
        Computer03 computer = new Computer03() ;

        // setter injection
        computer.setCpu("인텔 시피유");
        computer.setHdd("삼성 하드디스크");
        computer.setMainboard("엘지 메인보드");



        // loose coupling(약한 결합) : 객체를 외부에서 생성하여 생성자/셋터를 이용한 주입 방식
        // constructor injection
        Programmer03 soo = new Programmer03("김철수", "금천구 가산동", computer);
        System.out.println(soo.toString());

    }
}
