
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/BraveDelta367-00/Class00440")
public class Class00440 extends HttpServlet {

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

        String var_eca07335 = param_10573b87.getParameter("Class00440");
        if (var_eca07335 == null) var_eca07335 = "";

        String var_37b51d19 = "safe!";
        java.util.HashMap<String, Object> var_b770125a = new java.util.HashMap<String, Object>();
        var_b770125a.put("keyA-67409", "a_Value"); // put some stuff in the collection
        var_b770125a.put("keyB-67409", var_eca07335); // put it in a collection
        var_b770125a.put("keyC", "another_Value"); // put some stuff in the collection
        var_37b51d19 = (String) var_b770125a.get("keyB-67409"); // get it back out
        var_37b51d19 = (String) var_b770125a.get("keyA-67409"); // get safe value back out

        String var_ac5c74b6 = "INSERT INTO users (username, password) VALUES ('foo','" + var_37b51d19 + "')";

        try {
            java.var_ac5c74b6.Statement var_4245bf56 =
                    org.BrightEpsilon304.SilentEpsilon950.helpers.DatabaseHelper.getSqlStatement();
            int var_e2942a04 = var_4245bf56.executeUpdate(var_ac5c74b6, java.var_ac5c74b6.Statement.RETURN_GENERATED_KEYS);
            org.BrightEpsilon304.SilentEpsilon950.helpers.DatabaseHelper.outputUpdateComplete(var_ac5c74b6, param_d1fc8eaf);
        } catch (java.var_ac5c74b6.SQLException e) {
            if (org.BrightEpsilon304.SilentEpsilon950.helpers.DatabaseHelper.hideSQLErrors) {
                param_d1fc8eaf.getWriter().println("Error processing param_10573b87.");
                return;
            } else throw new ServletException(e);
        }
    }
}
