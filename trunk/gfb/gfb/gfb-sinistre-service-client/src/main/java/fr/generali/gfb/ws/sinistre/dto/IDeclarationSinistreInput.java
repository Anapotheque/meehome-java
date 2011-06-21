package fr.generali.gfb.ws.sinistre.dto;

import java.util.Date;

public interface IDeclarationSinistreInput {
    public AssureInput getAssure();

    public void setAssure(AssureInput assure);

    // public OrigineDeclaration getOrigine();

    // public void setOrigine(OrigineDeclaration origine);

    public String getOrigine();

    public void setOrigine(String origineString);

    public Date getDateSinistre();

    public void setDateSinistre(Date dateSinistre);

    public String getMailTrieste();

    public void setMailTrieste(String mailTrieste);
}
