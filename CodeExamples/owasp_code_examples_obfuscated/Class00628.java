
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverBeta661-00/Class00628")
public class Class00628 extends HttpServlet {

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
        String var_eca07335 = var_456727ac.getTheParameter("Class00628");
        if (var_eca07335 == null) var_eca07335 = "";

        // Chain a bunch of propagators in sequence
        String var_5790127d = var_eca07335; // assign
        StringBuilder var_1e2cd65f = new StringBuilder(var_5790127d); // stick in stringbuilder
        var_1e2cd65f.append(" SafeStuff"); // append some safe content
        var_1e2cd65f.replace(
                var_1e2cd65f.length() - "Chars".length(),
                var_1e2cd65f.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_ef3b8ec0 = new java.util.HashMap<String, Object>();
        var_ef3b8ec0.put("key24315", var_1e2cd65f.toString()); // put in a collection
        String var_0923d9cb = (String) var_ef3b8ec0.get("key24315"); // get it back out
        String var_f04765da = var_0923d9cb.substring(0, var_0923d9cb.length() - 1); // extract most of it
        String var_b6e10407 =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_f04765da.getBytes()))); // B64 encode and decode it
        String var_f56bfe40 = var_b6e10407.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_2f47f083 = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
        String var_37b51d19 = var_8afc1e9b.doSomething(var_2f47f083); // reflection

        String var_5b063e27 = null;
        java.io.FileOutputStream var_e571b045 = null;

        try {
            var_5b063e27 = org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.TESTFILES_DIR + var_37b51d19;

            var_e571b045 = new java.io.FileOutputStream(var_5b063e27);
            param_d1fc8eaf.getWriter()
                    .println(
                            "Now ready to write to file: "
                                    + org.BrightEpsilon304.esapi.ESAPI.encoder().encodeForHTML(var_5b063e27));

        } catch (Exception e) {
            System.out.println("Couldn't open FileOutputStream on file: '" + var_5b063e27 + "'");
            //			System.out.println("File exception caught and swallowed: " + e.getMessage());
        } finally {
            if (var_e571b045 != null) {
                try {
                    var_e571b045.close();
                    var_e571b045 = null;
                } catch (Exception e) {
                    // we tried...
                }
            }
        }
    }
}
