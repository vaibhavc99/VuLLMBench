
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-03/Class01515")
public class Class01515 extends HttpServlet {

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
        String param_eca07335 = var_456727ac.getTheParameter("Class01515");
        if (param_eca07335 == null) param_eca07335 = "";

        String var_37b51d19 = new Class_0cbc6611().doSomething(param_10573b87, param_eca07335);

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        param_d1fc8eaf.getWriter().write(var_37b51d19);
    } // end doPost

    private class Class_0cbc6611 {

        public String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
                throws ServletException, IOException {

            // Chain a bunch of propagators in sequence
            String var_ce3e1c59 = param_eca07335; // assign
            StringBuilder var_8f9cd617 = new StringBuilder(var_ce3e1c59); // stick in stringbuilder
            var_8f9cd617.append(" SafeStuff"); // append some safe content
            var_8f9cd617.replace(
                    var_8f9cd617.length() - "Chars".length(),
                    var_8f9cd617.length(),
                    "Chars"); // replace some of the end content
            java.util.HashMap<String, Object> var_1f0acd18 = new java.util.HashMap<String, Object>();
            var_1f0acd18.put("key84124", var_8f9cd617.toString()); // put in a collection
            String var_80fb01cd = (String) var_1f0acd18.get("key84124"); // get it back out
            String var_e47fe435 = var_80fb01cd.substring(0, var_80fb01cd.length() - 1); // extract most of it
            String var_8230f52a =
                    new String(
                            org.apache.commons.codec.binary.Base64.decodeBase64(
                                    org.apache.commons.codec.binary.Base64.encodeBase64(
                                            var_e47fe435.getBytes()))); // B64 encode and decode it
            String var_45c177c7 = var_8230f52a.split(" ")[0]; // split it on a space
            org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                    org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
            String var_f3b814ac = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
            String var_37b51d19 = var_8afc1e9b.doSomething(var_f3b814ac); // reflection

            return var_37b51d19;
        }
    } // end innerclass Class_0cbc6611
} // end DataflowThruInnerClass
