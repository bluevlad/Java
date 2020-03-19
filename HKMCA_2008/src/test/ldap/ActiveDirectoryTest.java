package test.ldap;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class ActiveDirectoryTest {
	public void main() {
		Hashtable env = new Hashtable(11);
		env.put(Context.SECURITY_AUTHENTICATION, "none");
		env.put(Context.SECURITY_PRINCIPAL, "CN=kiran,OU=LinkedgeOU,DC=LINKEDGEDOMAIN");//User
		env.put(Context.SECURITY_CREDENTIALS, "kiran");//Password
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://testing2:389/DC=LINKEDGEDOMAIN");
		try {
			DirContext ctx = new InitialDirContext(env);
			String[] sAttrIDs = new String[2];
			Attributes attr = ctx.getAttributes("");
			System.out.println("Domain Name:" + attr.get("name").get());
		} catch (NamingException e) {
			System.err.println("Problem getting attribute: " + e);
		}
	}
}
