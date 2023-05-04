package mycomputer;

public class Programmer04 {
    private String name ;
    private String address ;
    private Computer04 computer ;

    public Programmer04(String name, String address, Computer04 computer) {
        this.name = name ;
        this.address = address ;
        this.computer = computer ;
    }

    @Override
    public String toString() {
        String imsi = "" ;
        imsi += "Programmer Infomation\n" ;
        imsi += "name : " + this.name + "\n" ;
        imsi += "address : " + this.address + "\n\n" ;

        imsi += "Computer Infomation\n" ;
        imsi += "" + this.computer.toString() + "\n" ;
        return imsi;
    }
}
