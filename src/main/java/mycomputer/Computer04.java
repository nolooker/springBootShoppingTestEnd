package mycomputer;

import lombok.Setter;

@Setter
public class Computer04 {
    
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
