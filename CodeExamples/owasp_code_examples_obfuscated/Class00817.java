
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(var_2063c160 = "/QuickBeta672-01/Class00817")
public class Class00817 extends HttpServlet {

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

        String var_be571b25 = param_10573b87.getQueryString();
        String var_fb72fe30 = "Class00817" + "=";
        int var_799f5a62 = -1;
        if (var_be571b25 != null) var_799f5a62 = var_be571b25.indexOf(var_fb72fe30);
        if (var_799f5a62 == -1) {
            param_d1fc8eaf.getWriter()
                    .println(
                            "getQueryString() couldn't find expected parameter '"
                                    + "Class00817"
                                    + "' in query string.");
            return;
        }

        String var_eca07335 =
                var_be571b25.substring(
                        var_799f5a62
                                + var_fb72fe30
                                        .length()); // 1st assume "Class00817" var_eca07335 is last
        // parameter in query string.
        // And then check to see if its in the middle of the query string and if so, trim off what
        // comes after.
        int var_a89f0a70 = var_be571b25.indexOf("&", var_799f5a62);
        if (var_a89f0a70 != -1) {
            var_eca07335 = var_be571b25.substring(var_799f5a62 + var_fb72fe30.length(), var_a89f0a70);
        }
        var_eca07335 = java.net.URLDecoder.decode(var_eca07335, "UTF-8");

        // Chain a bunch of propagators in sequence
        String var_924e3610 = var_eca07335; // assign
        StringBuilder var_ef89d507 = new StringBuilder(var_924e3610); // stick in stringbuilder
        var_ef89d507.append(" SafeStuff"); // append some safe content
        var_ef89d507.replace(
                var_ef89d507.length() - "Chars".length(),
                var_ef89d507.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_cb4c5bf5 = new java.util.HashMap<String, Object>();
        var_cb4c5bf5.put("key9795", var_ef89d507.toString()); // put in a collection
        String var_ef630d14 = (String) var_cb4c5bf5.get("key9795"); // get it back out
        String var_367e2c57 = var_ef630d14.substring(0, var_ef630d14.length() - 1); // extract most of it
        String var_02c14b98 =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_367e2c57.getBytes()))); // B64 encode and decode it
        String var_2390f9df = var_02c14b98.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_37b51d19 = var_8afc1e9b.doSomething(var_2390f9df); // reflection

        double var_2063c160 = new java.util.Random().nextDouble();
        String var_4c1b7d4e = Double.toString(var_2063c160).substring(2); // Trim off the 0. at the front.

        String var_ee11cbb1 = "Donna";
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

        param_d1fc8eaf.getWriter().println("Weak Randomness Test java.util.Random.nextDouble() executed");
    }
}
