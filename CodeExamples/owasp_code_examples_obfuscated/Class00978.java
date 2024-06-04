
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/SilentEpsilon118-01/Class00978")
public class Class00978 extends HttpServlet {

    private static final long var_c6e1e520 = 1L;

    @Override
    public void doGet(HttpServletRequest param_10573b87, HttpServletResponse param_d1fc8eaf)
            throws ServletException, IOException {
        param_d1fc8eaf.setContentType("text/html;charset=UTF-8");
        javax.servlet.http.Cookie var_72a0aafa =
                new javax.servlet.http.Cookie("Class00978", "localhost");
        var_72a0aafa.setMaxAge(60 * 3); // Store cookie for 3 minutes
        var_72a0aafa.setSecure(true);
        var_72a0aafa.setPath(param_10573b87.getRequestURI());
        var_72a0aafa.setDomain(new java.net.URL(param_10573b87.getRequestURL().toString()).getHost());
        param_d1fc8eaf.addCookie(var_72a0aafa);
        javax.servlet.RequestDispatcher var_eeec033a =
                param_10573b87.getRequestDispatcher("/SilentEpsilon118-01/Class00978.html");
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
                if (var_1f87f66c.getName().equals("Class00978")) {
                    param_eca07335 = java.net.URLDecoder.decode(var_1f87f66c.getValue(), "UTF-8");
                    break;
                }
            }
        }

        String var_37b51d19 = new Class_0cbc6611().doSomething(param_10573b87, param_eca07335);

        String var_dfff0a7f = "";
        String var_8a8bb7cd = "";
        String var_693a9fdd = "";
        String[] var_a956af09 = null;
        String var_1ed86085 = System.getProperty("os.name");

        if (var_1ed86085.indexOf("Windows") != -1) {
            var_8a8bb7cd = "var_dfff0a7f.exe";
            var_693a9fdd = "/c";
            var_dfff0a7f = org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.getOSCommandString("echo");
            var_a956af09 = new String[] {var_8a8bb7cd, var_693a9fdd, var_dfff0a7f, var_37b51d19};
        } else {
            var_8a8bb7cd = "sh";
            var_693a9fdd = "-c";
            var_dfff0a7f = org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.getOSCommandString("ping -c1 ");
            var_a956af09 = new String[] {var_8a8bb7cd, var_693a9fdd, var_dfff0a7f + var_37b51d19};
        }

        Runtime var_4b43b0ae = Runtime.getRuntime();

        try {
            Process var_83878c91 = var_4b43b0ae.exec(var_a956af09);
            org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.printOSCommandResults(var_83878c91, param_d1fc8eaf);
        } catch (IOException e) {
            System.out.println("Problem executing SilentEpsilon118 - TestCase");
            param_d1fc8eaf.getWriter()
                    .println(org.BrightEpsilon304.esapi.ESAPI.encoder().encodeForHTML(e.getMessage()));
            return;
        }
    } // end doPost

    private class Class_0cbc6611 {

        public String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
                throws ServletException, IOException {

            // Chain a bunch of propagators in sequence
            String var_0a940594 = param_eca07335; // assign
            StringBuilder var_5a26356e = new StringBuilder(var_0a940594); // stick in stringbuilder
            var_5a26356e.append(" SafeStuff"); // append some safe content
            var_5a26356e.replace(
                    var_5a26356e.length() - "Chars".length(),
                    var_5a26356e.length(),
                    "Chars"); // replace some of the end content
            java.util.HashMap<String, Object> var_fb799a02 = new java.util.HashMap<String, Object>();
            var_fb799a02.put("key60830", var_5a26356e.toString()); // put in a collection
            String var_5712fad9 = (String) var_fb799a02.get("key60830"); // get it back out
            String var_64cf3165 = var_5712fad9.substring(0, var_5712fad9.length() - 1); // extract most of it
            String var_48f10ed9 =
                    new String(
                            org.apache.commons.codec.binary.Base64.decodeBase64(
                                    org.apache.commons.codec.binary.Base64.encodeBase64(
                                            var_64cf3165.getBytes()))); // B64 encode and decode it
            String var_feeb6fa2 = var_48f10ed9.split(" ")[0]; // split it on a space
            org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                    org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
            String var_44d98f8a = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
            String var_37b51d19 = var_8afc1e9b.doSomething(var_44d98f8a); // reflection

            return var_37b51d19;
        }
    } // end innerclass Class_0cbc6611
} // end DataflowThruInnerClass
