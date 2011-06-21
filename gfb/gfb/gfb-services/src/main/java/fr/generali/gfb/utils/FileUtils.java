/**
 * Generali Solutions d'assurances - Tous droits réservés &copy; 2007 - 2010
 */
package fr.generali.gfb.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Classe utilitaire sur les fichiers
 */
public class FileUtils {

    /**
     * Retourne sous format String un fichier
     * 
     * @param file fichier File
     * @return String fichier au format String
     * @throws IOException Erreur d'ouverture du fichier
     */
    public static String readFileToString(File file) throws IOException {

        StringBuffer sb = new StringBuffer();
        BufferedReader reader = null;

        reader = new BufferedReader(new FileReader(file));
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        reader.close();

        return sb.toString();
    }
}
