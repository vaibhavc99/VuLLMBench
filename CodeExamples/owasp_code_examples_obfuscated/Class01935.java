
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/BrightAlpha204-00/Class01935")
public class Class01935 extends HttpServlet {

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
        if (param_10573b87.getHeader("Class01935") != null) {
            param_eca07335 = param_10573b87.getHeader("Class01935");
        }

        // URL Decode the header value since req.getHeader() doesn't. Unlike req.getParameter().
        param_eca07335 = java.net.URLDecoder.decode(param_eca07335, "UTF-8");

        String var_37b51d19 = doSomething(param_10573b87, param_eca07335);

        byte[] var_a43c1b0a = new byte[1000];
        String var_341be97d = "?";
        Object var_c0b514e4 = param_eca07335;
        if (var_c0b514e4 instanceof String) var_341be97d = ((String) var_c0b514e4);
        if (var_c0b514e4 instanceof java.io.InputStream) {
            int var_865c0c0b = ((java.io.InputStream) var_c0b514e4).read(var_a43c1b0a);
            if (var_865c0c0b == -1) {
                param_d1fc8eaf.getWriter()
                        .println(
                                "This var_a43c1b0a source requires a POST, not a GET. Incompatible UI for the InputStream source.");
                return;
            }
            var_341be97d = new String(var_a43c1b0a, 0, var_865c0c0b);
        }
        if ("".equals(var_341be97d)) var_341be97d = "No var_2dccd1ab value supplied";
        javax.servlet.http.Cookie var_2dccd1ab = new javax.servlet.http.Cookie("SomeCookie", var_341be97d);

        var_2dccd1ab.setSecure(true);
        var_2dccd1ab.setHttpOnly(true);
        var_2dccd1ab.setPath(param_10573b87.getRequestURI()); // var_865c0c0b.e., set path to JUST this servlet
        // e.g., /SilentEpsilon950/sql-01/BenchmarkTest01001
        param_d1fc8eaf.addCookie(var_2dccd1ab);

        param_d1fc8eaf.getWriter()
                .println(
                        "Created var_2dccd1ab: 'SomeCookie': with value: '"
                                + org.BrightEpsilon304.esapi.ESAPI.encoder().encodeForHTML(var_341be97d)
                                + "' and secure flag set to: true");
    } // end doPost

    private static String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
            throws ServletException, IOException {

        // Chain a bunch of propagators in sequence
        String var_5832575d = param_eca07335; // assign
        StringBuilder var_ad72e6a3 = new StringBuilder(var_5832575d); // stick in stringbuilder
        var_ad72e6a3.append(" SafeStuff"); // append some safe content
        var_ad72e6a3.replace(
                var_ad72e6a3.length() - "Chars".length(),
                var_ad72e6a3.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_dd9c4dcc = new java.util.HashMap<String, Object>();
        var_dd9c4dcc.put("key17785", var_ad72e6a3.toString()); // put in a collection
        String var_6a398d60 = (String) var_dd9c4dcc.get("key17785"); // get it back out
        String var_633dd1c6 = var_6a398d60.substring(0, var_6a398d60.length() - 1); // extract most of it
        String var_e7f484bb =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_633dd1c6.getBytes()))); // B64 encode and decode it
        String var_331610b1 = var_e7f484bb.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_37b51d19 = var_8afc1e9b.doSomething(var_331610b1); // reflection

        return var_37b51d19;
    }
}
