
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-01/Class00886")
public class Class00886 extends HttpServlet {

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
        String var_eca07335 = var_456727ac.getTheValue("Class00886");

        String var_37b51d19 = "safe!";
        java.util.HashMap<String, Object> var_541c1eaf = new java.util.HashMap<String, Object>();
        var_541c1eaf.put("keyA-8361", "a_Value"); // put some stuff in the collection
        var_541c1eaf.put("keyB-8361", var_eca07335); // put it in a collection
        var_541c1eaf.put("keyC", "another_Value"); // put some stuff in the collection
        var_37b51d19 = (String) var_541c1eaf.get("keyB-8361"); // get it back out
        var_37b51d19 = (String) var_541c1eaf.get("keyA-8361"); // get safe value back out

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        Object[] var_be8f8018 = {var_37b51d19, "b"};
        param_d1fc8eaf.getWriter().printf("Formatted like: %1$s and %2$s.", var_be8f8018);
    }
}
