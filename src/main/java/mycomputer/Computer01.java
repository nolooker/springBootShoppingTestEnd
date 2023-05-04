package mycomputer;

import lombok.Setter;

//@Getter
@Setter
public class Computer01 {

    private String cpu ;
    private String hdd ;
    private String mainboard ;

    @Override
    public String toString() {
        String imsi = "" ;

        imsi += "cpu info : " + this.cpu + "\n" ;
        imsi += "hdd info : " + this.hdd + "\n" ;
        imsi += "mainboard info : " + this.mainboard + "\n" ;

        return imsi ;
    }
}
