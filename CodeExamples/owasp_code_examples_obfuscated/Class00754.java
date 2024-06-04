
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/SilentGamma375-00/Class00754")
public class Class00754 extends HttpServlet {

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

        String[] var_f09cc7ee = param_10573b87.getParameterValues("Class00754");
        String var_eca07335;
        if (var_f09cc7ee != null && var_f09cc7ee.length > 0) var_eca07335 = var_f09cc7ee[0];
        else var_eca07335 = "";

        String var_37b51d19 = "safe!";
        java.util.HashMap<String, Object> var_c4ee913b = new java.util.HashMap<String, Object>();
        var_c4ee913b.put("keyA-6425", "a-Value"); // put some stuff in the collection
        var_c4ee913b.put("keyB-6425", var_eca07335); // put it in a collection
        var_c4ee913b.put("keyC", "another-Value"); // put some stuff in the collection
        var_37b51d19 = (String) var_c4ee913b.get("keyB-6425"); // get it back out

        // javax.servlet.http.HttpSession.putValue(java.lang.String,java.lang.Object^)
        param_10573b87.getSession().putValue("userid", var_37b51d19);

        param_d1fc8eaf.getWriter()
                .println(
                        "Item: 'userid' with value: '"
                                + org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.encodeForHTML(var_37b51d19)
                                + "' saved in session.");
    }
}
