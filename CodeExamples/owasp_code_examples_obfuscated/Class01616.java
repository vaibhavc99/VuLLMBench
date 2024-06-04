
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/SilentGamma375-00/Class01616")
public class Class01616 extends HttpServlet {

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

        String[] var_f09cc7ee = param_10573b87.getParameterValues("Class01616");
        String param_eca07335;
        if (var_f09cc7ee != null && var_f09cc7ee.length > 0) param_eca07335 = var_f09cc7ee[0];
        else param_eca07335 = "";

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
            String var_1011bd41 = param_eca07335; // assign
            StringBuilder var_ad23710b = new StringBuilder(var_1011bd41); // stick in stringbuilder
            var_ad23710b.append(" SafeStuff"); // append some safe content
            var_ad23710b.replace(
                    var_ad23710b.length() - "Chars".length(),
                    var_ad23710b.length(),
                    "Chars"); // replace some of the end content
            java.util.HashMap<String, Object> var_c8af0b77 = new java.util.HashMap<String, Object>();
            var_c8af0b77.put("key53857", var_ad23710b.toString()); // put in a collection
            String var_0166566e = (String) var_c8af0b77.get("key53857"); // get it back out
            String var_0ed8e8e9 = var_0166566e.substring(0, var_0166566e.length() - 1); // extract most of it
            String var_ebb2989f =
                    new String(
                            org.apache.commons.codec.binary.Base64.decodeBase64(
                                    org.apache.commons.codec.binary.Base64.encodeBase64(
                                            var_0ed8e8e9.getBytes()))); // B64 encode and decode it
            String var_75bbc7e6 = var_ebb2989f.split(" ")[0]; // split it on a space
            org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                    org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
            String var_37b51d19 = var_8afc1e9b.doSomething(var_75bbc7e6); // reflection

            return var_37b51d19;
        }
    } // end innerclass Class_0cbc6611
} // end DataflowThruInnerClass
