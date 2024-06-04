
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-05/Class02587")
public class Class02587 extends HttpServlet {

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
        String var_fb72fe30 = "Class02587" + "=";
        int var_799f5a62 = -1;
        if (var_be571b25 != null) var_799f5a62 = var_be571b25.indexOf(var_fb72fe30);
        if (var_799f5a62 == -1) {
            param_d1fc8eaf.getWriter()
                    .println(
                            "getQueryString() couldn't find expected parameter '"
                                    + "Class02587"
                                    + "' in query string.");
            return;
        }

        String param_eca07335 =
                var_be571b25.substring(
                        var_799f5a62
                                + var_fb72fe30
                                        .length()); // 1st assume "Class02587" param_eca07335 is last
        // parameter in query string.
        // And then check to see if its in the middle of the query string and if so, trim off what
        // comes after.
        int var_a89f0a70 = var_be571b25.indexOf("&", var_799f5a62);
        if (var_a89f0a70 != -1) {
            param_eca07335 = var_be571b25.substring(var_799f5a62 + var_fb72fe30.length(), var_a89f0a70);
        }
        param_eca07335 = java.net.URLDecoder.decode(param_eca07335, "UTF-8");

        String var_37b51d19 = doSomething(param_10573b87, param_eca07335);

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        Object[] var_be8f8018 = {"a", var_37b51d19};
        param_d1fc8eaf.getWriter().printf(java.util.Locale.US, "Formatted like: %1$s and %2$s.", var_be8f8018);
    } // end doPost

    private static String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
            throws ServletException, IOException {

        // Chain a bunch of propagators in sequence
        String var_37bd3018 = param_eca07335; // assign
        StringBuilder var_3f2a0ccb = new StringBuilder(var_37bd3018); // stick in stringbuilder
        var_3f2a0ccb.append(" SafeStuff"); // append some safe content
        var_3f2a0ccb.replace(
                var_3f2a0ccb.length() - "Chars".length(),
                var_3f2a0ccb.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_aae6f2ff = new java.util.HashMap<String, Object>();
        var_aae6f2ff.put("key93081", var_3f2a0ccb.toString()); // put in a collection
        String var_9081bf04 = (String) var_aae6f2ff.get("key93081"); // get it back out
        String var_c22def76 = var_9081bf04.substring(0, var_9081bf04.length() - 1); // extract most of it
        String var_38b57461 =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_c22def76.getBytes()))); // B64 encode and decode it
        String var_abf6ef86 = var_38b57461.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_37b51d19 = var_8afc1e9b.doSomething(var_abf6ef86); // reflection

        return var_37b51d19;
    }
}
