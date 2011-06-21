/**
 * Generali Solutions d'assurances - Tous droits réservés &copy; 2007 - 2010
 */
package fr.generali.gfb.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;

/**
 * Classe utilitaire sur les ressources
 */
public class ResourceUtils {

    /**
     * Retourne sous format String une resource
     * 
     * @param location path de la ressource
     * @return String fichier au format String
     * @throws IOException
     * @throws IOException Erreur sur l'ouverture/parcours de la ressource
     */
    public static String readResourceToString(String location) throws IOException {
        FileSystemResourceLoader resourceLoader = new FileSystemResourceLoader();
        Resource resource = resourceLoader.getResource(location);

        Reader reader = null;
        BufferedReader fichier;

        reader = new InputStreamReader(resource.getInputStream());
        StringBuffer sb = new StringBuffer();

        String line = null;
        fichier = new BufferedReader(reader);

        // Lecture de la ressource
        line = fichier.readLine();
        while (line != null) {
            sb.append(line);
            line = fichier.readLine();
        }

        fichier.close();
        reader.close();

        return sb.toString();
    }
}