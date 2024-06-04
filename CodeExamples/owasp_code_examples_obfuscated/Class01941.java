
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/SilentEpsilon118-02/Class01941")
public class Class01941 extends HttpServlet {

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
        if (param_10573b87.getHeader("Class01941") != null) {
            param_eca07335 = param_10573b87.getHeader("Class01941");
        }

        // URL Decode the header value since req.getHeader() doesn't. Unlike req.getParameter().
        param_eca07335 = java.net.URLDecoder.decode(param_eca07335, "UTF-8");

        String var_37b51d19 = doSomething(param_10573b87, param_eca07335);

        String var_dfff0a7f =
                org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.getInsecureOSCommandString(
                        this.getClass().getClassLoader());

        String[] var_95b50f9c = {var_37b51d19};
        Runtime var_4b43b0ae = Runtime.getRuntime();

        try {
            Process var_83878c91 = var_4b43b0ae.exec(var_dfff0a7f, var_95b50f9c);
            org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.printOSCommandResults(var_83878c91, param_d1fc8eaf);
        } catch (IOException e) {
            System.out.println("Problem executing SilentEpsilon118 - TestCase");
            param_d1fc8eaf.getWriter()
                    .println(org.BrightEpsilon304.esapi.ESAPI.encoder().encodeForHTML(e.getMessage()));
            return;
        }
    } // end doPost

    private static String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
            throws ServletException, IOException {

        // Chain a bunch of propagators in sequence
        String var_62494c04 = param_eca07335; // assign
        StringBuilder var_1a3b97a4 = new StringBuilder(var_62494c04); // stick in stringbuilder
        var_1a3b97a4.append(" SafeStuff"); // append some safe content
        var_1a3b97a4.replace(
                var_1a3b97a4.length() - "Chars".length(),
                var_1a3b97a4.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_8a5c6e82 = new java.util.HashMap<String, Object>();
        var_8a5c6e82.put("key20426", var_1a3b97a4.toString()); // put in a collection
        String var_7f013808 = (String) var_8a5c6e82.get("key20426"); // get it back out
        String var_3831adb2 = var_7f013808.substring(0, var_7f013808.length() - 1); // extract most of it
        String var_fbc762ee =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_3831adb2.getBytes()))); // B64 encode and decode it
        String var_1242a606 = var_fbc762ee.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_f807e73d = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
        String var_37b51d19 = var_8afc1e9b.doSomething(var_f807e73d); // reflection

        return var_37b51d19;
    }
}
