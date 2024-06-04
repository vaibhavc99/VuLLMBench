
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/BrightGamma197-00/Class01491")
public class Class01491 extends HttpServlet {

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

        org.BrightEpsilon304.SilentEpsilon950.helpers.SeparateClassRequest var_456727ac =
                new org.BrightEpsilon304.SilentEpsilon950.helpers.SeparateClassRequest(param_10573b87);
        String param_eca07335 = var_456727ac.getTheParameter("Class01491");
        if (param_eca07335 == null) param_eca07335 = "";

        String var_37b51d19 = new Class_0cbc6611().doSomething(param_10573b87, param_eca07335);

        org.BrightEpsilon304.SilentEpsilon950.helpers.LDAPManager var_2deb000b = new org.BrightEpsilon304.SilentEpsilon950.helpers.LDAPManager();
        try {
            param_d1fc8eaf.setContentType("text/html;charset=UTF-8");
            javax.naming.directory.DirContext var_ecacffff = var_2deb000b.getDirContext();
            String var_593616de = "ou=users,ou=system";
            javax.naming.directory.SearchControls var_d54185b7 = new javax.naming.directory.SearchControls();
            var_d54185b7.setSearchScope(javax.naming.directory.SearchControls.SUBTREE_SCOPE);
            String var_b2c97ae4 = "(&(objectclass=person)(uid=" + var_37b51d19 + "))";
            // System.out.println("Filter " + var_b2c97ae4);
            boolean var_6cfe6169 = false;
            javax.naming.NamingEnumeration<javax.naming.directory.SearchResult> var_53e61336 =
                    var_ecacffff.search(var_593616de, var_b2c97ae4, var_d54185b7);
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

    private class Class_0cbc6611 {

        public String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
                throws ServletException, IOException {

            // Chain a bunch of propagators in sequence
            String var_e17042b8 = param_eca07335; // assign
            StringBuilder var_4b955819 = new StringBuilder(var_e17042b8); // stick in stringbuilder
            var_4b955819.append(" SafeStuff"); // append some safe content
            var_4b955819.replace(
                    var_4b955819.length() - "Chars".length(),
                    var_4b955819.length(),
                    "Chars"); // replace some of the end content
            java.util.HashMap<String, Object> var_d860ec4e = new java.util.HashMap<String, Object>();
            var_d860ec4e.put("key55812", var_4b955819.toString()); // put in a collection
            String var_a69aa650 = (String) var_d860ec4e.get("key55812"); // get it back out
            String var_8f2d7896 = var_a69aa650.substring(0, var_a69aa650.length() - 1); // extract most of it
            String var_1e4b8883 =
                    new String(
                            org.apache.commons.codec.binary.Base64.decodeBase64(
                                    org.apache.commons.codec.binary.Base64.encodeBase64(
                                            var_8f2d7896.getBytes()))); // B64 encode and decode it
            String var_14917dbc = var_1e4b8883.split(" ")[0]; // split it on a space
            org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                    org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
            String var_c6241811 = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
            String var_37b51d19 = var_8afc1e9b.doSomething(var_c6241811); // reflection

            return var_37b51d19;
        }
    } // end innerclass Class_0cbc6611
} // end DataflowThruInnerClass
