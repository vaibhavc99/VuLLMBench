
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/SilentEpsilon118-02/Class02148")
public class Class02148 extends HttpServlet {

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

        String param_eca07335 = param_10573b87.getParameter("Class02148");
        if (param_eca07335 == null) param_eca07335 = "";

        String var_37b51d19 = doSomething(param_10573b87, param_eca07335);

        String var_dfff0a7f = "";
        String var_8a8bb7cd = "";
        String var_693a9fdd = "";
        String[] var_a956af09 = null;
        String var_1ed86085 = System.getProperty("os.name");

        if (var_1ed86085.indexOf("Windows") != -1) {
            var_8a8bb7cd = "var_dfff0a7f.exe";
            var_693a9fdd = "/c";
            var_dfff0a7f = "echo ";
            var_a956af09 = new String[] {var_8a8bb7cd, var_693a9fdd, var_dfff0a7f, var_37b51d19};
        } else {
            var_8a8bb7cd = "sh";
            var_693a9fdd = "-c";
            var_dfff0a7f = org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.getOSCommandString("ls ");
            var_a956af09 = new String[] {var_8a8bb7cd, var_693a9fdd, var_dfff0a7f + var_37b51d19};
        }

        String[] var_95b50f9c = {"foo=var_37b51d19"};

        Runtime var_4b43b0ae = Runtime.getRuntime();

        try {
            Process var_83878c91 = var_4b43b0ae.exec(var_a956af09, var_95b50f9c);
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
        String var_07631779 = param_eca07335; // assign
        StringBuilder var_3d8c7d63 = new StringBuilder(var_07631779); // stick in stringbuilder
        var_3d8c7d63.append(" SafeStuff"); // append some safe content
        var_3d8c7d63.replace(
                var_3d8c7d63.length() - "Chars".length(),
                var_3d8c7d63.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_816dcff6 = new java.util.HashMap<String, Object>();
        var_816dcff6.put("key17988", var_3d8c7d63.toString()); // put in a collection
        String var_8c0bd2cd = (String) var_816dcff6.get("key17988"); // get it back out
        String var_85e39a80 = var_8c0bd2cd.substring(0, var_8c0bd2cd.length() - 1); // extract most of it
        String var_4c408591 =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_85e39a80.getBytes()))); // B64 encode and decode it
        String var_45bee04a = var_4c408591.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_1ccaf4fe = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
        String var_37b51d19 = var_8afc1e9b.doSomething(var_1ccaf4fe); // reflection

        return var_37b51d19;
    }
}
