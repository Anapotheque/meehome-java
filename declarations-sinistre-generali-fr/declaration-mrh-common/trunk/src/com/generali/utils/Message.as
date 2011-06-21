package com.generali.utils {
	
	public class Message {
		
		// Adresse
		public static const MSG_EMPTY_ADDRESS:String = "L'adresse est obligatoire, merci de la saisir";
		public static const MSG_EMPTY_ZIPCODE:String = "Le code postal est obligatoire, merci de le saisir";
		public static const MSG_BAD_ZIPCODE:String = "Le code postal doit être composé de 5 chiffres, merci de le saisir à nouveau.";
		public static const MSG_EMPTY_CITY:String = "La ville est obligatoire, merci de la saisir";
		
		// Date du sinistre
		public static const MSG_FORMAT_DATE:String = "La date du sinistre doit être conforme au format (jj/mm/aaaa), merci de la saisir à nouveau."
		public static const MSG_BAD_DATE:String = "La date du sinistre doit être antérieure ou égale à la date du jour."
		public static const MSG_EMPTY_DATE:String = "La date du sinistre est obligatoire, merci de la saisir à nouveau.";
		
		// Assuré --> TODO : metre tous ces messages dans les *.as
		public static const MSG_1:String	= "Votre nom est obligatoire, merci de le saisir.";
		public static const MSG_2:String	= "Votre prénom est obligatoire, merci de le saisir.";
		public static const MSG_3:String	= "Votre adresse est obligatoire, merci de la saisir.";
		public static const MSG_4_1:String	= "Votre code postal est obligatoire, merci de le saisir.";
		public static const MSG_4_2:String	= "Votre code postal doit être composé de 5 chiffres, merci de le saisir à nouveau.";
		public static const MSG_5:String	= "Votre ville est obligatoire, merci de la saisir.";
		public static const MSG_6_1:String	= "Votre mail est obligatoire, merci de le saisir.";
		public static const MSG_6_2:String	= "Votre adresse email n’est pas valide, merci de la saisir à nouveau.";
		public static const MSG_7_1:String	= "Au moins un numéro de téléphone est obligatoire, merci de le saisir.";
		public static const MSG_7_2:String	= "Votre n° de téléphone mobile doit être composé de 10 chiffres, merci de le saisir à nouveau.";
		public static const MSG_8:String	= "Votre n° de téléphone domicile doit être composé de 10 chiffres, merci de le saisir à nouveau.";
		public static const MSG_9:String	= "Votre n° de téléphone bureau doit être composé de 10 chiffres, merci de le saisir à nouveau.";
		public static const MSG_10_1:String	= "Votre numéro de contrat est obligatoire, merci de le saisir.";
		public static const MSG_10_2:String	= "Votre numéro d'immatriculation est obligatoire, merci de le saisir.";
		
		// Sinistre
		public static const MSG_11_1:String	= "La date du sinistre est non valide, merci de la saisir à nouveau.";
		public static const MSG_11_2:String	= "La date du sinistre doit être antérieure ou égale à la date du jour, merci de la saisir.";
		public static const MSG_11_3:String	= "La date du sinistre est obligatoire, merci de la saisir.";
		public static const MSG_12:String	= "L'heure de début de sinistre est supérieure à l'heure de fin de sinistre, merci de les saisir à nouveau.";
	}
}