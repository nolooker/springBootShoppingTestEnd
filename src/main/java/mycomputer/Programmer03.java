package mycomputer;

public class Programmer03 {

    private String name ;
    private String address ;
    private Computer03 computer ;

    public Programmer03(String name, String address, Computer03 computer) {
        this.name = name;
        this.address = address;
        this.computer = computer;

    }

    @Override
    public String toString() {

        String imsi = "" ;
        imsi += "Programmer Infomation\n" ;
        imsi += "name : " + this.name + "\n" ;
        imsi += "address : " + this.address + "\n" + "\n" ;
        imsi += "Computer Infomation\n" ;
        imsi += "" + this.computer.toString() + "\n" ;

        return imsi ;
    }
}
