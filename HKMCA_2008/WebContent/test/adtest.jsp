<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="javax.naming.directory.*"%>
<html>
<head>
</head>
<body>
<%
    Hashtable env = new Hashtable(11);
    env.put(Context.SECURITY_AUTHENTICATION, "none");
    env.put(Context.SECURITY_PRINCIPAL, "CN=kiran,OU=LinkedgeOU,DC=ad.ubq.com");//User
    env.put(Context.SECURITY_CREDENTIALS, "kiran");//Password
    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
    env.put(Context.PROVIDER_URL, "ldap://220.75.183.12:389/DC=LINKEDGEDOMAIN");
    try {
        DirContext ctx = new InitialDirContext(env);
        String[] sAttrIDs = new String[2];
        Attributes attr = ctx.getAttributes("");
        System.out.println("Domain Name:" + attr.get("name").get());
    } catch (NamingException e) {
        System.err.println("Problem getting attribute: " + e);
    }

%>
</body>
</html>
