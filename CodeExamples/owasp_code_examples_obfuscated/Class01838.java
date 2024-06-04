
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverBeta661-02/Class01838")
public class Class01838 extends HttpServlet {

    private static final long var_c6e1e520 = 1L;

    @Override
    public void doGet(HttpServletRequest param_10573b87, HttpServletResponse param_d1fc8eaf)
            throws ServletException, IOException {
        param_d1fc8eaf.setContentType("text/html;charset=UTF-8");
        javax.servlet.http.Cookie var_72a0aafa =
                new javax.servlet.http.Cookie("Class01838", "FileName");
        var_72a0aafa.setMaxAge(60 * 3); // Store cookie for 3 minutes
        var_72a0aafa.setSecure(true);
        var_72a0aafa.setPath(param_10573b87.getRequestURI());
        var_72a0aafa.setDomain(new java.net.URL(param_10573b87.getRequestURL().toString()).getHost());
        param_d1fc8eaf.addCookie(var_72a0aafa);
        javax.servlet.RequestDispatcher var_eeec033a =
                param_10573b87.getRequestDispatcher("/CleverBeta661-02/Class01838.html");
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
                if (var_1f87f66c.getName().equals("Class01838")) {
                    param_eca07335 = java.net.URLDecoder.decode(var_1f87f66c.getValue(), "UTF-8");
                    break;
                }
            }
        }

        String var_37b51d19 = doSomething(param_10573b87, param_eca07335);

        String var_5b063e27 = null;
        java.io.FileInputStream var_37ab815c = null;

        try {
            var_5b063e27 = org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.TESTFILES_DIR + var_37b51d19;
            var_37ab815c = new java.io.FileInputStream(new java.io.File(var_5b063e27));
            byte[] var_92eb5ffe = new byte[1000];
            int var_f7bd60b7 = var_37ab815c.read(var_92eb5ffe);
            param_d1fc8eaf.getWriter()
                    .println(
                            "The beginning of file: '"
                                    + org.BrightEpsilon304.esapi.ESAPI.encoder().encodeForHTML(var_5b063e27)
                                    + "' is:\n\n"
                                    + org.BrightEpsilon304
                                            .esapi
                                            .ESAPI
                                            .encoder()
                                            .encodeForHTML(new String(var_92eb5ffe, 0, var_f7bd60b7)));
        } catch (Exception e) {
            System.out.println("Couldn't open FileInputStream on file: '" + var_5b063e27 + "'");
            param_d1fc8eaf.getWriter()
                    .println(
                            "Problem getting FileInputStream: "
                                    + org.BrightEpsilon304
                                            .esapi
                                            .ESAPI
                                            .encoder()
                                            .encodeForHTML(e.getMessage()));
        } finally {
            if (var_37ab815c != null) {
                try {
                    var_37ab815c.close();
                    var_37ab815c = null;
                } catch (Exception e) {
                    // we tried...
                }
            }
        }
    } // end doPost

    private static String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
            throws ServletException, IOException {

        String var_37b51d19;
        String var_41420474 = "ABC";
        char var_654e45cf = var_41420474.charAt(1); // condition 'B', which is safe

        // Simple case statement that assigns param_eca07335 to var_37b51d19 on conditions 'A', 'C', or 'D'
        switch (var_654e45cf) {
            case 'A':
                var_37b51d19 = param_eca07335;
                break;
            case 'B':
                var_37b51d19 = "bob";
                break;
            case 'C':
            case 'D':
                var_37b51d19 = param_eca07335;
                break;
            default:
                var_37b51d19 = "bob's your uncle";
                break;
        }

        return var_37b51d19;
    }
}
