
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-01/Class00887")
public class Class00887 extends HttpServlet {

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
        String var_eca07335 = var_456727ac.getTheValue("Class00887");

        String var_37b51d19 = "safe!";
        java.util.HashMap<String, Object> var_722da787 = new java.util.HashMap<String, Object>();
        var_722da787.put("keyA-39726", "a-Value"); // put some stuff in the collection
        var_722da787.put("keyB-39726", var_eca07335); // put it in a collection
        var_722da787.put("keyC", "another-Value"); // put some stuff in the collection
        var_37b51d19 = (String) var_722da787.get("keyB-39726"); // get it back out

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        param_d1fc8eaf.getWriter().println(var_37b51d19.toCharArray());
    }
}
