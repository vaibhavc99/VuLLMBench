
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/BraveDelta367-04/Class02095")
public class Class02095 extends HttpServlet {

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

        String param_eca07335 = "";
        java.util.Enumeration<String> var_4340fd73 = param_10573b87.getHeaders("Class02095");

        if (var_4340fd73 != null && var_4340fd73.hasMoreElements()) {
            param_eca07335 = var_4340fd73.nextElement(); // just grab first element
        }

        // URL Decode the header value since req.getHeaders() doesn't. Unlike req.getParameters().
        param_eca07335 = java.net.URLDecoder.decode(param_eca07335, "UTF-8");

        String var_37b51d19 = doSomething(param_10573b87, param_eca07335);

        String var_ac5c74b6 = "SELECT * from USERS where USERNAME='foo' and PASSWORD='" + var_37b51d19 + "'";

        try {
            java.var_ac5c74b6.Statement var_4245bf56 =
                    org.BrightEpsilon304.SilentEpsilon950.helpers.DatabaseHelper.getSqlStatement();
            var_4245bf56.execute(var_ac5c74b6, java.var_ac5c74b6.Statement.RETURN_GENERATED_KEYS);
            org.BrightEpsilon304.SilentEpsilon950.helpers.DatabaseHelper.printResults(var_4245bf56, var_ac5c74b6, param_d1fc8eaf);
        } catch (java.var_ac5c74b6.SQLException e) {
            if (org.BrightEpsilon304.SilentEpsilon950.helpers.DatabaseHelper.hideSQLErrors) {
                param_d1fc8eaf.getWriter().println("Error processing param_10573b87.");
                return;
            } else throw new ServletException(e);
        }
    } // end doPost

    private static String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
            throws ServletException, IOException {

        String var_37b51d19 = "safe!";
        java.util.HashMap<String, Object> var_1de5fa6f = new java.util.HashMap<String, Object>();
        var_1de5fa6f.put("keyA-43923", "a_Value"); // put some stuff in the collection
        var_1de5fa6f.put("keyB-43923", param_eca07335); // put it in a collection
        var_1de5fa6f.put("keyC", "another_Value"); // put some stuff in the collection
        var_37b51d19 = (String) var_1de5fa6f.get("keyB-43923"); // get it back out
        var_37b51d19 = (String) var_1de5fa6f.get("keyA-43923"); // get safe value back out

        return var_37b51d19;
    }
}
