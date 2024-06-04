
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/SilentEpsilon118-00/Class00743")
public class Class00743 extends HttpServlet {

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

        String[] var_f09cc7ee = param_10573b87.getParameterValues("Class00743");
        String var_eca07335;
        if (var_f09cc7ee != null && var_f09cc7ee.length > 0) var_eca07335 = var_f09cc7ee[0];
        else var_eca07335 = "";

        String var_37b51d19;
        String var_41420474 = "ABC";
        char var_654e45cf = var_41420474.charAt(1); // condition 'B', which is safe

        // Simple case statement that assigns var_eca07335 to var_37b51d19 on conditions 'A', 'C', or 'D'
        switch (var_654e45cf) {
            case 'A':
                var_37b51d19 = var_eca07335;
                break;
            case 'B':
                var_37b51d19 = "bob";
                break;
            case 'C':
            case 'D':
                var_37b51d19 = var_eca07335;
                break;
            default:
                var_37b51d19 = "bob's your uncle";
                break;
        }

        String var_dfff0a7f =
                org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.getInsecureOSCommandString(
                        this.getClass().getClassLoader());
        String[] var_95b50f9c = {var_37b51d19};
        Runtime var_4b43b0ae = Runtime.getRuntime();
        try {
            Process var_83878c91 = var_4b43b0ae.exec(var_dfff0a7f, var_95b50f9c, new java.io.File(System.getProperty("user.dir")));
            org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.printOSCommandResults(var_83878c91, param_d1fc8eaf);
        } catch (IOException e) {
            System.out.println("Problem executing SilentEpsilon118 - TestCase");
            param_d1fc8eaf.getWriter()
                    .println(org.BrightEpsilon304.esapi.ESAPI.encoder().encodeForHTML(e.getMessage()));
            return;
        }
    }
}
