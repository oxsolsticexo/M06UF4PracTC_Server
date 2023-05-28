/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import DAO.DAOEJB;
import Interfaces.DAOInterface;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import main.PartidaEJB;
import main.PreguntaEJB;
import main.SessionManagerEJB;

/**
 * Classe encarregada de fer les connexions amb els EJB remots
 *
 * @author manel
 */
public class Lookups {

    private static final String APP_VERSION = "1.1.0";

    private static final String WILDFLY_INITIAL_CONTEXT_FACTORY = "org.wildfly.naming.client.WildFlyInitialContextFactory";

    private static final String APP_NAME = "M06UF4PracTC_Server-" + APP_VERSION;

    public static IPartida partidaEJBRemoteLookup() throws NamingException {

        String strlookup = "ejb:/" + APP_NAME + "/" + PartidaEJB.class.getSimpleName() + "!" + IPartida.class.getName() + "?stateful";

        Properties jndiProperties = new Properties();

        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, WILDFLY_INITIAL_CONTEXT_FACTORY);

        Context context = new InitialContext(jndiProperties);

        return (IPartida) context.lookup(strlookup);
    }

    /**
     * recupera la clase DAO
     * @return
     * @throws NamingException 
     */
    public static DAOInterface DAOEJBLocalLookup() throws NamingException {
        String strlookup = "java:jboss/exported/" + APP_NAME + "/" + DAOEJB.class.getSimpleName() + "!" + DAOInterface.class.getName();

        Properties jndiProperties = new Properties();

        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, WILDFLY_INITIAL_CONTEXT_FACTORY);

        Context context = new InitialContext(jndiProperties);

        return (DAOInterface) context.lookup(strlookup);
    }

    public static IPregunta preguntaEJBRemoteLookup() throws NamingException {

        String strlookup = "ejb:/" + APP_NAME + "/" + PreguntaEJB.class.getSimpleName() + "!" + IPregunta.class.getName() + "?stateful";

        Properties jndiProperties = new Properties();

        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, WILDFLY_INITIAL_CONTEXT_FACTORY);

        Context context = new InitialContext(jndiProperties);

        return (IPregunta) context.lookup(strlookup);
    }

    /**
     * recupera el session manager
     *
     * @return
     * @throws NamingException
     */
    public static ISessionManager sessionManagerEJBRemoteLookup() throws NamingException {

        String strlookup = "ejb:/" + APP_NAME + "/" + SessionManagerEJB.class.getSimpleName() + "!" + ISessionManager.class.getName();

        Properties jndiProperties = new Properties();

        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, WILDFLY_INITIAL_CONTEXT_FACTORY);

        Context context = new InitialContext(jndiProperties);

        return (ISessionManager) context.lookup(strlookup);
    }
}
