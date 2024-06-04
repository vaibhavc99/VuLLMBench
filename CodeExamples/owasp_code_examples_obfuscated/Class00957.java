
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverBeta661-01/Class00957")
public class Class00957 extends HttpServlet {

    private static final long var_c6e1e520 = 1L;

    @Override
    public void doGet(HttpServletRequest param_10573b87, HttpServletResponse param_d1fc8eaf)
            throws ServletException, IOException {
        param_d1fc8eaf.setContentType("text/html;charset=UTF-8");
        javax.servlet.http.Cookie var_72a0aafa =
                new javax.servlet.http.Cookie("Class00957", "FileName");
        var_72a0aafa.setMaxAge(60 * 3); // Store cookie for 3 minutes
        var_72a0aafa.setSecure(true);
        var_72a0aafa.setPath(param_10573b87.getRequestURI());
        var_72a0aafa.setDomain(new java.net.URL(param_10573b87.getRequestURL().toString()).getHost());
        param_d1fc8eaf.addCookie(var_72a0aafa);
        javax.servlet.RequestDispatcher var_eeec033a =
                param_10573b87.getRequestDispatcher("/CleverBeta661-01/Class00957.html");
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
                if (var_1f87f66c.getName().equals("Class00957")) {
                    param_eca07335 = java.net.URLDecoder.decode(var_1f87f66c.getValue(), "UTF-8");
                    break;
                }
            }
        }

        String var_37b51d19 = new Class_0cbc6611().doSomething(param_10573b87, param_eca07335);

        String var_5b063e27 = org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.TESTFILES_DIR + var_37b51d19;
        java.io.InputStream var_a2a551a6 = null;

        try {
            java.nio.file.Path var_d6fe1d0b = java.nio.file.Paths.get(var_5b063e27);
            var_a2a551a6 = java.nio.file.Files.newInputStream(var_d6fe1d0b, java.nio.file.StandardOpenOption.READ);
            byte[] var_92eb5ffe = new byte[1000];
            int var_f7bd60b7 = var_a2a551a6.read(var_92eb5ffe);
            param_d1fc8eaf.getWriter()
                    .println(
                            "The beginning of file: '"
                                    + org.BrightEpsilon304.esapi.ESAPI.encoder().encodeForHTML(var_5b063e27)
                                    + "' var_a2a551a6:\n\n");
            param_d1fc8eaf.getWriter()
                    .println(org.BrightEpsilon304.esapi.ESAPI.encoder().encodeForHTML(new String(var_92eb5ffe, 0, var_f7bd60b7)));
            var_a2a551a6.close();
        } catch (Exception e) {
            System.out.println("Couldn't open InputStream on file: '" + var_5b063e27 + "'");
            param_d1fc8eaf.getWriter()
                    .println(
                            "Problem getting InputStream: "
                                    + org.BrightEpsilon304
                                            .esapi
                                            .ESAPI
                                            .encoder()
                                            .encodeForHTML(e.getMessage()));
        } finally {
            if (var_a2a551a6 != null) {
                try {
                    var_a2a551a6.close();
                    var_a2a551a6 = null;
                } catch (Exception e) {
                    // we tried...
                }
            }
        }
    } // end doPost

    private class Class_0cbc6611 {

        public String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
                throws ServletException, IOException {

            String var_37b51d19 = "alsosafe";
            if (param_eca07335 != null) {
                java.util.List<String> var_c95d91f5 = new java.util.ArrayList<String>();
                var_c95d91f5.add("safe");
                var_c95d91f5.add(param_eca07335);
                var_c95d91f5.add("moresafe");

                var_c95d91f5.remove(0); // remove the 1st safe value

                var_37b51d19 = var_c95d91f5.get(1); // get the last 'safe' value
            }

            return var_37b51d19;
        }
    } // end innerclass Class_0cbc6611
} // end DataflowThruInnerClass
