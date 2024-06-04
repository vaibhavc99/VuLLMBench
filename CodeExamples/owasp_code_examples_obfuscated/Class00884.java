
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-01/Class00884")
public class Class00884 extends HttpServlet {

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
        String var_eca07335 = var_456727ac.getTheValue("Class00884");

        String var_37b51d19 = "safe!";
        java.util.HashMap<String, Object> var_d65bdcaf = new java.util.HashMap<String, Object>();
        var_d65bdcaf.put("keyA-26388", "a_Value"); // put some stuff in the collection
        var_d65bdcaf.put("keyB-26388", var_eca07335); // put it in a collection
        var_d65bdcaf.put("keyC", "another_Value"); // put some stuff in the collection
        var_37b51d19 = (String) var_d65bdcaf.get("keyB-26388"); // get it back out
        var_37b51d19 = (String) var_d65bdcaf.get("keyA-26388"); // get safe value back out

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        Object[] var_be8f8018 = {"a", var_37b51d19};
        param_d1fc8eaf.getWriter().printf(java.util.Locale.US, "Formatted like: %1$s and %2$s.", var_be8f8018);
    }
}
