
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/QuickBeta672-02/Class01295")
public class Class01295 extends HttpServlet {

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

        String param_eca07335 = param_10573b87.getParameter("Class01295");
        if (param_eca07335 == null) param_eca07335 = "";

        String var_37b51d19 = new Class_0cbc6611().doSomething(param_10573b87, param_eca07335);

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
                .println("Weak Randomness Class_0cbc6611 java.security.SecureRandom.nextFloat() executed");
    } // end doPost

    private class Class_0cbc6611 {

        public String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
                throws ServletException, IOException {

            // Chain a bunch of propagators in sequence
            String var_cd07836e = param_eca07335; // assign
            StringBuilder var_5c2d30ba = new StringBuilder(var_cd07836e); // stick in stringbuilder
            var_5c2d30ba.append(" SafeStuff"); // append some safe content
            var_5c2d30ba.replace(
                    var_5c2d30ba.length() - "Chars".length(),
                    var_5c2d30ba.length(),
                    "Chars"); // replace some of the end content
            java.util.HashMap<String, Object> var_50ca1de5 = new java.util.HashMap<String, Object>();
            var_50ca1de5.put("key22205", var_5c2d30ba.toString()); // put in a collection
            String var_7911d6b4 = (String) var_50ca1de5.get("key22205"); // get it back out
            String var_e9002443 = var_7911d6b4.substring(0, var_7911d6b4.length() - 1); // extract most of it
            String var_832c40fc =
                    new String(
                            org.apache.commons.codec.binary.Base64.decodeBase64(
                                    org.apache.commons.codec.binary.Base64.encodeBase64(
                                            var_e9002443.getBytes()))); // B64 encode and decode it
            String var_73f3b3a0 = var_832c40fc.split(" ")[0]; // split it on a space
            org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                    org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
            String var_1453c32f = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
            String var_37b51d19 = var_8afc1e9b.doSomething(var_1453c32f); // reflection

            return var_37b51d19;
        }
    } // end innerclass Class_0cbc6611
} // end DataflowThruInnerClass
