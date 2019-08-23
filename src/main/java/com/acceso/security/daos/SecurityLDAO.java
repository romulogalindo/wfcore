package com.acceso.security.daos;

import com.acceso.wfcore.daos.DAO;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import java.util.Properties;

public class SecurityLDAO extends DAO {
    public static String TAG = "SECURE-LDAP";

    public SecurityLDAO() {

    }

    public void connect() {
        System.out.println("CONNECT =>>>>>>>>>>>< ");
        String username = "areyes";
//        String username = "cn=admin,dc=acceso,dc=com,dc=pe";
        String password = "Acceso.123";
//        String password = "";
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        props.put(Context.PROVIDER_URL, "ldap://192.168.44.82:389");
//        props.put(Context.SECURITY_PRINCIPAL, "uid=adminuser,ou=special users,o=xx.com");//adminuser - User with special priviledge, dn user
        props.put(Context.SECURITY_PRINCIPAL, "cn=admin,dc=acceso,dc=com,dc=pe");//adminuser - User with special priviledge, dn user
        props.put(Context.SECURITY_CREDENTIALS, "Acceso.123");//dn user password


        SearchControls ctrls = new SearchControls();
        ctrls.setReturningAttributes(new String[]{"givenName", "sn", "memberOf"});
        ctrls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        InitialDirContext context = null;
        NamingEnumeration<SearchResult> answers = null;
        try {
            context = new InitialDirContext(props);
            answers = context.search("o=xx.com", "(uid=" + username + ")", ctrls);
            System.out.println("context = " + context);
            System.out.println("answers = " + answers);
        } catch (Exception ep) {
            System.out.println("==================================== = ");
            ep.printStackTrace();
        }

        javax.naming.directory.SearchResult result = answers.nextElement();
        System.out.println("result = " + result);
        System.out.println("result = " + result.getAttributes());

        Attributes attributes = result.getAttributes();
        NamingEnumeration namingEnumeration = attributes.getAll();
        attributes.getAll();

        try {
            while (namingEnumeration.hasMore()) {
                Object o = namingEnumeration.next();
                System.out.println("??o = " + o);
            }
        } catch (Exception ep) {
            System.out.println("ep = " + ep);
            ep.printStackTrace();
        }

        String user = result.getNameInNamespace();
        System.out.println("user = " + user);
        try {
            props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            props.put(Context.PROVIDER_URL, "ldap://192.168.44.82:389");
            props.put(Context.SECURITY_PRINCIPAL, user);
            props.put(Context.SECURITY_CREDENTIALS, password);

            context = new InitialDirContext(props);
            System.out.println("context = " + context);
        } catch (Exception ep) {
            System.out.println("==================================== = ");
            ep.printStackTrace();
        }
    }


}
