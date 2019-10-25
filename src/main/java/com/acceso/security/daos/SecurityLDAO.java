package com.acceso.security.daos;

import com.acceso.wfcore.daos.DAO;
import com.acceso.wfweb.units.UsuarioLDAP;
//import com.ibm.websphere.crypto.PasswordUtil;
import com.ibm.websphere.crypto.PasswordUtil;
import com.sun.mail.util.BASE64EncoderStream;
//import org.apache.directory.ldap.client.api.LdapConnection;
//import org.apache.directory.ldap.client.api.LdapConnectionConfig;
//import org.apache.directory.ldap.client.api.LdapNetworkConnection;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Properties;

public class SecurityLDAO extends DAO {
    public static String TAG = "SECURE-LDAP";
    protected String LDAPServer;
    protected String LDAPDn;
    protected String LDAPUser;
    protected String LDAPPassword;

    public SecurityLDAO(String LDAPServer, String LDAPDn) {
        this.LDAPServer = LDAPServer;
        this.LDAPDn = LDAPDn;
    }

    public SecurityLDAO(String url) {
        this.LDAPServer = url.split("@")[1];
        this.LDAPUser = url.split("@")[0].split(":")[0];
        this.LDAPPassword = url.split("@")[0].split(":")[1];
    }

    //
    //String serverURL = "ldap://192.168.44.82:389";
//    public UsuarioLDAP connect(String LDAPUser, String LDAPPassword) {
    public UsuarioLDAP connect(String zimbra_username, String zimbre_password) {

        System.out.println("LDAP::ADMIN::ACCESO");

        UsuarioLDAP usuarioLDAP = new UsuarioLDAP();
        Properties parms = getLDAPProperties(false);
        parms.put(Context.SECURITY_PRINCIPAL, LDAPUser);
        parms.put(Context.SECURITY_CREDENTIALS, LDAPPassword);

        DirContext ctx = null;
        NamingEnumeration<SearchResult> answers = null;

        SearchControls ctrls = new SearchControls();
        ctrls.setReturningAttributes(new String[]{"givenName", "sn", "memberOf", "userPassword"});
        ctrls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        try {
            //LOGIN
            ctx = new InitialDirContext(parms);
            System.err.println("Successful authenticated bind");
            System.out.println("ctx = " + ctx.getNameInNamespace());
            usuarioLDAP.setIl_conect(true);
            usuarioLDAP.setUser(zimbra_username);
            System.out.println("Por probar!!! = " + ctrls);
//            answers = ctx.search("dc=acceso,dc=com,dc=pe", "(uid=" + LDAPUser + ")", ctrls);
            answers = ctx.search("ou=users,dc=acceso,dc=com,dc=pe", "(uid=" + zimbra_username + ")", ctrls);

            try {
                while (answers.hasMore()) {
                    Attributes attributes = answers.next().getAttributes();
                    NamingEnumeration namingEnumeration = attributes.getAll();
                    while (namingEnumeration.hasMore()) {
                        Object o2 = namingEnumeration.next();
                        System.out.println("o2 = " + o2);
                        String attrId;
                        Attribute attribute = (Attribute) o2;
                        System.out.println("o2 = " + attribute.getID());
                        if (attribute.getID().contentEquals("userPassword")) {
                            String valPassword = attribute.get().toString();
                            String pwd = new String((byte[]) attribute.get());
                            System.out.println("valPassword = " + valPassword);
                            System.out.println("valPassword2 = " + pwd);
                            //

//                            public static String generateSSHA(byte[] password)
//        throws NoSuchAlgorithmException {
                            SecureRandom secureRandom = new SecureRandom();
                            byte[] salt = new byte[4];
                            secureRandom.nextBytes(salt);

                            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
                            crypt.reset();
                            crypt.update("W41t3Kn1g4t".getBytes());
                            crypt.update(salt);
                            byte[] hash = crypt.digest();

                            byte[] hashPlusSalt = new byte[hash.length + salt.length];
                            System.arraycopy(hash, 0, hashPlusSalt, 0, hash.length);
                            System.arraycopy(salt, 0, hashPlusSalt, hash.length, salt.length);
//                            PasswordUtil.encode("W41t3Kn1g4t","SHA");
                            String n_pas = new StringBuilder().append("{SSHA}")
                                    .append(Base64.getEncoder().encodeToString(hashPlusSalt))
                                    .toString();
                            System.out.println("============>" + n_pas);
                            //----
                            MessageDigest md = MessageDigest.getInstance("SHA");//MessageDigest.
                            md.update("W41t3Kn1g4t".getBytes());
//                            sun.misc.BASE64Encoder
                            byte raw[] = md.digest();
                            String rps = new String(org.apache.commons.codec.binary.Base64.encodeBase64(raw));
                            System.out.println("rps =>>>>>>>>>>> " + rps);
//                            BASE64EncoderStream base64 = new BASE64Encoder();
//                            String result = "{SHA}" + base64.encode(raw);
//                            System.out.println("userpassword in LDAP:" + result);
//                            }
//                            new LdapShaPasswordEncoder
//                            String uy = PasswordUtil.encode_password("W41t3Kn1g4t", "SHA");
//                            System.out.println("uy = " + uy);
                        }
                        //----
//                        if (attribute.get() instanceof java.lang.String) {
//                            //modo string
//                            System.out.println("attribute = " + attribute + ",-->" + attribute.get().getClass() + ",--->" + attribute.getID());
//                        } else {
//                            //es password
//                            String pwd = new String((byte[]) attribute.get());
//                            System.out.println("attribute = " + attribute + ",-->" + pwd);
//                        }
                    }
                }
            } catch (Exception ep) {
                System.out.println("ep = " + ep);
                ep.printStackTrace();
            }

            return usuarioLDAP;
        } catch (Exception ne) {
            usuarioLDAP.setIl_conect(false);
            usuarioLDAP.setUser(LDAPUser);
            System.err.println("Unsuccessful authenticated bind\n");
            ne.printStackTrace(System.err);
            return usuarioLDAP;
        }


    }

    protected Properties getLDAPProperties(boolean ssl) {
        Properties parms = new Properties();
        parms.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        parms.put(Context.PROVIDER_URL, "ldap://" + LDAPServer);
        if (ssl)
            parms.put(Context.SECURITY_PROTOCOL, "ssl");
        parms.put(Context.SECURITY_AUTHENTICATION, "simple");

        return parms;
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
