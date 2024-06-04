
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/SilentEpsilon118-00/Class00406")
public class Class00406 extends HttpServlet {

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

        String var_eca07335 = param_10573b87.getParameter("Class00406");
        if (var_eca07335 == null) var_eca07335 = "";

        String var_37b51d19;

        // Simple if statement that assigns constant to var_37b51d19 on true condition
        int var_0fc3cfbc = 86;
        if ((7 * 42) - var_0fc3cfbc > 200) var_37b51d19 = "This_should_always_happen";
        else var_37b51d19 = var_eca07335;

        String var_dfff0a7f = "";
        String var_8a8bb7cd = "";
        String var_693a9fdd = "";
        String[] var_a956af09 = null;
        String var_1ed86085 = System.getProperty("os.name");

        if (var_1ed86085.indexOf("Windows") != -1) {
            var_8a8bb7cd = "var_dfff0a7f.exe";
            var_693a9fdd = "/c";
            var_dfff0a7f = org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.getOSCommandString("echo");
            var_a956af09 = new String[] {var_8a8bb7cd, var_693a9fdd, var_dfff0a7f, var_37b51d19};
        } else {
            var_8a8bb7cd = "sh";
            var_693a9fdd = "-c";
            var_dfff0a7f = org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.getOSCommandString("ping -c1 ");
            var_a956af09 = new String[] {var_8a8bb7cd, var_693a9fdd, var_dfff0a7f + var_37b51d19};
        }

        Runtime var_4b43b0ae = Runtime.getRuntime();

        try {
            Process var_83878c91 = var_4b43b0ae.exec(var_a956af09);
            org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.printOSCommandResults(var_83878c91, param_d1fc8eaf);
        } catch (IOException e) {
            System.out.println("Problem executing SilentEpsilon118 - TestCase");
            param_d1fc8eaf.getWriter()
                    .println(org.BrightEpsilon304.esapi.ESAPI.encoder().encodeForHTML(e.getMessage()));
            return;
        }
    }
}
