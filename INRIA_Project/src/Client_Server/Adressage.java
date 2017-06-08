package Client_Server;

import java.net.InetAddress;

import java.net.UnknownHostException;


/*
 * Sert à récupérer l'adresse local et celle d'un site internet
 */
public class Adressage {

    public static void main(String[] zero) {

        InetAddress LocaleAdresse ;
        InetAddress ServeurAdresse;
        
        try {
            LocaleAdresse = InetAddress.getLocalHost();
            System.out.println("L'adresse locale est : "+LocaleAdresse.getHostAddress()); 
            ServeurAdresse= InetAddress.getByName("www.test.com");
            System.out.println("L'adresse du serveur du site est : "+ServeurAdresse.getHostAddress());
        }
        catch (UnknownHostException e) {
        	e.printStackTrace();
        }
    }
}