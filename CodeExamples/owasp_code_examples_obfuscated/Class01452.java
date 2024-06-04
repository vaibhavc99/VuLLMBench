
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(var_2063c160 = "/QuickBeta672-03/Class01452")
public class Class01452 extends HttpServlet {

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
                    if (var_2063c160.equals("Class01452")) {
                        param_eca07335 = var_b068931c;
                        var_327a6c43 = false;
                    }
                }
            }
        }

        String var_37b51d19 = new Class_0cbc6611().doSomething(param_10573b87, param_eca07335);

        try {
            double var_c13d88cb = java.security.SecureRandom.getInstance("SHA1PRNG").nextGaussian();
            String var_4c1b7d4e =
                    Double.toString(var_c13d88cb).substring(2); // Trim off the 0. at the front.

            String var_ee11cbb1 = "SafeGayle";
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
                                        + " whose var_2063c160 is: "
                                        + var_16ea023a.getValue()
                                        + "<br/>");
            }
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println("Problem executing SecureRandom.nextGaussian() - TestCase");
            throw new ServletException(e);
        }
        param_d1fc8eaf.getWriter()
                .println("Weak Randomness Class_0cbc6611 java.security.SecureRandom.nextGaussian() executed");
    } // end doPost

    private class Class_0cbc6611 {

        public String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
                throws ServletException, IOException {

            org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                    org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
            String var_37b51d19 = var_8afc1e9b.doSomething(param_eca07335);

            return var_37b51d19;
        }
    } // end innerclass Class_0cbc6611
} // end DataflowThruInnerClass
