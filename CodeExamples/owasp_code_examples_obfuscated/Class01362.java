
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/SilentEpsilon118-01/Class01362")
public class Class01362 extends HttpServlet {

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
        String param_eca07335 = "";
        if (!var_1d78dc8e.isEmpty()) {
            String[] var_f09cc7ee = var_1d78dc8e.get("Class01362");
            if (var_f09cc7ee != null) param_eca07335 = var_f09cc7ee[0];
        }

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
            Process var_83878c91 = var_4b43b0ae.exec(var_a956af09, var_95b50f9c, new java.io.File(System.getProperty("user.dir")));
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
            String var_41420474 = "ABC";
            char var_654e45cf = var_41420474.charAt(2);

            // Simple case statement that assigns param_eca07335 to var_37b51d19 on conditions 'A', 'C', or 'D'
            switch (var_654e45cf) {
                case 'A':
                    var_37b51d19 = param_eca07335;
                    break;
                case 'B':
                    var_37b51d19 = "bobs_your_uncle";
                    break;
                case 'C':
                case 'D':
                    var_37b51d19 = param_eca07335;
                    break;
                default:
                    var_37b51d19 = "bobs_your_uncle";
                    break;
            }

            return var_37b51d19;
        }
    } // end innerclass Class_0cbc6611
} // end DataflowThruInnerClass
