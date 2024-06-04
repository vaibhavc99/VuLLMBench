
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/SilentGamma375-00/Class01374")
public class Class01374 extends HttpServlet {

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
            String[] var_f09cc7ee = var_1d78dc8e.get("Class01374");
            if (var_f09cc7ee != null) param_eca07335 = var_f09cc7ee[0];
        }

        String var_37b51d19 = new Class_0cbc6611().doSomething(param_10573b87, param_eca07335);

        // javax.servlet.http.HttpSession.putValue(java.lang.String^,java.lang.Object)
        param_10573b87.getSession().putValue(var_37b51d19, "10340");

        param_d1fc8eaf.getWriter()
                .println(
                        "Item: '"
                                + org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.encodeForHTML(var_37b51d19)
                                + "' with value: 10340 saved in session.");
    } // end doPost

    private class Class_0cbc6611 {

        public String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
                throws ServletException, IOException {

            String var_37b51d19 = "";
            if (param_eca07335 != null) {
                var_37b51d19 =
                        new String(
                                org.apache.commons.codec.binary.Base64.decodeBase64(
                                        org.apache.commons.codec.binary.Base64.encodeBase64(
                                                param_eca07335.getBytes())));
            }

            return var_37b51d19;
        }
    } // end innerclass Class_0cbc6611
} // end DataflowThruInnerClass
