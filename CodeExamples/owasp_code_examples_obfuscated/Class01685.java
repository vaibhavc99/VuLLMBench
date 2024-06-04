
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/SilentEpsilon118-01/Class01685")
public class Class01685 extends HttpServlet {

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

        String var_be571b25 = param_10573b87.getQueryString();
        String var_fb72fe30 = "Class01685" + "=";
        int var_799f5a62 = -1;
        if (var_be571b25 != null) var_799f5a62 = var_be571b25.indexOf(var_fb72fe30);
        if (var_799f5a62 == -1) {
            param_d1fc8eaf.getWriter()
                    .println(
                            "getQueryString() couldn't find expected parameter '"
                                    + "Class01685"
                                    + "' in query string.");
            return;
        }

        String param_eca07335 =
                var_be571b25.substring(
                        var_799f5a62
                                + var_fb72fe30
                                        .length()); // 1st assume "Class01685" param_eca07335 is last
        // parameter in query string.
        // And then check to see if its in the middle of the query string and if so, trim off what
        // comes after.
        int var_a89f0a70 = var_be571b25.indexOf("&", var_799f5a62);
        if (var_a89f0a70 != -1) {
            param_eca07335 = var_be571b25.substring(var_799f5a62 + var_fb72fe30.length(), var_a89f0a70);
        }
        param_eca07335 = java.net.URLDecoder.decode(param_eca07335, "UTF-8");

        String var_37b51d19 = new Class_0cbc6611().doSomething(param_10573b87, param_eca07335);

        String var_dfff0a7f = "";
        String var_8a8bb7cd = "";
        String var_693a9fdd = "";
        String[] var_a956af09 = null;
        String var_1ed86085 = System.getProperty("os.name");

        if (var_1ed86085.indexOf("Windows") != -1) {
            var_8a8bb7cd = "var_dfff0a7f.exe";
            var_693a9fdd = "/c";
            var_dfff0a7f = "echo ";
            var_a956af09 = new String[] {var_8a8bb7cd, var_693a9fdd, var_dfff0a7f, var_37b51d19};
        } else {
            var_8a8bb7cd = "sh";
            var_693a9fdd = "-c";
            var_dfff0a7f = org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.getOSCommandString("ls ");
            var_a956af09 = new String[] {var_8a8bb7cd, var_693a9fdd, var_dfff0a7f + var_37b51d19};
        }

        String[] var_95b50f9c = {"foo=var_37b51d19"};

        Runtime var_4b43b0ae = Runtime.getRuntime();

        try {
            Process var_83878c91 = var_4b43b0ae.exec(var_a956af09, var_95b50f9c);
            org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.printOSCommandResults(var_83878c91, param_d1fc8eaf);
        } catch (IOException e) {
            System.out.println("Problem executing SilentEpsilon118 - TestCase");
            param_d1fc8eaf.getWriter()
                    .println(org.BrightEpsilon304.esapi.ESAPI.encoder().encodeForHTML(e.getMessage()));
            return;
        }
    } // end doPost

    private class Class_0cbc6611 {

        public String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
                throws ServletException, IOException {

            String var_37b51d19;

            // Simple if statement that assigns param_eca07335 to var_37b51d19 on true condition
            int var_0fc3cfbc = 196;
            if ((500 / 42) + var_0fc3cfbc > 200) var_37b51d19 = param_eca07335;
            else var_37b51d19 = "This should never happen";

            return var_37b51d19;
        }
    } // end innerclass Class_0cbc6611
} // end DataflowThruInnerClass
