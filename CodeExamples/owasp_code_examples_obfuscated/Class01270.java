
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/SilentEpsilon118-01/Class01270")
public class Class01270 extends HttpServlet {

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

        String param_eca07335 = param_10573b87.getParameter("Class01270");
        if (param_eca07335 == null) param_eca07335 = "";

        String var_37b51d19 = new Class_0cbc6611().doSomething(param_10573b87, param_eca07335);

        String var_8a8bb7cd = "";
        String var_693a9fdd = "";
        String var_1ed86085 = System.getProperty("os.name");
        if (var_1ed86085.indexOf("Windows") != -1) {
            var_8a8bb7cd = "cmd.exe";
            var_693a9fdd = "/c";
        } else {
            var_8a8bb7cd = "sh";
            var_693a9fdd = "-c";
        }
        String[] var_a956af09 = {var_8a8bb7cd, var_693a9fdd, "echo " + var_37b51d19};

        ProcessBuilder var_f09883b5 = new ProcessBuilder(var_a956af09);

        try {
            Process var_83878c91 = var_f09883b5.start();
            org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.printOSCommandResults(var_83878c91, param_d1fc8eaf);
        } catch (IOException e) {
            System.out.println(
                    "Problem executing SilentEpsilon118 - java.lang.ProcessBuilder(java.lang.String[]) Class_0cbc6611 Case");
            throw new ServletException(e);
        }
    } // end doPost

    private class Class_0cbc6611 {

        public String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
                throws ServletException, IOException {

            String var_37b51d19;

            // Simple ? condition that assigns param_eca07335 to var_37b51d19 on false condition
            int var_0fc3cfbc = 106;

            var_37b51d19 = (7 * 42) - var_0fc3cfbc > 200 ? "This should never happen" : param_eca07335;

            return var_37b51d19;
        }
    } // end innerclass Class_0cbc6611
} // end DataflowThruInnerClass
