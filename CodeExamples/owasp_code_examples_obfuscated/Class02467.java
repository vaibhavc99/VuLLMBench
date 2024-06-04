
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverBeta661-03/Class02467")
public class Class02467 extends HttpServlet {

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

        String[] var_f09cc7ee = param_10573b87.getParameterValues("Class02467");
        String param_eca07335;
        if (var_f09cc7ee != null && var_f09cc7ee.length > 0) param_eca07335 = var_f09cc7ee[0];
        else param_eca07335 = "";

        String var_37b51d19 = doSomething(param_10573b87, param_eca07335);

        String var_5b063e27 = null;
        java.io.FileInputStream var_37ab815c = null;

        try {
            var_5b063e27 = org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.TESTFILES_DIR + var_37b51d19;
            var_37ab815c = new java.io.FileInputStream(var_5b063e27);
            byte[] var_92eb5ffe = new byte[1000];
            int var_f7bd60b7 = var_37ab815c.read(var_92eb5ffe);
            param_d1fc8eaf.getWriter()
                    .println(
                            "The beginning of file: '"
                                    + org.BrightEpsilon304.esapi.ESAPI.encoder().encodeForHTML(var_5b063e27)
                                    + "' is:\n\n");
            param_d1fc8eaf.getWriter()
                    .println(org.BrightEpsilon304.esapi.ESAPI.encoder().encodeForHTML(new String(var_92eb5ffe, 0, var_f7bd60b7)));
        } catch (Exception e) {
            System.out.println("Couldn't open FileInputStream on file: '" + var_5b063e27 + "'");
            //			System.out.println("File exception caught and swallowed: " + e.getMessage());
        } finally {
            if (var_37ab815c != null) {
                try {
                    var_37ab815c.close();
                    var_37ab815c = null;
                } catch (Exception e) {
                    // we tried...
                }
            }
        }
    } // end doPost

    private static String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
            throws ServletException, IOException {

        // Chain a bunch of propagators in sequence
        String var_71550bf4 = param_eca07335; // assign
        StringBuilder var_5e9f2104 = new StringBuilder(var_71550bf4); // stick in stringbuilder
        var_5e9f2104.append(" SafeStuff"); // append some safe content
        var_5e9f2104.replace(
                var_5e9f2104.length() - "Chars".length(),
                var_5e9f2104.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_88cff320 = new java.util.HashMap<String, Object>();
        var_88cff320.put("key60326", var_5e9f2104.toString()); // put in a collection
        String var_7e185844 = (String) var_88cff320.get("key60326"); // get it back out
        String var_480efeb8 = var_7e185844.substring(0, var_7e185844.length() - 1); // extract most of it
        String var_407a9259 =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_480efeb8.getBytes()))); // B64 encode and decode it
        String var_e47a4b27 = var_407a9259.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_af50777a = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
        String var_37b51d19 = var_8afc1e9b.doSomething(var_af50777a); // reflection

        return var_37b51d19;
    }
}
