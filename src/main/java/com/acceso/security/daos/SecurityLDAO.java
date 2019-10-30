package com.acceso.security.daos;

import com.acceso.wfcore.daos.DAO;
import com.acceso.wfweb.units.UsuarioLDAP;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
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

    public UsuarioLDAP connect() {

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
            usuarioLDAP.setUser(LDAPUser);
            System.out.println("Por probar!!! = " + ctrls);
//            answers = ctx.search("dc=acceso,dc=com,dc=pe", "(uid=" + LDAPUser + ")", ctrls);
//            answers = ctx.search("ou=users,dc=acceso,dc=com,dc=pe", "(uid=" + LDAPUser + ")", ctrls);


            return usuarioLDAP;
        } catch (Exception ne) {
            usuarioLDAP.setIl_conect(false);
            usuarioLDAP.setUser(LDAPUser);
            System.err.println("Unsuccessful authenticated bind\n");
            ne.printStackTrace(System.err);
            return usuarioLDAP;
        }

    }

    public int changepwd(String no_correo, String no_password) {
        System.out.println("LDAP::ADMIN::ACCESO[" + no_correo + "," + no_password + "]");

        Properties parms = getLDAPProperties(false);
        parms.put(Context.SECURITY_PRINCIPAL, LDAPUser);
        parms.put(Context.SECURITY_CREDENTIALS, LDAPPassword);

        DirContext ctx = null;

        try {
            //LOGIN
            ctx = new InitialDirContext(parms);
            System.err.println("Successful authenticated bind X1");
            System.out.println("ctx = " + ctx.getNameInNamespace());

            String pwe = generateSSHA(new String(no_password.getBytes("ISO-8859-1"), "UTF-8").getBytes());

            ModificationItem[] mods = new ModificationItem[1];
            mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("userpassword", pwe));
            ctx.modifyAttributes("uid=" + no_correo + ",ou=users,dc=acceso,dc=com,dc=pe", mods);

            return 1;
        } catch (Exception ep) {
            System.err.println("Unsuccessful authenticated bind\n");
            ep.printStackTrace(System.err);
            return 0;
        }
    }

    public String generateSSHA(byte[] password) throws Exception {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[4];
        secureRandom.nextBytes(salt);

        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(password);
        crypt.update(salt);
        byte[] hash = crypt.digest();

        byte[] hashPlusSalt = new byte[hash.length + salt.length];
        System.arraycopy(hash, 0, hashPlusSalt, 0, hash.length);
        System.arraycopy(salt, 0, hashPlusSalt, hash.length, salt.length);

        return new StringBuilder().append("{SSHA}")
                .append(Base64.getEncoder().encodeToString(hashPlusSalt))
                .toString();
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

}


//}
