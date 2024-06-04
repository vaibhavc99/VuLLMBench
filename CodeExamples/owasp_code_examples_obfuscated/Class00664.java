
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/QuickBeta672-01/Class00664")
public class Class00664 extends HttpServlet {

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
        String var_eca07335 = var_456727ac.getTheParameter("Class00664");
        if (var_eca07335 == null) var_eca07335 = "";

        // Chain a bunch of propagators in sequence
        String var_9fb4212f = var_eca07335; // assign
        StringBuilder var_215af262 = new StringBuilder(var_9fb4212f); // stick in stringbuilder
        var_215af262.append(" SafeStuff"); // append some safe content
        var_215af262.replace(
                var_215af262.length() - "Chars".length(),
                var_215af262.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_1613559f = new java.util.HashMap<String, Object>();
        var_1613559f.put("key87833", var_215af262.toString()); // put in a collection
        String var_dd1ceec2 = (String) var_1613559f.get("key87833"); // get it back out
        String var_7a89e4a2 = var_dd1ceec2.substring(0, var_dd1ceec2.length() - 1); // extract most of it
        String var_eb1cd7b4 =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_7a89e4a2.getBytes()))); // B64 encode and decode it
        String var_22ec25ce = var_eb1cd7b4.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_674d3e36 = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
        String var_37b51d19 = var_8afc1e9b.doSomething(var_674d3e36); // reflection

        try {
            float var_34d1c350 = java.security.SecureRandom.getInstance("SHA1PRNG").nextFloat();
            String var_4c1b7d4e =
                    Float.toString(var_34d1c350).substring(2); // Trim off the 0. at the front.

            String var_ee11cbb1 = "SafeFloyd";
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
                        if (var_2dccd1ab.getValue()
                                .equals(param_10573b87.getSession().getAttribute(var_94ba3607))) {
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
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println("Problem executing SecureRandom.nextFloat() - TestCase");
            throw new ServletException(e);
        }
        param_d1fc8eaf.getWriter()
                .println("Weak Randomness Test java.security.SecureRandom.nextFloat() executed");
    }
}
