
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-00/Class00467")
public class Class00467 extends HttpServlet {

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

        java.util.Map<String, String[]> var_1d78dc8e = param_10573b87.getParameterMap();
        String var_eca07335 = "";
        if (!var_1d78dc8e.isEmpty()) {
            String[] var_f09cc7ee = var_1d78dc8e.get("Class00467");
            if (var_f09cc7ee != null) var_eca07335 = var_f09cc7ee[0];
        }

        String var_37b51d19 = "safe!";
        java.util.HashMap<String, Object> var_7e105c57 = new java.util.HashMap<String, Object>();
        var_7e105c57.put("keyA-88136", "a-Value"); // put some stuff in the collection
        var_7e105c57.put("keyB-88136", var_eca07335); // put it in a collection
        var_7e105c57.put("keyC", "another-Value"); // put some stuff in the collection
        var_37b51d19 = (String) var_7e105c57.get("keyB-88136"); // get it back out

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        Object[] var_be8f8018 = {"a", "b"};
        param_d1fc8eaf.getWriter().format(java.util.Locale.US, var_37b51d19, var_be8f8018);
    }
}
