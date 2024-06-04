
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverBeta661-01/Class01159")
public class Class01159 extends HttpServlet {

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
        java.util.Enumeration<String> var_4340fd73 = param_10573b87.getHeaders("Class01159");

        if (var_4340fd73 != null && var_4340fd73.hasMoreElements()) {
            param_eca07335 = var_4340fd73.nextElement(); // just grab first element
        }

        // URL Decode the header value since req.getHeaders() doesn't. Unlike req.getParameters().
        param_eca07335 = java.net.URLDecoder.decode(param_eca07335, "UTF-8");

        String var_37b51d19 = new Class_0cbc6611().doSomething(param_10573b87, param_eca07335);

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

    private class Class_0cbc6611 {

        public String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
                throws ServletException, IOException {

            // Chain a bunch of propagators in sequence
            String var_7a87bfe3 = param_eca07335; // assign
            StringBuilder var_74f7acaa = new StringBuilder(var_7a87bfe3); // stick in stringbuilder
            var_74f7acaa.append(" SafeStuff"); // append some safe content
            var_74f7acaa.replace(
                    var_74f7acaa.length() - "Chars".length(),
                    var_74f7acaa.length(),
                    "Chars"); // replace some of the end content
            java.util.HashMap<String, Object> var_d5a04f1b = new java.util.HashMap<String, Object>();
            var_d5a04f1b.put("key54259", var_74f7acaa.toString()); // put in a collection
            String var_c829fa4f = (String) var_d5a04f1b.get("key54259"); // get it back out
            String var_fb671d4c = var_c829fa4f.substring(0, var_c829fa4f.length() - 1); // extract most of it
            String var_5a905da3 =
                    new String(
                            org.apache.commons.codec.binary.Base64.decodeBase64(
                                    org.apache.commons.codec.binary.Base64.encodeBase64(
                                            var_fb671d4c.getBytes()))); // B64 encode and decode it
            String var_980aa337 = var_5a905da3.split(" ")[0]; // split it on a space
            org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                    org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
            String var_f308c88f = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
            String var_37b51d19 = var_8afc1e9b.doSomething(var_f308c88f); // reflection

            return var_37b51d19;
        }
    } // end innerclass Class_0cbc6611
} // end DataflowThruInnerClass
