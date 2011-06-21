/**
 * 
 */
package fr.generali.gfb.monitoring.monitors;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;

import fr.generali.monitoring.api.AbstractMonitor;
import fr.generali.monitoring.api.TestElement;
import fr.generali.monitoring.api.Type;

/**
 * Le JNDIMonitor du CCJ ne permet pas de modifier les paramètre d'environnement de l'InitialContext pour le lookup
 * JNDI, ce qui en limite l'usage.
 * 
 * @author dlacourt
 *
 */
public class GdmEaiJmsQueueJndiMonitor extends AbstractMonitor {

    /**
     * Message d'erreur.
     */
    private static final String MSG_JNDI_NAME_NOT_FOUND = "EAI JMS Queue JNDI name not found: ";

    /**
     * Message d'erreur.
     */
    private static final String MSG_NO_JNDI_NAME = "no EAI JMS Queue JNDI name set for JNDIMonitor";

    /**
     * Nom JNDI à tester.
     */
    private String queueName;
    
    private Properties jndiEnvironmentParameters;
    
    /**
     * Constructeur par défaut. Il n'est pas conseillé de l'utiliser, sauf via
     * Spring.
     */
    public GdmEaiJmsQueueJndiMonitor() {
        super("EAI JMS Queue JNDI lookup", Type.JNDI);
    }

    /**
     * Création d'un {@link JNDIMonitor}
     * 
     * @param aJndiName nom JNDI à tester
     */
    public GdmEaiJmsQueueJndiMonitor(String aJndiName) {
        this(aJndiName, null);
    }

    /**
     * Création d'un {@link JNDIMonitor}
     * 
     * @param aJndiName nom JNDI à tester
     * @param desc description du test
     */
    public GdmEaiJmsQueueJndiMonitor(String aJndiName, String desc) {
        super("EAI JMS Queue JNDI lookup,URL=" + aJndiName, Type.JNDI, desc);
        this.queueName = aJndiName;
    }

    
    /**
     * Construction d'un message d'erreur de type "name not found".
     * @return message d'erreur
     */
    private String getNameNotFoundErrorMsg() {
        return MSG_JNDI_NAME_NOT_FOUND + "'" + queueName + "'";
    }
    
    /**
     * 
     */
    @Override
    public void doMonitor(TestElement monitoredElement) {
        // Vérification du paramétrage du monitor :

        if (this.queueName == null || this.queueName.length() == 0) {
            monitoredElement.setTestIsKo(MSG_NO_JNDI_NAME);
        } else {

            // Appel JNDI :

            try {
                final Context ctx = new InitialContext(jndiEnvironmentParameters);
                final Object res = ctx.lookup(queueName);
                if (res == null) {
                    monitoredElement.setTestIsKo(getNameNotFoundErrorMsg());
                } else {
                    monitoredElement.setTestIsOk();
                }
            } catch (NameNotFoundException ex) {
                monitoredElement.setTestIsKo(getNameNotFoundErrorMsg(), ex);
            } catch (Throwable ex) {
                monitoredElement.setTestIsKo(ex);
            }
        }
    }

    /**
     * @return the jndiEnvironmentParameters
     */
    public Properties getJndiEnvironmentParameters() {
        return jndiEnvironmentParameters;
    }

    /**
     * @param jndiEnvironmentParameters the jndiEnvironmentParameters to set
     */
    public void setJndiEnvironmentParameters(Properties jndiEnvironmentParameters) {
        this.jndiEnvironmentParameters = jndiEnvironmentParameters;
    }

    /**
     * @return the queueName
     */
    public String getQueueName() {
        return queueName;
    }

    /**
     * @param queueName the queueName to set
     */
    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    

}
