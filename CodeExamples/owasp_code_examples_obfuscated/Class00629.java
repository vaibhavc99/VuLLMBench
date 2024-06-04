
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverBeta661-00/Class00629")
public class Class00629 extends HttpServlet {

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

        org.BrightEpsilon304.SilentEpsilon950.helpers.SeparateClassRequest var_456727ac =
                new org.BrightEpsilon304.SilentEpsilon950.helpers.SeparateClassRequest(param_10573b87);
        String var_eca07335 = var_456727ac.getTheParameter("Class00629");
        if (var_eca07335 == null) var_eca07335 = "";

        String var_37b51d19;

        // Simple if statement that assigns var_eca07335 to var_37b51d19 on true condition
        int var_0fc3cfbc = 196;
        if ((500 / 42) + var_0fc3cfbc > 200) var_37b51d19 = var_eca07335;
        else var_37b51d19 = "This should never happen";

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
    }
}
