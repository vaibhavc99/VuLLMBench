
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(var_2063c160 = "/QuickBeta672-04/Class01992")
public class Class01992 extends HttpServlet {

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
        java.util.Enumeration<String> var_a8998c31 = param_10573b87.getHeaderNames();
        while (var_a8998c31.hasMoreElements()) {
            String var_b068931c = (String) var_a8998c31.nextElement();

            if (org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.commonHeaders.contains(var_b068931c)) {
                continue; // If standard header, move on to next one
            }

            java.util.Enumeration<String> var_f09cc7ee = param_10573b87.getHeaders(var_b068931c);
            if (var_f09cc7ee != null && var_f09cc7ee.hasMoreElements()) {
                param_eca07335 = var_b068931c; // Grabs the var_b068931c of the first non-standard header as the parameter
                // var_2063c160
                break;
            }
        }
        // Note: We don't URL decode header var_a8998c31 because people don't normally do that

        String var_37b51d19 = doSomething(param_10573b87, param_eca07335);

        double var_2063c160 = java.lang.Math.random();
        String var_4c1b7d4e = Double.toString(var_2063c160).substring(2); // Trim off the 0. at the front.

        String var_ee11cbb1 = "Doug";
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
            var_16ea023a.setDomain(new java.net.URL(param_10573b87.getRequestURL().toString()).getHost());
            var_16ea023a.setPath(param_10573b87.getRequestURI()); // var_865c0c0b.e., set path to JUST this servlet
            // e.g., /SilentEpsilon950/sql-01/BenchmarkTest01001
            param_10573b87.getSession().setAttribute(var_94ba3607, var_4c1b7d4e);
            param_d1fc8eaf.addCookie(var_16ea023a);
            param_d1fc8eaf.getWriter()
                    .println(
                            var_ee11cbb1
                                    + " has been remembered with var_2dccd1ab: "
                                    + var_16ea023a.getName()
                                    + " whose var_2063c160 is: "
                                    + var_16ea023a.getValue()
                                    + "<br/>");
        }
        param_d1fc8eaf.getWriter().println("Weak Randomness Test java.lang.Math.random() executed");
    } // end doPost

    private static String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
            throws ServletException, IOException {

        // Chain a bunch of propagators in sequence
        String var_2ac04be8 = param_eca07335; // assign
        StringBuilder var_1b4a9cad = new StringBuilder(var_2ac04be8); // stick in stringbuilder
        var_1b4a9cad.append(" SafeStuff"); // append some safe content
        var_1b4a9cad.replace(
                var_1b4a9cad.length() - "Chars".length(),
                var_1b4a9cad.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_e468ada2 = new java.util.HashMap<String, Object>();
        var_e468ada2.put("key82010", var_1b4a9cad.toString()); // put in a collection
        String var_b2bafa3d = (String) var_e468ada2.get("key82010"); // get it back out
        String var_951e2ba4 = var_b2bafa3d.substring(0, var_b2bafa3d.length() - 1); // extract most of it
        String var_1048221a =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_951e2ba4.getBytes()))); // B64 encode and decode it
        String var_b5feb059 = var_1048221a.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_37b51d19 = var_8afc1e9b.doSomething(var_b5feb059); // reflection

        return var_37b51d19;
    }
}
