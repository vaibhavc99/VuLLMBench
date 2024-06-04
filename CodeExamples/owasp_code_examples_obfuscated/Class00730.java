
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-01/Class00730")
public class Class00730 extends HttpServlet {

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

        String[] var_f09cc7ee = param_10573b87.getParameterValues("Class00730");
        String var_eca07335;
        if (var_f09cc7ee != null && var_f09cc7ee.length > 0) var_eca07335 = var_f09cc7ee[0];
        else var_eca07335 = "";

        String var_37b51d19 = "safe!";
        java.util.HashMap<String, Object> var_1b49dfe3 = new java.util.HashMap<String, Object>();
        var_1b49dfe3.put("keyA-29173", "a_Value"); // put some stuff in the collection
        var_1b49dfe3.put("keyB-29173", var_eca07335); // put it in a collection
        var_1b49dfe3.put("keyC", "another_Value"); // put some stuff in the collection
        var_37b51d19 = (String) var_1b49dfe3.get("keyB-29173"); // get it back out
        var_37b51d19 = (String) var_1b49dfe3.get("keyA-29173"); // get safe value back out

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        param_d1fc8eaf.getWriter().write(var_37b51d19.toCharArray());
    }
}
