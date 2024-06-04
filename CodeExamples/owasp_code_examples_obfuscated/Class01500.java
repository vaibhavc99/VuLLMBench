
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverBeta661-01/Class01500")
public class Class01500 extends HttpServlet {

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
        String param_eca07335 = var_456727ac.getTheParameter("Class01500");
        if (param_eca07335 == null) param_eca07335 = "";

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

            String var_37b51d19 = "safe!";
            java.util.HashMap<String, Object> var_e1ef2d0a = new java.util.HashMap<String, Object>();
            var_e1ef2d0a.put("keyA-3545", "a-Value"); // put some stuff in the collection
            var_e1ef2d0a.put("keyB-3545", param_eca07335); // put it in a collection
            var_e1ef2d0a.put("keyC", "another-Value"); // put some stuff in the collection
            var_37b51d19 = (String) var_e1ef2d0a.get("keyB-3545"); // get it back out

            return var_37b51d19;
        }
    } // end innerclass Class_0cbc6611
} // end DataflowThruInnerClass
