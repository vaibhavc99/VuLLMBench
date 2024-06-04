
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-00/Class00386")
public class Class00386 extends HttpServlet {

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

        String var_eca07335 = param_10573b87.getParameter("Class00386");
        if (var_eca07335 == null) var_eca07335 = "";

        String var_37b51d19 = "safe!";
        java.util.HashMap<String, Object> var_7127e185 = new java.util.HashMap<String, Object>();
        var_7127e185.put("keyA-8057", "a_Value"); // put some stuff in the collection
        var_7127e185.put("keyB-8057", var_eca07335); // put it in a collection
        var_7127e185.put("keyC", "another_Value"); // put some stuff in the collection
        var_37b51d19 = (String) var_7127e185.get("keyB-8057"); // get it back out
        var_37b51d19 = (String) var_7127e185.get("keyA-8057"); // get safe value back out

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        Object[] var_be8f8018 = {var_37b51d19, "b"};
        param_d1fc8eaf.getWriter().printf("Formatted like: %1$s and %2$s.", var_be8f8018);
    }
}
