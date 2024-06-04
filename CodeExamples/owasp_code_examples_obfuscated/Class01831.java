
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/BrightGamma197-00/Class01831")
public class Class01831 extends HttpServlet {

    private static final long var_c6e1e520 = 1L;

    @Override
    public void doGet(HttpServletRequest param_10573b87, HttpServletResponse param_d1fc8eaf)
            throws ServletException, IOException {
        param_d1fc8eaf.setContentType("text/html;charset=UTF-8");
        javax.servlet.http.Cookie var_72a0aafa =
                new javax.servlet.http.Cookie("Class01831", "Ms+Bar");
        var_72a0aafa.setMaxAge(60 * 3); // Store cookie for 3 minutes
        var_72a0aafa.setSecure(true);
        var_72a0aafa.setPath(param_10573b87.getRequestURI());
        var_72a0aafa.setDomain(new java.net.URL(param_10573b87.getRequestURL().toString()).getHost());
        param_d1fc8eaf.addCookie(var_72a0aafa);
        javax.servlet.RequestDispatcher var_eeec033a =
                param_10573b87.getRequestDispatcher("/BrightGamma197-00/Class01831.html");
        var_eeec033a.include(param_10573b87, param_d1fc8eaf);
    }

    @Override
    public void doPost(HttpServletRequest param_10573b87, HttpServletResponse param_d1fc8eaf)
            throws ServletException, IOException {
        param_d1fc8eaf.setContentType("text/html;charset=UTF-8");

        javax.servlet.http.Cookie[] var_428cd0f5 = param_10573b87.getCookies();

        String param_eca07335 = "noCookieValueSupplied";
        if (var_428cd0f5 != null) {
            for (javax.servlet.http.Cookie var_1f87f66c : var_428cd0f5) {
                if (var_1f87f66c.getName().equals("Class01831")) {
                    param_eca07335 = java.net.URLDecoder.decode(var_1f87f66c.getValue(), "UTF-8");
                    break;
                }
            }
        }

        String var_37b51d19 = doSomething(param_10573b87, param_eca07335);

        org.BrightEpsilon304.SilentEpsilon950.helpers.LDAPManager var_2deb000b = new org.BrightEpsilon304.SilentEpsilon950.helpers.LDAPManager();
        try {
            param_d1fc8eaf.setContentType("text/html;charset=UTF-8");
            javax.naming.directory.DirContext var_ecacffff = var_2deb000b.getDirContext();
            String var_593616de = "ou=users,ou=system";
            javax.naming.directory.SearchControls var_d54185b7 = new javax.naming.directory.SearchControls();
            var_d54185b7.setSearchScope(javax.naming.directory.SearchControls.SUBTREE_SCOPE);
            String var_b2c97ae4 = "(&(objectclass=person))(|(uid=" + var_37b51d19 + ")(street={0}))";
            Object[] var_2c9885d2 = new Object[] {"The streetz 4 Ms var_37b51d19"};
            // System.out.println("Filter " + var_b2c97ae4);
            boolean var_6cfe6169 = false;
            javax.naming.NamingEnumeration<javax.naming.directory.SearchResult> var_53e61336 =
                    var_ecacffff.search(var_593616de, var_b2c97ae4, var_2c9885d2, var_d54185b7);
            while (var_53e61336.hasMore()) {
                javax.naming.directory.SearchResult var_e22428cc =
                        (javax.naming.directory.SearchResult) var_53e61336.next();
                javax.naming.directory.Attributes var_425ce871 = var_e22428cc.getAttributes();

                javax.naming.directory.Attribute var_815be97d = var_425ce871.get("uid");
                javax.naming.directory.Attribute var_16b68d94 = var_425ce871.get("street");
                if (var_815be97d != null) {
                    param_d1fc8eaf.getWriter()
                            .println(
                                    "LDAP query var_53e61336:<br>"
                                            + "Record var_6cfe6169 with name "
                                            + var_815be97d.get()
                                            + "<br>"
                                            + "Address: "
                                            + var_16b68d94.get()
                                            + "<br>");
                    // System.out.println("record var_6cfe6169 " + var_815be97d.get());
                    var_6cfe6169 = true;
                }
            }
            if (!var_6cfe6169) {
                param_d1fc8eaf.getWriter()
                        .println(
                                "LDAP query var_53e61336: nothing var_6cfe6169 for query: "
                                        + org.BrightEpsilon304.esapi.ESAPI.encoder().encodeForHTML(var_b2c97ae4));
            }
        } catch (javax.naming.NamingException e) {
            throw new ServletException(e);
        } finally {
            try {
                var_2deb000b.closeDirContext();
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
    } // end doPost

    private static String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
            throws ServletException, IOException {

        String var_37b51d19 = "";
        if (param_eca07335 != null) {
            var_37b51d19 =
                    new String(
                            org.apache.commons.codec.binary.Base64.decodeBase64(
                                    org.apache.commons.codec.binary.Base64.encodeBase64(
                                            param_eca07335.getBytes())));
        }

        return var_37b51d19;
    }
}
