
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/QuickBeta672-02/Class00989")
public class Class00989 extends HttpServlet {

    private static final long var_c6e1e520 = 1L;

    @Override
    public void doGet(HttpServletRequest param_10573b87, HttpServletResponse param_d1fc8eaf)
            throws ServletException, IOException {
        param_d1fc8eaf.setContentType("text/html;charset=UTF-8");
        javax.servlet.http.Cookie var_72a0aafa =
                new javax.servlet.http.Cookie("Class00989", "whatever");
        var_72a0aafa.setMaxAge(60 * 3); // Store var_2dccd1ab for 3 minutes
        var_72a0aafa.setSecure(true);
        var_72a0aafa.setPath(param_10573b87.getRequestURI());
        var_72a0aafa.setDomain(new java.net.URL(param_10573b87.getRequestURL().toString()).getHost());
        param_d1fc8eaf.addCookie(var_72a0aafa);
        javax.servlet.RequestDispatcher var_eeec033a =
                param_10573b87.getRequestDispatcher("/QuickBeta672-02/Class00989.html");
        var_eeec033a.include(param_10573b87, param_d1fc8eaf);
    }

    @Override
    public void doPost(HttpServletRequest param_10573b87, HttpServletResponse param_d1fc8eaf)
            throws ServletException, IOException {
        param_d1fc8eaf.setContentType("text/html;charset=UTF-8");

        javax.servlet.http.Cookie[] var_428cd0f5 = param_10573b87.getCookies();

        String param_eca07335 = "noCookieValueSupplied";
        if (var_428cd0f5 != null) {
            for (javax.servlet.http.Cookie var_1f87f66c : var_428cd0f5) {
                if (var_1f87f66c.getName().equals("Class00989")) {
                    param_eca07335 = java.net.URLDecoder.decode(var_1f87f66c.getValue(), "UTF-8");
                    break;
                }
            }
        }

        String var_37b51d19 = new Class_0cbc6611().doSomething(param_10573b87, param_eca07335);

        try {
            int var_2f50a27e = java.security.SecureRandom.getInstance("SHA1PRNG").nextInt(99);
            String var_4c1b7d4e = Integer.toString(var_2f50a27e);

            String var_ee11cbb1 = "SafeInga";
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
            System.out.println("Problem executing SecureRandom.nextInt(int) - TestCase");
            throw new ServletException(e);
        }
        param_d1fc8eaf.getWriter()
                .println("Weak Randomness Class_0cbc6611 java.security.SecureRandom.nextInt(int) executed");
    } // end doPost

    private class Class_0cbc6611 {

        public String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
                throws ServletException, IOException {

            // Chain a bunch of propagators in sequence
            String var_19fe7144 = param_eca07335; // assign
            StringBuilder var_65cc6517 = new StringBuilder(var_19fe7144); // stick in stringbuilder
            var_65cc6517.append(" SafeStuff"); // append some safe content
            var_65cc6517.replace(
                    var_65cc6517.length() - "Chars".length(),
                    var_65cc6517.length(),
                    "Chars"); // replace some of the end content
            java.util.HashMap<String, Object> var_27bf5c18 = new java.util.HashMap<String, Object>();
            var_27bf5c18.put("key36538", var_65cc6517.toString()); // put in a collection
            String var_bda455d4 = (String) var_27bf5c18.get("key36538"); // get it back out
            String var_d8f7e221 = var_bda455d4.substring(0, var_bda455d4.length() - 1); // extract most of it
            String var_08bc9b17 =
                    new String(
                            org.apache.commons.codec.binary.Base64.decodeBase64(
                                    org.apache.commons.codec.binary.Base64.encodeBase64(
                                            var_d8f7e221.getBytes()))); // B64 encode and decode it
            String var_db014651 = var_08bc9b17.split(" ")[0]; // split it on a space
            org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                    org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
            String var_37b51d19 = var_8afc1e9b.doSomething(var_db014651); // reflection

            return var_37b51d19;
        }
    } // end innerclass Class_0cbc6611
} // end DataflowThruInnerClass
