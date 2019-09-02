package com.acceso.security.daos;

import com.acceso.wfcore.daos.DAO;
//import org.apache.directory.ldap.client.api.LdapConnection;
//import org.apache.directory.ldap.client.api.LdapConnectionConfig;
//import org.apache.directory.ldap.client.api.LdapNetworkConnection;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;
import java.security.MessageDigest;
import java.util.Properties;

public class SecurityLDAO extends DAO {
    public static String TAG = "SECURE-LDAP";

    public SecurityLDAO() {

    }

    public void connect() {

        System.out.println("LDAP");
        String serverURL = "ldap://192.168.44.82:389";
//        String bindDN = "cn=admin,dc=acceso,dc=com,dc=pe";
        String bindDN = "cn=rgalindo,cn=groups,ou=people,cn=admin,dc=acceso,dc=com,dc=pe";
//        String bindPassword = "Acceso.123";
        String bindPassword = "acceso123";

        Properties parms = new Properties();
        parms.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        parms.put(Context.PROVIDER_URL, serverURL);
//        parms.put(Context.SECURITY_PROTOCOL, "ssl");
        parms.put(Context.SECURITY_AUTHENTICATION, "simple");
        parms.put(Context.SECURITY_PRINCIPAL, bindDN);
        parms.put(Context.SECURITY_CREDENTIALS, bindPassword);

        DirContext ctx = null;
        NamingEnumeration<SearchResult> answers = null;

        SearchControls ctrls = new SearchControls();
        ctrls.setReturningAttributes(new String[]{"givenName", "sn", "memberOf", "userPassword"});
        ctrls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        try {
            ctx = new InitialDirContext(parms);
            System.err.println("Successful authenticated bind");
            System.out.println("ctx = " + ctx.getNameInNamespace());

            //answers = ctx.search("o=xx.com", "(uid=areyes)", ctrls);
//            answers = ctx.search("dc=acceso,dc=com,dc=pe", "(uid=areyes)",ctrls);
            answers = ctx.search("dc=acceso,dc=com,dc=pe", "(uid=rgalindo)", ctrls);
//            Attributes attributes = answers.getAttributes();
//            NamingEnumeration namingEnumeration = attributes.getAll();
//            attributes.getAll();

            try {
                while (answers.hasMore()) {
//                    Object o = answers.next();
                    Attributes attributes = answers.next().getAttributes();
                    NamingEnumeration namingEnumeration = attributes.getAll();
                    while (namingEnumeration.hasMore()) {
                        Object o2 = namingEnumeration.next();
                        System.out.println("o2 = " + o2);
                        String attrId;
//                        Attribute attribute = attributes.get(o2 + "");
                        Attribute attribute = (Attribute) o2;
                        if (attribute.get() instanceof java.lang.String) {
                            //modo string
                            System.out.println("attribute = " + attribute + ",-->" + attribute.get().getClass() + ",--->" + attribute.getID());
                        } else {
                            //es password
                            String pwd = new String((byte[]) attribute.get());
//                            System.out.println("attribute = " + attribute + ",-->" + encryptLdapPassword("MD5", pwd));
                            System.out.println("attribute = " + attribute + ",-->" + pwd);
                        }


//                        System.out.println("o2 = " + attribute.get() + ",=====>" + attribute.getID() + "----" + attribute.size());
                    }
//                    System.out.println("??o = " + o);
                }
            } catch (Exception ep) {
                System.out.println("ep = " + ep);
                ep.printStackTrace();
            }

        } catch (Exception ne) {
            System.err.println("Unsuccessful authenticated bind\n");
            ne.printStackTrace(System.err);
        }
    }

    private String encryptLdapPassword(String algorithm, String _password) {
        String sEncrypted = _password;
        if ((_password != null) && (_password.length() > 0)) {
            boolean bMD5 = algorithm.equalsIgnoreCase("MD5");
            boolean bSHA = algorithm.equalsIgnoreCase("SHA")
                    || algorithm.equalsIgnoreCase("SHA1")
                    || algorithm.equalsIgnoreCase("SHA-1");
            if (bSHA || bMD5) {
                String sAlgorithm = "MD5";
                if (bSHA) {
                    sAlgorithm = "SHA";
                }
                try {
                    MessageDigest md = MessageDigest.getInstance(sAlgorithm);
                    md.update(_password.getBytes("UTF-8"));

//                    sEncrypted = "{" + sAlgorithm + "}" + (new BASE64Encoder()).encode(md.digest());
                    sEncrypted = "{" + sAlgorithm + "}" + java.util.Base64.getEncoder().encode(md.digest());
                } catch (Exception e) {
                    sEncrypted = null;
//                    logger.error(e, e);
                }
            }
        }
        return sEncrypted;
    }
}


//}
