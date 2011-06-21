/*
 * AppletExplorer.java
 *
 * Created on 8 mars 2007, 13:29
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fr.alliance.docalliance.applet;

import java.applet.Applet;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import netscape.javascript.JSObject;

/**
 *
 * @author ylr
 */
public class AppletExplorer extends Applet{
    
    /** 	
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String typeFile;
	private String servlet;
	private String host;
	private String idDiv;
	private String port;
	private String pathApp;
    
	public void init(){
		if(getParameter("typeFile")==null){
			this.typeFile="PAYE";
		}
		else{
			this.typeFile=getParameter("typeFile");
		}
		if(getParameter("host")==null){
			this.host= "localhost";
		}
		else{
			this.host= getParameter("host");
		}
		
		if(getParameter("port")==null){
			this.port= "8084";
		}
		else{
			this.port= getParameter("port");
		}
		
		if(getParameter("servlet")==null){
			this.servlet= "UploadPDF";
		}
		else{
			this.servlet= getParameter("servlet");
		}
		
		if(getParameter("pathApp")==null){
			this.pathApp= "DocAlliance";
		}
		else{
			this.pathApp= getParameter("pathApp");
		}
		if(getParameter("idDiv")==null){
			this.idDiv= "";
		}
		else{
			this.idDiv= getParameter("idDiv");
		}	
		
	}
	
    public void start(){
    	JSObject window = JSObject.getWindow(this);
    	int k = 0;
    	JFileChooser choix = new JFileChooser();
    	PDFFiltre filtre = new PDFFiltre(new String[]{"pdf"},"Tous les fichiers pdf (*.pdf)");
    	choix.setMultiSelectionEnabled(true);
    	choix.setDialogTitle("Selection des documents pdf");
    	choix.removeChoosableFileFilter(choix.getFileFilter());
    	choix.setFileFilter(filtre);
    	int retour = choix.showOpenDialog(null);
    	if(retour == JFileChooser.APPROVE_OPTION){
    		//ON RECUPERE TOUS LES FICHIERS DANS UN TABLEAU DE FICHIER
    		File[] file = choix.getSelectedFiles();
    		File[] filesOK = new File[(int) choix.getSelectedFiles().length];
    		ArrayList fileOK = new ArrayList();
    		ArrayList fileError = new ArrayList();

    		//SCAN DE LA LISTE DES FICHIERS
    		for(int i = 0 ; i < file.length ; i ++){
    			String testpdf = "";
    			String newsletter = "";

    			//ON RECUPERE LA SYNTAXE DU DEBUT DU MOT
    			if(typeFile.equals("NEWSLETTER"))
    				for(int j = 0; j < 10; j++)
    					newsletter += ""+file[i].getName().charAt(j);

    			//SI LES TROIS DERNIERES LETTRES SONT EGALES A ".pdf"
    			//ET SI LE FICHIER CONTIENT UN "_"
    			//ET SI LE CHOIX EST "PAYE" ALORS ON RECUPERE LES DONNEES
    			// OU SI LES TROIS DERNIERES LETTRES SONT EGALES A ".pdf"
    			//ET SI LE FICHIER CONTIENT "newsletter" EN DEBUT DE CARACTERE
    			//ET SI LE CHOIX EST "NEWSLETTER" ALORS ON RECUPERE LES DONNEES
    			if(file[i].getName().contains("_") && typeFile.equals("PAYE") || newsletter.equalsIgnoreCase("newsletter") && typeFile.equals("NEWSLETTER") || typeFile.equals("DIVERS") ){
    				filesOK[k]=file[i];
    				k++;
    				fileOK.add(file[i].getName());
    			}
    			//SINON DETECTION DE L'ERREUR ET TRAITEMENT
    			else
    				fileError.add(file[i].getName());
    		}

    		if(!fileError.isEmpty()){

    			String chaine1 = new String();
    			if(fileError.size() > 1)
    				chaine1 = "Certains fichiers ne repondent pas aux critères : ";
    			else
    				chaine1 = "Un fichier ne repond pas aux critères : ";

    			chaine1 += "\n"+fileError.get(0);
    			for(int i = 1; i < fileError.size(); i ++)
    				chaine1 += "\n"+fileError.get(i);
    			JOptionPane.showMessageDialog(null,chaine1);

    			String chaine2 = new String();
    			if(fileOK.size() > 1)
    				chaine2 = "Ajouter les fichiers corrects ?";
    			else
    				chaine2 = "Ajouter le fichier correct ?";

    			chaine2 += "\n"+fileOK.get(0);
    			for(int i = 1; i < fileOK.size(); i ++){
    				chaine2 += "\n"+fileOK.get(i);
    			}
    			//SI LE TABLEAU DE FICHIER CONTIENT PLUS D'UN PDF ON PROPOSE D'AJOUTER LES FICHIERS CORRECTS
    			int result = JOptionPane.showConfirmDialog(null,chaine2);
    			if(result == JOptionPane.NO_OPTION || result == JOptionPane.CANCEL_OPTION){
    				filesOK = null;
    			}
    		}
    		if(filesOK != null && filesOK.length>0){
    			for(int i = 0; i<filesOK.length; ++i){
    				this.Upload(filesOK[i]);
    			}
    		}
    	}
    	else{
    		try {
				URL urlCodeBase = new URL("http://"+this.host+":"+this.port+"/"+this.pathApp+"/"+this.servlet);
	    		HttpURLConnection servletConnection = (HttpURLConnection) urlCodeBase.openConnection();
				servletConnection.getResponseCode();
    		} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	window.eval("javascript:Reload('"+this.typeFile+"')");
		this.stop();
    	return;
    }
    
    private void Upload(File sourcelocale) {
    	try {  // Ouverture d'une communication avec la servlet 
    		URL urlCodeBase = new URL("http://"+this.host+":"+this.port+"/"+this.pathApp+"/"+this.servlet+"?fileName="+sourcelocale.getName()+"&typeFile="+this.typeFile); 

    		HttpURLConnection servletConnection = (HttpURLConnection) urlCodeBase.openConnection();
    		servletConnection.setRequestMethod("POST");
    		servletConnection.setDoInput(true); 
    		servletConnection.setDoOutput(true); 
    		servletConnection.setUseCaches(false); 
    		servletConnection.setDefaultUseCaches(false); 
    		//servletConnection.setRequestProperty("Connection","Keep-Alive");
    		servletConnection.setRequestProperty("Content-Type","application/octet-stream");
    		servletConnection.addRequestProperty("fileName", sourcelocale.getName());
    		servletConnection.addRequestProperty("typeFile",this.typeFile);
    		//upload the file  
    		OutputStream out = servletConnection.getOutputStream(); 
    		DataOutputStream dos = new DataOutputStream(out); 
    		FileInputStream fis = new FileInputStream(sourcelocale); 
    		servletConnection.connect();
    		byte[] tab = new byte[1024]; 
    		int lu = fis.read(tab); 
    		while(lu>=0) {  
    			//dos.write(tab, 0, lu);
    			out.write(tab, 0, lu);
    			lu = fis.read(tab); 
    		}  
    		dos.flush();
    		dos.close(); 
    		
    		fis.close();
    		servletConnection.getResponseCode();
    		out.close();
    		
    		servletConnection.disconnect();
    	} 
    	catch (Exception e) 
    	{  
    		e.printStackTrace(); 
    	} 
    }
    
    public void destroy(){      
    }
    
    public void stop(){
    	super.stop();
    }
}
