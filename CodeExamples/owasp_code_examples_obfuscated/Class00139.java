
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/BrightGamma197-00/Class00139")
public class Class00139 extends HttpServlet {

    private static final long var_c6e1e520 = 1L;

    @Override
    public void doGet(HttpServletRequest param_10573b87, HttpServletResponse param_d1fc8eaf)
            throws ServletException, IOException {
        doPost(param_10573b87, param_d1fc8eaf);
    }

    @Override
    public void doPost(HttpServletRequest param_10573b87, HttpServletResponse param_d1fc8eaf)
            throws ServletException, IOException {
        param_d1fc8eaf.setContentType("text/html;charset=UTF-8");

        String var_eca07335 = "";
        if (param_10573b87.getHeader("Class00139") != null) {
            var_eca07335 = param_10573b87.getHeader("Class00139");
        }

        // URL Decode the header value since req.getHeader() doesn't. Unlike req.getParameter().
        var_eca07335 = java.net.URLDecoder.decode(var_eca07335, "UTF-8");

        // Chain a bunch of propagators in sequence
        String var_14e5347a = var_eca07335; // assign
        StringBuilder var_9afc9c96 = new StringBuilder(var_14e5347a); // stick in stringbuilder
        var_9afc9c96.append(" SafeStuff"); // append some safe content
        var_9afc9c96.replace(
                var_9afc9c96.length() - "Chars".length(),
                var_9afc9c96.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_a74e4622 = new java.util.HashMap<String, Object>();
        var_a74e4622.put("key48394", var_9afc9c96.toString()); // put in a collection
        String var_b0599fd9 = (String) var_a74e4622.get("key48394"); // get it back out
        String var_1ebddf61 = var_b0599fd9.substring(0, var_b0599fd9.length() - 1); // extract most of it
        String var_8f5e00dc =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_1ebddf61.getBytes()))); // B64 encode and decode it
        String var_fb627a4d = var_8f5e00dc.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_6384da82 = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
        String var_37b51d19 = var_8afc1e9b.doSomething(var_6384da82); // reflection

        org.BrightEpsilon304.SilentEpsilon950.helpers.LDAPManager var_2deb000b = new org.BrightEpsilon304.SilentEpsilon950.helpers.LDAPManager();
        try {
            param_d1fc8eaf.setContentType("text/html;charset=UTF-8");
            String var_593616de = "ou=users,ou=system";
            javax.naming.directory.SearchControls var_d54185b7 = new javax.naming.directory.SearchControls();
            var_d54185b7.setSearchScope(javax.naming.directory.SearchControls.SUBTREE_SCOPE);
            String var_b2c97ae4 = "(&(objectclass=person)(uid=" + var_37b51d19 + "))";

            javax.naming.directory.DirContext var_ecacffff = var_2deb000b.getDirContext();
            javax.naming.directory.InitialDirContext var_cca5e614 =
                    (javax.naming.directory.InitialDirContext) var_ecacffff;
            boolean var_6cfe6169 = false;
            javax.naming.NamingEnumeration<javax.naming.directory.SearchResult> var_53e61336 =
                    var_cca5e614.search(var_593616de, var_b2c97ae4, var_d54185b7);

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
    }
}
