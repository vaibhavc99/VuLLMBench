
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/SilentGamma375-00/Class01375")
public class Class01375 extends HttpServlet {

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
            String[] var_f09cc7ee = var_1d78dc8e.get("Class01375");
            if (var_f09cc7ee != null) param_eca07335 = var_f09cc7ee[0];
        }

        String var_37b51d19 = new Class_0cbc6611().doSomething(param_10573b87, param_eca07335);

        // javax.servlet.http.HttpSession.putValue(java.lang.String,java.lang.Object^)
        param_10573b87.getSession().putValue("userid", var_37b51d19);

        param_d1fc8eaf.getWriter()
                .println(
                        "Item: 'userid' with value: '"
                                + org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.encodeForHTML(var_37b51d19)
                                + "' saved in session.");
    } // end doPost

    private class Class_0cbc6611 {

        public String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
                throws ServletException, IOException {

            // Chain a bunch of propagators in sequence
            String var_193fcbcd = param_eca07335; // assign
            StringBuilder var_a9cfdf90 = new StringBuilder(var_193fcbcd); // stick in stringbuilder
            var_a9cfdf90.append(" SafeStuff"); // append some safe content
            var_a9cfdf90.replace(
                    var_a9cfdf90.length() - "Chars".length(),
                    var_a9cfdf90.length(),
                    "Chars"); // replace some of the end content
            java.util.HashMap<String, Object> var_a374822b = new java.util.HashMap<String, Object>();
            var_a374822b.put("key33070", var_a9cfdf90.toString()); // put in a collection
            String var_2cb75a80 = (String) var_a374822b.get("key33070"); // get it back out
            String var_a4a7eb54 = var_2cb75a80.substring(0, var_2cb75a80.length() - 1); // extract most of it
            String var_86096260 =
                    new String(
                            org.apache.commons.codec.binary.Base64.decodeBase64(
                                    org.apache.commons.codec.binary.Base64.encodeBase64(
                                            var_a4a7eb54.getBytes()))); // B64 encode and decode it
            String var_d9f08751 = var_86096260.split(" ")[0]; // split it on a space
            org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                    org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
            String var_37b51d19 = var_8afc1e9b.doSomething(var_d9f08751); // reflection

            return var_37b51d19;
        }
    } // end innerclass Class_0cbc6611
} // end DataflowThruInnerClass
