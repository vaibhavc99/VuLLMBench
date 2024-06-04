
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/SilentEpsilon118-02/Class02515")
public class Class02515 extends HttpServlet {

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

        String[] var_f09cc7ee = param_10573b87.getParameterValues("Class02515");
        String param_eca07335;
        if (var_f09cc7ee != null && var_f09cc7ee.length > 0) param_eca07335 = var_f09cc7ee[0];
        else param_eca07335 = "";

        String var_37b51d19 = doSomething(param_10573b87, param_eca07335);

        String var_dfff0a7f =
                org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.getInsecureOSCommandString(
                        this.getClass().getClassLoader());

        String[] var_95b50f9c = {var_37b51d19};
        Runtime var_4b43b0ae = Runtime.getRuntime();

        try {
            Process var_83878c91 = var_4b43b0ae.exec(var_dfff0a7f, var_95b50f9c);
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

        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_37b51d19 = var_8afc1e9b.doSomething(param_eca07335);

        return var_37b51d19;
    }
}
