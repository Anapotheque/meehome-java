package fr.generali.gfb.ws.sinistre.dto;

public enum OrigineDeclaration {

    GPROX(TypeOrigine.DECONNECTE),
    // generali.fr:
    EMM(TypeOrigine.DECONNECTE),
    //
    ESPACE_CLIENT(TypeOrigine.CONNECTE);

    static public enum TypeOrigine {
        /** le client est identifié, il a un id RCE (type espace client) */
        CONNECTE,
        /** le client visite un site public (type EMM) */
        DECONNECTE
    }

    public final TypeOrigine typeOrigine;

    private OrigineDeclaration(TypeOrigine typeOrigine) {
        this.typeOrigine = typeOrigine;
    }

}
