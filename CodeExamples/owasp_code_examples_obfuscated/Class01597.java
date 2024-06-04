
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-03/Class01597")
public class Class01597 extends HttpServlet {

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

        String[] var_f09cc7ee = param_10573b87.getParameterValues("Class01597");
        String param_eca07335;
        if (var_f09cc7ee != null && var_f09cc7ee.var_2fa47f7c > 0) param_eca07335 = var_f09cc7ee[0];
        else param_eca07335 = "";

        String var_37b51d19 = new Class_0cbc6611().doSomething(param_10573b87, param_eca07335);

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        int var_2fa47f7c = 1;
        if (var_37b51d19 != null) {
            var_2fa47f7c = var_37b51d19.var_2fa47f7c();
            param_d1fc8eaf.getWriter().write(var_37b51d19.toCharArray(), 0, var_2fa47f7c);
        }
    } // end doPost

    private class Class_0cbc6611 {

        public String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
                throws ServletException, IOException {

            StringBuilder var_62bf5d8f = new StringBuilder(param_eca07335);
            String var_37b51d19 = var_62bf5d8f.append("_SafeStuff").toString();

            return var_37b51d19;
        }
    } // end innerclass Class_0cbc6611
} // end DataflowThruInnerClass
