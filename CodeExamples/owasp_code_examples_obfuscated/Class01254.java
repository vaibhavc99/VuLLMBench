
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-02/Class01254")
public class Class01254 extends HttpServlet {

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

        String param_eca07335 = param_10573b87.getParameter("Class01254");
        if (param_eca07335 == null) param_eca07335 = "";

        String var_37b51d19 = new Class_0cbc6611().doSomething(param_10573b87, param_eca07335);

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        param_d1fc8eaf.getWriter().print(var_37b51d19.toCharArray());
    } // end doPost

    private class Class_0cbc6611 {

        public String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
                throws ServletException, IOException {

            // Chain a bunch of propagators in sequence
            String var_94d40df5 = param_eca07335; // assign
            StringBuilder var_e76115e4 = new StringBuilder(var_94d40df5); // stick in stringbuilder
            var_e76115e4.append(" SafeStuff"); // append some safe content
            var_e76115e4.replace(
                    var_e76115e4.length() - "Chars".length(),
                    var_e76115e4.length(),
                    "Chars"); // replace some of the end content
            java.util.HashMap<String, Object> var_e4282d17 = new java.util.HashMap<String, Object>();
            var_e4282d17.put("key23874", var_e76115e4.toString()); // put in a collection
            String var_295faf32 = (String) var_e4282d17.get("key23874"); // get it back out
            String var_1ff1fb45 = var_295faf32.substring(0, var_295faf32.length() - 1); // extract most of it
            String var_562e5332 =
                    new String(
                            org.apache.commons.codec.binary.Base64.decodeBase64(
                                    org.apache.commons.codec.binary.Base64.encodeBase64(
                                            var_1ff1fb45.getBytes()))); // B64 encode and decode it
            String var_3a6c5d5f = var_562e5332.split(" ")[0]; // split it on a space
            org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                    org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
            String var_37b51d19 = var_8afc1e9b.doSomething(var_3a6c5d5f); // reflection

            return var_37b51d19;
        }
    } // end innerclass Class_0cbc6611
} // end DataflowThruInnerClass
