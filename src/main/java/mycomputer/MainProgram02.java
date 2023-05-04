package mycomputer;

public class MainProgram02 {

    public static void main(String[] args) {

        // 생성자를 이용하여 Constructor Injection을 수행하고 있습니다.
        Programmer02 soo = new Programmer02("김철수", "금천구 가산동");
        System.out.println(soo.toString());

        System.out.println("==================================================");

        OfficeWorker02 hee = new OfficeWorker02("박영희", "동대문구 제기동");
        System.out.println(hee.toString());


    }
}
