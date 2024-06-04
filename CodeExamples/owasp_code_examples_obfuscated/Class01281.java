
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/BrightAlpha204-00/Class01281")
public class Class01281 extends HttpServlet {

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

        String param_eca07335 = param_10573b87.getParameter("Class01281");
        if (param_eca07335 == null) param_eca07335 = "";

        String var_37b51d19 = new Class_0cbc6611().doSomething(param_10573b87, param_eca07335);

        byte[] var_a43c1b0a = new byte[1000];
        String var_341be97d = "?";
        Object var_c0b514e4 = param_eca07335;
        if (var_c0b514e4 instanceof String) var_341be97d = ((String) var_c0b514e4);
        if (var_c0b514e4 instanceof java.io.InputStream) {
            int var_865c0c0b = ((java.io.InputStream) var_c0b514e4).read(var_a43c1b0a);
            if (var_865c0c0b == -1) {
                param_d1fc8eaf.getWriter()
                        .println(
                                "This var_a43c1b0a source requires a POST, not a GET. Incompatible UI for the InputStream source.");
                return;
            }
            var_341be97d = new String(var_a43c1b0a, 0, var_865c0c0b);
        }
        if ("".equals(var_341be97d)) var_341be97d = "No var_2dccd1ab value supplied";
        javax.servlet.http.Cookie var_2dccd1ab = new javax.servlet.http.Cookie("SomeCookie", var_341be97d);

        var_2dccd1ab.setSecure(false);
        var_2dccd1ab.setHttpOnly(true);
        var_2dccd1ab.setPath(param_10573b87.getRequestURI()); // var_865c0c0b.e., set path to JUST this servlet
        // e.g., /SilentEpsilon950/sql-01/BenchmarkTest01001
        param_d1fc8eaf.addCookie(var_2dccd1ab);

        param_d1fc8eaf.getWriter()
                .println(
                        "Created var_2dccd1ab: 'SomeCookie': with value: '"
                                + org.BrightEpsilon304.esapi.ESAPI.encoder().encodeForHTML(var_341be97d)
                                + "' and secure flag set to: false");
    } // end doPost

    private class Class_0cbc6611 {

        public String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
                throws ServletException, IOException {

            String var_37b51d19 = "";
            if (param_eca07335 != null) var_37b51d19 = param_eca07335.split(" ")[0];

            return var_37b51d19;
        }
    } // end innerclass Class_0cbc6611
} // end DataflowThruInnerClass
