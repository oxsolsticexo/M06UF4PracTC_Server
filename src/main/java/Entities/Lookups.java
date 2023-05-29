/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Logica.Interfaces.ISessionManager;
import Logica.Interfaces.IPregunta;
import Logica.Interfaces.IPartida;
import DAO.DAOEJB;
import DAO.DAOHallOfFame;
import DAO.DAOPregunta;
import Logica.EJB.HallOfFameEJB;
import Logica.Interfaces.DAOInterface;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import Logica.EJB.PartidaEJB;
import Logica.EJB.PreguntaEJB;
import Logica.EJB.SessionManagerEJB;
import Logica.Interfaces.IDAOPregunta;
import Logica.Interfaces.IFameInterface;
import Logica.Interfaces.IHallOfFame;

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
     *
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

    public static IDAOPregunta DAOPreguntaLocalLookup() throws NamingException {
        String strlookup = "java:jboss/exported/" + APP_NAME + "/" + DAOPregunta.class.getSimpleName() + "!" + IDAOPregunta.class.getName();

        Properties jndiProperties = new Properties();

        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, WILDFLY_INITIAL_CONTEXT_FACTORY);

        Context context = new InitialContext(jndiProperties);

        return (IDAOPregunta) context.lookup(strlookup);
    }

    public static IHallOfFame DAOHallOfFameLookup() throws NamingException {
        String strlookup = "ejb:/" + APP_NAME + "/" + HallOfFameEJB.class.getSimpleName() + "!" + IHallOfFame.class.getName() + "?stateful";

        Properties jndiProperties = new Properties();

        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, WILDFLY_INITIAL_CONTEXT_FACTORY);

        Context context = new InitialContext(jndiProperties);

        return (IHallOfFame) context.lookup(strlookup);
    }

    public static IFameInterface IFameLookup() throws NamingException {
        String strlookup = "ejb:/" + APP_NAME + "/" + DAOHallOfFame.class.getSimpleName() + "!" + IFameInterface.class.getName() + "?stateful";

        Properties jndiProperties = new Properties();

        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, WILDFLY_INITIAL_CONTEXT_FACTORY);

        Context context = new InitialContext(jndiProperties);

        return (IFameInterface) context.lookup(strlookup);
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
