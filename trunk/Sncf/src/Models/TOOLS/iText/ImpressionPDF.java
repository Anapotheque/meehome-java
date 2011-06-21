package Models.TOOLS.iText;

import Models.TOOLS.Controleur.MegaControleur;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;

//RABALLAND Romain v3.0

public class ImpressionPDF extends MegaControleur{
    
    public static void ImprimePDF(String name, String nomPDF) {
        final String PATH_ADOBE_READER = "C:\\Program Files\\Adobe\\Acrobat 7.0\\Reader\\AcroRd32.exe";
        final String ADOBE_READER_PRINT_COMMAND = "/t";
        final String SLASH = "/";
        final String QUOTE = "\"";
        final String SPACE = " ";
        final String pFile =""+cheminEnregistrementPDF+name+"_"+nomPDF+".pdf";
        PrintService service = PrintServiceLookup.lookupDefaultPrintService();//localisation de l'imprimante par défaut
        
        //Commande à exécuter
        String lCommand = QUOTE + PATH_ADOBE_READER + QUOTE + SPACE +
                ADOBE_READER_PRINT_COMMAND + SPACE +
                QUOTE + pFile + QUOTE + SPACE +
                QUOTE + service.getName() + QUOTE;//service.getName() c'est l'imprimante par defaut
        
        Process lAdobeProcess = null;
        
        try {
            
            //Execute Adobe Reader command "/t" (imprime et ferme)
            lAdobeProcess = Runtime.getRuntime().exec(lCommand);
            
            //dors pendant 2.4seconde avant de fermer la fenêtre de Acrobat Reader
            Thread.sleep(1000);
            lAdobeProcess.destroy();
        } catch (Exception e) {
            System.err.println("[printPDF] Error printing PDF : " + pFile);
            e.printStackTrace();
        } finally {
            if (lAdobeProcess != null) {
                //destruction de l'instance de Acrobat Reader
                lAdobeProcess.destroy();
                lAdobeProcess = null;
            }
        }
    }
}
    
