package userclasses.paramreseau;

public class ParametresReseau {
     
 // private static String ADRESSESERVEUR      = "192.168.12.135";
    private static String ADRESSESERVEUR      ="127.0.0.1";
    private static String PORT                = "8080";
    private static String APPLI               = "WebAppliGC_PPE_MP_1";
    private static String ENTREE_SERVICE      = "rest";
    
    public  static String RACINE_SERVICE_REST = "http://"+ADRESSESERVEUR+":"+PORT+"/"+
                                                 APPLI+"/"+ENTREE_SERVICE;          
}


