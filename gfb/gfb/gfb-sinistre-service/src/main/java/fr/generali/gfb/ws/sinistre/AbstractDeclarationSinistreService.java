/**
 * 
 */
package fr.generali.gfb.ws.sinistre;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import fr.generali.espaceclient.eai.nonpivot.LogAdminData;
import fr.generali.espaceclient.eai.nonpivot.ParamsLogAdminData;
import fr.generali.espaceclient.eai.service.IProducerService;
import fr.generali.espaceclient.proxy.generic.domain.InformationIntermediaire;
import fr.generali.espaceclient.proxy.generic.exception.GenericAccessException;
import fr.generali.espaceclient.proxy.generic.service.IGenericFct01Service;
import fr.generali.gfb.ws.sinistre.dto.IDeclarationSinistreInput;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;
import fr.generali.gfb.ws.sinistre.persistence.domain.constants.TypeDeclaEnum;
import fr.generali.gfb.ws.sinistre.persistence.domain.constants.TypeSinistreEnum;
import fr.generali.socle.exceptions.TechnicalException;

/**
 * Classe contenant des méthodes communes
 * 
 * TODO factoriser les méthodes Auto et Mrh
 * 
 * @author e19853
 * 
 */
public abstract class AbstractDeclarationSinistreService {

	/**
	 * Logger
	 */
	private static Logger LOG = LoggerFactory
			.getLogger(AbstractDeclarationSinistreService.class);

	/**
	 * client eai
	 */
	@Autowired
	private IProducerService producerService;

	@Autowired
	private ISinistreOrigineConnecteService sinistreOrigineConnecteService;

	@Autowired
	@Qualifier("genericFct01Service")
	private IGenericFct01Service genericClientService;

	@Autowired
	@Qualifier("sinistreProperties")
	protected Properties properties;

	/**
	 * Log de la déclaration de sinistre dans l'admin
	 * 
	 * Post JMS
	 * @throws GenericAccessException 
	 */
	protected void logAdmin(final IDeclarationSinistreInput input,
			TypeDeclaEnum typeDeclaEnum, TypeSinistreEnum typeSinistre, String codeRetour, InformationIntermediaire infosIntermediaire)
			throws TechnicalException {
		LogAdminData logAdminData = new LogAdminData();
		logAdminData.setIdProcess("DECLARATION_SINISTRE");
		logAdminData.setDateCreation(new Date());
		logAdminData.setIdEspaceClient(input.getAssure().getNumeroClient());
		logAdminData.setIdRCE(input.getAssure().getIdRceClient());
		logAdminData.setReseau(input.getAssure().getReseau());

		List<ParamsLogAdminData> params = new ArrayList<ParamsLogAdminData>();
		logAdminData.setParams(params);

		params.add(new ParamsLogAdminData("idContrat", input.getAssure().getNumeroContrat()));
		params.add(new ParamsLogAdminData("typeSinistre", typeDeclaEnum.name()));
		params.add(new ParamsLogAdminData("typeGarantie", typeSinistre.getCode()));
		params.add(new ParamsLogAdminData("codeCompagnie", input.getAssure().getCodeCompagnie()));
		
		if ( infosIntermediaire != null ) {
			params.add(new ParamsLogAdminData("codeIntermediaire", infosIntermediaire.getIntermediaire().getId()));
		}
		params.add(new ParamsLogAdminData("nom", input.getAssure().getNom()));
		params.add(new ParamsLogAdminData("prenom", input.getAssure().getPrenom()));
		params.add(new ParamsLogAdminData("codePostal", input.getAssure().getCodePostal()));
		params.add(new ParamsLogAdminData("email", input.getAssure().getEmail()));
		
		try {
			producerService.sendMessageDemandeLogAdmin(logAdminData);
		} catch (Exception e) {
			throw new TechnicalException(" logAdmin :: ", e);
		}
	}

	/**
	 * 
	 */
	protected void sendDeclarationSinistre(IDeclarationSinistreInput input, DeclarationSinistre declarationSinistre,
			TypeDeclaEnum typeDeclaEnum, TypeSinistreEnum typeSinistre, String mailApporteur,
			boolean isClientInternet,
			InformationIntermediaire infosIntermediaire, String mailTrieste,
			boolean isCourtier) {
		String codeRetour = null;
		
		// if("67".equals(declarationSinistre.getCodeCompagnie())){
		// reseau = "SALARIE";
		// }
		Exception exception = null;
		// Traiter le sinistre
		try {
			switch (typeDeclaEnum) {
			case AUTO:
				sinistreOrigineConnecteService.traitementSinistreAuto(
						declarationSinistre,  mailApporteur, isClientInternet,
						infosIntermediaire, mailTrieste, isCourtier);
				break;
			case MRH:
				sinistreOrigineConnecteService.traitementSinistreMrh(
						declarationSinistre,  mailApporteur, isClientInternet,
						infosIntermediaire, mailTrieste, isCourtier);
				break;
			default:
				throw new TechnicalException(" sendDeclarationSinistre :: "
						+ typeDeclaEnum + " : typeDeclaEnum non géré ");
			}
		} catch (Exception e) {
			exception = e;
			codeRetour = "KO";
			LOG.error(" sendDeclarationSinistre  :: ", e);
		}

		logAdmin(input, typeDeclaEnum, typeSinistre, codeRetour, infosIntermediaire);

		if (exception != null) {
			throw new TechnicalException(" sendDeclarationSinistre  :: ",
					exception);
		}

	}

	/**
	 * 
	 * @param declarationSinistre
	 * @return
	 * @throws GenericAccessException
	 */
	protected InformationIntermediaire recupererInfosIntermediaire(
			DeclarationSinistre declarationSinistre)
			throws GenericAccessException {
		InformationIntermediaire infosIntermediaire = genericClientService
				.getInfosPortefeuille(declarationSinistre.getCodeCompagnie(),
						declarationSinistre.getCodePortefeuille());
		return infosIntermediaire;
	}

}
