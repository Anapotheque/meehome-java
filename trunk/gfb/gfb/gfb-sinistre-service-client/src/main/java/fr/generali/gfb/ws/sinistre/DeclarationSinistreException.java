package fr.generali.gfb.ws.sinistre;

import fr.generali.gfb.ws.sinistre.dto.IDeclarationSinistreInput;
import fr.generali.socle.exceptions.BusinessException;

/**
 * Exception levée lorsque le service de déclaration de sinistre a eu un
 * problème à l'exécution
 */
public class DeclarationSinistreException extends BusinessException {

    /**
     * 
     */
    private static final long serialVersionUID = 4586800729856546706L;

    private IDeclarationSinistreInput input;

    public DeclarationSinistreException(Throwable cause, IDeclarationSinistreInput input) {
        super(cause);
        this.input = input;
    }

    public DeclarationSinistreException(String message, IDeclarationSinistreInput input) {
        super(message);
        this.input = input;
    }

    public DeclarationSinistreException(String message) {
        super(message);
        this.input = null;
    }

    public IDeclarationSinistreInput getInput() {
        return input;
    }

    void setInput(IDeclarationSinistreInput input) {
        this.input = input;
    }

}
