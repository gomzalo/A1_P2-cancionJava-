package p2g3cancionj;


import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 *
 * @author G
 */

public class comunicacion {
    public static PanamaHitek_Arduino ino = new PanamaHitek_Arduino();
    public static String opcion = "";
    static BasicPlayer sonido = new BasicPlayer();
    
    public static void main(String [ ] args){
            
            com();
    }
    
    static String com(){
        String opc = "";
        SerialPortEventListener listener =  new SerialPortEventListener (){
            //String opcion = "";
            @Override
            public void serialEvent(SerialPortEvent spe){
                try {
                    if(ino.isMessageAvailable()){
                        
                        opcion = ino.printMessage();
                        System.out.println(opcion) ;
                            eligeCancion(opcion);
                            
                    }
                } catch (SerialPortException ex) {
                    Logger.getLogger(comunicacion.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ArduinoException ex) {
                    Logger.getLogger(comunicacion.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }   
        };
        opc = opcion;
        try {
            ino.arduinoRXTX("COM4", 9600, listener);
        } catch (ArduinoException ex) {
            Logger.getLogger(comunicacion.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return opc;
    }
    
    static void eligeCancion(String op){
        switch(op){
            case "0":
                reproduciraudio("src/canciones/One.mp3");
                break;
            case "1":
 
                reproduciraudio("src/canciones/ChopSuey.mp3");
                break;
            case "2":
                reproduciraudio("src/canciones/HotelCalifornia.mp3");
                break;
            case "3":
        {
            try {
                sonido.stop();
            } catch (BasicPlayerException ex) {
                Logger.getLogger(comunicacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;
        }
    }
    
    static void enviar(String dato){
        try {
            ino.sendData(dato);
        } catch (ArduinoException ex) {
            Logger.getLogger(comunicacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SerialPortException ex) {
            Logger.getLogger(comunicacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void reproduciraudio(String file){

    try {

           

            sonido.open(new java.io.File(file));
            
            sonido.play();
            
            
            
            

     } catch (Exception e) {
            
            System.out.println(e);

     }

}
}
