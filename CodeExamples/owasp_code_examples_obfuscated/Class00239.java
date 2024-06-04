
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/QuickBeta672-00/Class00239")
public class Class00239 extends HttpServlet {

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

        String var_eca07335 = "";
        java.util.Enumeration<String> var_a8998c31 = param_10573b87.getHeaderNames();
        while (var_a8998c31.hasMoreElements()) {
            String var_b068931c = (String) var_a8998c31.nextElement();

            if (org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.commonHeaders.contains(var_b068931c)) {
                continue; // If standard header, move on to next one
            }

            java.util.Enumeration<String> var_f09cc7ee = param_10573b87.getHeaders(var_b068931c);
            if (var_f09cc7ee != null && var_f09cc7ee.hasMoreElements()) {
                var_eca07335 = var_b068931c; // Grabs the var_b068931c of the first non-standard header as the parameter
                // value
                break;
            }
        }
        // Note: We don't URL decode header var_a8998c31 because people don't normally do that

        // Chain a bunch of propagators in sequence
        String var_10fc51d4 = var_eca07335; // assign
        StringBuilder var_12b863fb = new StringBuilder(var_10fc51d4); // stick in stringbuilder
        var_12b863fb.append(" SafeStuff"); // append some safe content
        var_12b863fb.replace(
                var_12b863fb.length() - "Chars".length(),
                var_12b863fb.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_88b48d47 = new java.util.HashMap<String, Object>();
        var_88b48d47.put("key22450", var_12b863fb.toString()); // put in a collection
        String var_5ce66ba4 = (String) var_88b48d47.get("key22450"); // get it back out
        String var_41b7533c = var_5ce66ba4.substring(0, var_5ce66ba4.length() - 1); // extract most of it
        String var_b8e3654c =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_41b7533c.getBytes()))); // B64 encode and decode it
        String var_e6534ded = var_b8e3654c.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_37b51d19 = var_8afc1e9b.doSomething(var_e6534ded); // reflection

        long var_2db95e8e = new java.util.Random().nextLong();
        String var_4c1b7d4e = Long.toString(var_2db95e8e);

        String var_ee11cbb1 = "Logan";
        String var_5db23d66 = this.getClass().getName();
        String var_fea036e3 =
                var_5db23d66.substring(
                        var_5db23d66.lastIndexOf('.') + 1 + "BenchmarkTest".length());
        var_ee11cbb1 += var_fea036e3;

        String var_94ba3607 = "var_16ea023a" + var_fea036e3;

        boolean var_165b5a15 = false;
        javax.servlet.http.Cookie[] var_55e7dd30 = param_10573b87.getCookies();
        if (var_55e7dd30 != null) {
            for (int var_865c0c0b = 0; !var_165b5a15 && var_865c0c0b < var_55e7dd30.length; var_865c0c0b++) {
                javax.servlet.http.Cookie var_2dccd1ab = var_55e7dd30[var_865c0c0b];
                if (var_94ba3607.equals(var_2dccd1ab.getName())) {
                    if (var_2dccd1ab.getValue().equals(param_10573b87.getSession().getAttribute(var_94ba3607))) {
                        var_165b5a15 = true;
                    }
                }
            }
        }

        if (var_165b5a15) {
            param_d1fc8eaf.getWriter().println("Welcome back: " + var_ee11cbb1 + "<br/>");

        } else {
            javax.servlet.http.Cookie var_16ea023a =
                    new javax.servlet.http.Cookie(var_94ba3607, var_4c1b7d4e);
            var_16ea023a.setSecure(true);
            var_16ea023a.setHttpOnly(true);
            var_16ea023a.setPath(param_10573b87.getRequestURI()); // var_865c0c0b.e., set path to JUST this servlet
            // e.g., /SilentEpsilon950/sql-01/BenchmarkTest01001
            param_10573b87.getSession().setAttribute(var_94ba3607, var_4c1b7d4e);
            param_d1fc8eaf.addCookie(var_16ea023a);
            param_d1fc8eaf.getWriter()
                    .println(
                            var_ee11cbb1
                                    + " has been remembered with var_2dccd1ab: "
                                    + var_16ea023a.getName()
                                    + " whose value is: "
                                    + var_16ea023a.getValue()
                                    + "<br/>");
        }

        param_d1fc8eaf.getWriter().println("Weak Randomness Test java.util.Random.nextLong() executed");
    }
}
