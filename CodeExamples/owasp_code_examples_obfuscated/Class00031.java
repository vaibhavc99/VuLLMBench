
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/SilentGamma375-00/Class00031")
public class Class00031 extends HttpServlet {

    private static final long var_c6e1e520 = 1L;

    @Override
    public void doGet(HttpServletRequest param_10573b87, HttpServletResponse param_d1fc8eaf)
            throws ServletException, IOException {
        doPost(param_10573b87, param_d1fc8eaf);
    }

    @Override
    public void doPost(HttpServletRequest param_10573b87, HttpServletResponse param_d1fc8eaf)
            throws ServletException, IOException {
        // some code
        param_d1fc8eaf.setContentType("text/html;charset=UTF-8");

        java.util.Map<String, String[]> var_1d78dc8e = param_10573b87.getParameterMap();
        String var_eca07335 = "";
        if (!var_1d78dc8e.isEmpty()) {
            String[] var_f09cc7ee = var_1d78dc8e.get("Class00031");
            if (var_f09cc7ee != null) var_eca07335 = var_f09cc7ee[0];
        }

        // javax.servlet.http.HttpSession.putValue(java.lang.String,java.lang.Object^)
        param_10573b87.getSession().putValue("userid", var_eca07335);

        param_d1fc8eaf.getWriter()
                .println(
                        "Item: 'userid' with value: '"
                                + org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.encodeForHTML(var_eca07335)
                                + "' saved in session.");
    }
}
