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
            answers = ctx.search("ou=users,dc=acceso,dc=com,dc=pe", "(uid=" + LDAPUser + ")", ctrls);

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
                            NamingEnumeration a = attribute.getAll();
                            if (a.hasMore()) {
                                Object o = a.nextElement();
                                System.out.println("o = " + o);
                            }
                            DirContext dirContext = attribute.getAttributeDefinition();
//                            dirContext.
                            System.out.println("dirContext = " + dirContext);

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
                            //------------------
                            String password = "W41t3Kn1g4t!";
                            String encodedPasswordWithSSHA = "{SSHA}d9kd9/WYVx7fpOuYVCQgtFsWhPXq6kUx";
                            //eaea4531
//                            System.out.println("encodedPasswordWithSSHA = " + getSshaDigestFor(password, getSalt("eaea4531")));
                            System.out.println("encodedPasswordWithSSHA = " + getSshaDigestFor(password, "eaea4531".getBytes()));
//                            Assert.assertEquals(encodedPasswordWithSSHA, getSshaDigestFor(password, getSalt(encodedPasswordWithSSHA)));
                        }
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

    public Object changepwd(String no_correo, String no_password) {
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
            System.err.println("Successful authenticated bind X1");
            System.out.println("ctx = " + ctx.getNameInNamespace());

            //-----
            try {
//                String quotedPassword = "\"" + no_password + "\"";
                String quotedPassword = "" + no_password + "";
                char unicodePwd[] = quotedPassword.toCharArray();
                byte pwdArray[] = new byte[unicodePwd.length * 2];
                for (int i = 0; i < unicodePwd.length; i++) {
                    pwdArray[i * 2 + 1] = (byte) (unicodePwd[i] >>> 8);
                    pwdArray[i * 2 + 0] = (byte) (unicodePwd[i] & 0xff);
                }
                ModificationItem[] mods = new ModificationItem[1];
                mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                        new BasicAttribute("userpassword", pwdArray));
//                        new BasicAttribute("UnicodePwd", pwdArray));
                //uid=USER_LDAP,ou=users,dc=acceso,dc=com,dc=pe:PASSWORD_LDAP@192.168.44.138:389
                ctx.modifyAttributes("uid=romulo.galindo,ou=users,dc=acceso,dc=com,dc=pe", mods);
            } catch (Exception e) {
                System.out.println("update password error: " + e);
                System.exit(-1);
            }
            //-----

            usuarioLDAP.setIl_conect(true);
            usuarioLDAP.setUser(LDAPUser);
            System.out.println("Por probar!!! = " + ctrls);
//            answers = ctx.search("dc=acceso,dc=com,dc=pe", "(uid=" + LDAPUser + ")", ctrls);
            answers = ctx.search("ou=users,dc=acceso,dc=com,dc=pe", "(uid=" + LDAPUser + ")", ctrls);

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
                            NamingEnumeration a = attribute.getAll();
                            if (a.hasMore()) {
                                Object o = a.nextElement();
                                System.out.println("o = " + o);
                            }
                            DirContext dirContext = attribute.getAttributeDefinition();
//                            dirContext.
                            System.out.println("dirContext = " + dirContext);

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
                            //------------------
                            String password = "W41t3Kn1g4t!";
                            String encodedPasswordWithSSHA = "{SSHA}d9kd9/WYVx7fpOuYVCQgtFsWhPXq6kUx";
                            //eaea4531
//                            System.out.println("encodedPasswordWithSSHA = " + getSshaDigestFor(password, getSalt("eaea4531")));
                            System.out.println("encodedPasswordWithSSHA = " + getSshaDigestFor(password, "eaea4531".getBytes()));
//                            Assert.assertEquals(encodedPasswordWithSSHA, getSshaDigestFor(password, getSalt(encodedPasswordWithSSHA)));
                        }
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

    int SIZE_SHA1_HASH = 20;

    // The salt is the remaining part after the SHA1_hash
    private byte[] getSalt(String encodedPasswordWithSSHA) {
        byte[] data = Base64.getMimeDecoder().decode(encodedPasswordWithSSHA.substring(6));
        return Arrays.copyOfRange(data, SIZE_SHA1_HASH, data.length);
    }

    private String getSshaDigestFor(String password, byte[] salt) throws Exception {
        // create a SHA1 digest of the password + salt
        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
//        MessageDigest crypt = MessageDigest.getInstance("SHA-512");
        crypt.reset();
        crypt.update(password.getBytes(Charset.forName("UTF-8")));
        crypt.update(salt);
        byte[] hash = crypt.digest();

        // concatenate the hash with the salt
        byte[] hashPlusSalt = new byte[hash.length + salt.length];
        System.arraycopy(hash, 0, hashPlusSalt, 0, hash.length);
        System.arraycopy(salt, 0, hashPlusSalt, hash.length, salt.length);

        // prepend the SSHA tag + base64 encode the result
        return "{SSHA}" + Base64.getEncoder().encodeToString(hashPlusSalt);
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
