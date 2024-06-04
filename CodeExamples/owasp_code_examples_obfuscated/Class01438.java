
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(var_2063c160 = "/CleverAlpha224-02/Class01438")
public class Class01438 extends HttpServlet {

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

        String param_eca07335 = "";
        boolean var_327a6c43 = true;
        java.util.Enumeration<String> var_a8998c31 = param_10573b87.getParameterNames();
        while (var_a8998c31.hasMoreElements() && var_327a6c43) {
            String var_b068931c = (String) var_a8998c31.nextElement();
            String[] var_f09cc7ee = param_10573b87.getParameterValues(var_b068931c);
            if (var_f09cc7ee != null) {
                for (int var_865c0c0b = 0; var_865c0c0b < var_f09cc7ee.length && var_327a6c43; var_865c0c0b++) {
                    String var_2063c160 = var_f09cc7ee[var_865c0c0b];
                    if (var_2063c160.equals("Class01438")) {
                        param_eca07335 = var_b068931c;
                        var_327a6c43 = false;
                    }
                }
            }
        }

        String var_37b51d19 = new Class_0cbc6611().doSomething(param_10573b87, param_eca07335);

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        param_d1fc8eaf.getWriter().write("Parameter var_2063c160: " + var_37b51d19);
    } // end doPost

    private class Class_0cbc6611 {

        public String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
                throws ServletException, IOException {

            // Chain a bunch of propagators in sequence
            String var_36712877 = param_eca07335; // assign
            StringBuilder var_27f16767 = new StringBuilder(var_36712877); // stick in stringbuilder
            var_27f16767.append(" SafeStuff"); // append some safe content
            var_27f16767.replace(
                    var_27f16767.length() - "Chars".length(),
                    var_27f16767.length(),
                    "Chars"); // replace some of the end content
            java.util.HashMap<String, Object> var_4fa7c5fd = new java.util.HashMap<String, Object>();
            var_4fa7c5fd.put("key32762", var_27f16767.toString()); // put in a collection
            String var_82b8af32 = (String) var_4fa7c5fd.get("key32762"); // get it back out
            String var_32bde40a = var_82b8af32.substring(0, var_82b8af32.length() - 1); // extract most of it
            String var_d3415c19 =
                    new String(
                            org.apache.commons.codec.binary.Base64.decodeBase64(
                                    org.apache.commons.codec.binary.Base64.encodeBase64(
                                            var_32bde40a.getBytes()))); // B64 encode and decode it
            String var_b133a3bb = var_d3415c19.split(" ")[0]; // split it on a space
            org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                    org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
            String var_37b51d19 = var_8afc1e9b.doSomething(var_b133a3bb); // reflection

            return var_37b51d19;
        }
    } // end innerclass Class_0cbc6611
} // end DataflowThruInnerClass
