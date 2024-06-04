
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/SilentGamma375-00/Class00425")
public class Class00425 extends HttpServlet {

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

        String var_eca07335 = param_10573b87.getParameter("Class00425");
        if (var_eca07335 == null) var_eca07335 = "";

        StringBuilder var_8d0ec53f = new StringBuilder(var_eca07335);
        String var_37b51d19 = var_8d0ec53f.append("_SafeStuff").toString();

        // javax.servlet.http.HttpSession.putValue(java.lang.String,java.lang.Object^)
        param_10573b87.getSession().putValue("userid", var_37b51d19);

        param_d1fc8eaf.getWriter()
                .println(
                        "Item: 'userid' with value: '"
                                + org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.encodeForHTML(var_37b51d19)
                                + "' saved in session.");
    }
}
