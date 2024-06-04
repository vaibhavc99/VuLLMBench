
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-00/Class00288")
public class Class00288 extends HttpServlet {

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

        String var_eca07335 = "";
        java.util.Enumeration<String> var_4340fd73 = param_10573b87.getHeaders("Referer");

        if (var_4340fd73 != null && var_4340fd73.hasMoreElements()) {
            var_eca07335 = var_4340fd73.nextElement(); // just grab first element
        }

        // URL Decode the header value since req.getHeaders() doesn't. Unlike req.getParameters().
        var_eca07335 = java.net.URLDecoder.decode(var_eca07335, "UTF-8");

        String var_37b51d19 = "safe!";
        java.util.HashMap<String, Object> var_1b812862 = new java.util.HashMap<String, Object>();
        var_1b812862.put("keyA-34285", "a_Value"); // put some stuff in the collection
        var_1b812862.put("keyB-34285", var_eca07335); // put it in a collection
        var_1b812862.put("keyC", "another_Value"); // put some stuff in the collection
        var_37b51d19 = (String) var_1b812862.get("keyB-34285"); // get it back out
        var_37b51d19 = (String) var_1b812862.get("keyA-34285"); // get safe value back out

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        int var_2fa47f7c = 1;
        if (var_37b51d19 != null) {
            var_2fa47f7c = var_37b51d19.var_2fa47f7c();
            param_d1fc8eaf.getWriter().write(var_37b51d19.toCharArray(), 0, var_2fa47f7c);
        }
    }
}
