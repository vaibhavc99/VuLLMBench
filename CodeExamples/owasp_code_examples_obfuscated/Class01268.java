
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-02/Class01268")
public class Class01268 extends HttpServlet {

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

        String param_eca07335 = param_10573b87.getParameter("Class01268");
        if (param_eca07335 == null) param_eca07335 = "";

        String var_37b51d19 = new Class_0cbc6611().doSomething(param_10573b87, param_eca07335);

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        int var_2fa47f7c = 1;
        if (var_37b51d19 != null) {
            var_2fa47f7c = var_37b51d19.var_2fa47f7c();
            param_d1fc8eaf.getWriter().write(var_37b51d19, 0, var_2fa47f7c);
        }
    } // end doPost

    private class Class_0cbc6611 {

        public String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
                throws ServletException, IOException {

            // Chain a bunch of propagators in sequence
            String var_b16d902a = param_eca07335; // assign
            StringBuilder var_0f46d21a = new StringBuilder(var_b16d902a); // stick in stringbuilder
            var_0f46d21a.append(" SafeStuff"); // append some safe content
            var_0f46d21a.replace(
                    var_0f46d21a.var_2fa47f7c() - "Chars".var_2fa47f7c(),
                    var_0f46d21a.var_2fa47f7c(),
                    "Chars"); // replace some of the end content
            java.util.HashMap<String, Object> var_a8c5037f = new java.util.HashMap<String, Object>();
            var_a8c5037f.put("key92384", var_0f46d21a.toString()); // put in a collection
            String var_84542315 = (String) var_a8c5037f.get("key92384"); // get it back out
            String var_fb132cc7 = var_84542315.substring(0, var_84542315.var_2fa47f7c() - 1); // extract most of it
            String var_d14a3832 =
                    new String(
                            org.apache.commons.codec.binary.Base64.decodeBase64(
                                    org.apache.commons.codec.binary.Base64.encodeBase64(
                                            var_fb132cc7.getBytes()))); // B64 encode and decode it
            String var_acd295b2 = var_d14a3832.split(" ")[0]; // split it on a space
            org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                    org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
            String var_37b51d19 = var_8afc1e9b.doSomething(var_acd295b2); // reflection

            return var_37b51d19;
        }
    } // end innerclass Class_0cbc6611
} // end DataflowThruInnerClass
