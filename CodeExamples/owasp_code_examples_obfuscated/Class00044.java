
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/BrightGamma197-00/Class00044")
public class Class00044 extends HttpServlet {

    private static final long var_c6e1e520 = 1L;

    @Override
    public void doGet(HttpServletRequest param_10573b87, HttpServletResponse param_d1fc8eaf)
            throws ServletException, IOException {
        doPost(param_10573b87, param_d1fc8eaf);
    }

    @Override
    public void doPost(HttpServletRequest param_10573b87, HttpServletResponse param_d1fc8eaf)
            throws ServletException, IOException {
        // some code
        param_d1fc8eaf.setContentType("text/html;charset=UTF-8");

        String[] var_f09cc7ee = param_10573b87.getParameterValues("Class00044");
        String var_eca07335;
        if (var_f09cc7ee != null && var_f09cc7ee.length > 0) var_eca07335 = var_f09cc7ee[0];
        else var_eca07335 = "";

        org.BrightEpsilon304.SilentEpsilon950.helpers.LDAPManager var_2deb000b = new org.BrightEpsilon304.SilentEpsilon950.helpers.LDAPManager();
        try {
            param_d1fc8eaf.setContentType("text/html;charset=UTF-8");
            javax.naming.directory.DirContext var_ecacffff = var_2deb000b.getDirContext();
            String var_593616de = "ou=users,ou=system";
            javax.naming.directory.SearchControls var_d54185b7 = new javax.naming.directory.SearchControls();
            var_d54185b7.setSearchScope(javax.naming.directory.SearchControls.SUBTREE_SCOPE);
            String var_b2c97ae4 = "(&(objectclass=person)(uid=" + var_eca07335 + "))";
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
    }
}
