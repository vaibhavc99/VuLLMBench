
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/SilentEpsilon118-02/Class02154")
public class Class02154 extends HttpServlet {

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

        String param_eca07335 = param_10573b87.getParameter("Class02154");
        if (param_eca07335 == null) param_eca07335 = "";

        String var_37b51d19 = doSomething(param_10573b87, param_eca07335);

        String var_dfff0a7f = "";
        String var_1ed86085 = System.getProperty("os.name");
        if (var_1ed86085.indexOf("Windows") != -1) {
            var_dfff0a7f = org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.getOSCommandString("echo");
        }

        String[] var_95b50f9c = {"Foo=var_37b51d19"};
        Runtime var_4b43b0ae = Runtime.getRuntime();

        try {
            Process var_83878c91 =
                    var_4b43b0ae.exec(var_dfff0a7f + var_37b51d19, var_95b50f9c, new java.io.File(System.getProperty("user.dir")));
            org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.printOSCommandResults(var_83878c91, param_d1fc8eaf);
        } catch (IOException e) {
            System.out.println("Problem executing SilentEpsilon118 - TestCase");
            param_d1fc8eaf.getWriter()
                    .println(org.BrightEpsilon304.esapi.ESAPI.encoder().encodeForHTML(e.getMessage()));
            return;
        }
    } // end doPost

    private static String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
            throws ServletException, IOException {

        String var_37b51d19;

        // Simple ? condition that assigns param_eca07335 to var_37b51d19 on false condition
        int var_0fc3cfbc = 106;

        var_37b51d19 = (7 * 42) - var_0fc3cfbc > 200 ? "This should never happen" : param_eca07335;

        return var_37b51d19;
    }
}
