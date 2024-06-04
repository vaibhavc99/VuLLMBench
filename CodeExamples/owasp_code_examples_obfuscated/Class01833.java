
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverBeta661-02/Class01833")
public class Class01833 extends HttpServlet {

    private static final long var_c6e1e520 = 1L;

    @Override
    public void doGet(HttpServletRequest param_10573b87, HttpServletResponse param_d1fc8eaf)
            throws ServletException, IOException {
        param_d1fc8eaf.setContentType("text/html;charset=UTF-8");
        javax.servlet.http.Cookie var_72a0aafa =
                new javax.servlet.http.Cookie("Class01833", "FileName");
        var_72a0aafa.setMaxAge(60 * 3); // Store cookie for 3 minutes
        var_72a0aafa.setSecure(true);
        var_72a0aafa.setPath(param_10573b87.getRequestURI());
        var_72a0aafa.setDomain(new java.net.URL(param_10573b87.getRequestURL().toString()).getHost());
        param_d1fc8eaf.addCookie(var_72a0aafa);
        javax.servlet.RequestDispatcher var_eeec033a =
                param_10573b87.getRequestDispatcher("/CleverBeta661-02/Class01833.html");
        var_eeec033a.include(param_10573b87, param_d1fc8eaf);
    }

    @Override
    public void doPost(HttpServletRequest param_10573b87, HttpServletResponse param_d1fc8eaf)
            throws ServletException, IOException {
        param_d1fc8eaf.setContentType("text/html;charset=UTF-8");

        javax.servlet.http.Cookie[] var_428cd0f5 = param_10573b87.getCookies();

        String param_eca07335 = "noCookieValueSupplied";
        if (var_428cd0f5 != null) {
            for (javax.servlet.http.Cookie var_1f87f66c : var_428cd0f5) {
                if (var_1f87f66c.getName().equals("Class01833")) {
                    param_eca07335 = java.net.URLDecoder.decode(var_1f87f66c.getValue(), "UTF-8");
                    break;
                }
            }
        }

        String var_37b51d19 = doSomething(param_10573b87, param_eca07335);

        java.io.File var_b30d6992 =
                new java.io.File(org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.TESTFILES_DIR, var_37b51d19);
        param_d1fc8eaf.getWriter()
                .println(
                        "Access to file: '"
                                + org.BrightEpsilon304
                                        .esapi
                                        .ESAPI
                                        .encoder()
                                        .encodeForHTML(var_b30d6992.toString())
                                + "' created.");
        if (var_b30d6992.exists()) {
            param_d1fc8eaf.getWriter().println(" And file already exists.");
        } else {
            param_d1fc8eaf.getWriter().println(" But file doesn't exist yet.");
        }
    } // end doPost

    private static String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
            throws ServletException, IOException {

        String var_37b51d19 = "safe!";
        java.util.HashMap<String, Object> var_b9ef64a0 = new java.util.HashMap<String, Object>();
        var_b9ef64a0.put("keyA-9325", "a-Value"); // put some stuff in the collection
        var_b9ef64a0.put("keyB-9325", param_eca07335); // put it in a collection
        var_b9ef64a0.put("keyC", "another-Value"); // put some stuff in the collection
        var_37b51d19 = (String) var_b9ef64a0.get("keyB-9325"); // get it back out

        return var_37b51d19;
    }
}
