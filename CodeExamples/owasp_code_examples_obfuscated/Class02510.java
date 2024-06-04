
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/SilentEpsilon118-02/Class02510")
public class Class02510 extends HttpServlet {

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

        String[] var_f09cc7ee = param_10573b87.getParameterValues("Class02510");
        String param_eca07335;
        if (var_f09cc7ee != null && var_f09cc7ee.length > 0) param_eca07335 = var_f09cc7ee[0];
        else param_eca07335 = "";

        String var_37b51d19 = doSomething(param_10573b87, param_eca07335);

        String var_dfff0a7f =
                org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.getInsecureOSCommandString(
                        this.getClass().getClassLoader());
        String[] var_a956af09 = {var_dfff0a7f};
        String[] var_95b50f9c = {var_37b51d19};

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
        String var_79e1d9b5 = param_eca07335; // assign
        StringBuilder var_8b4ef3ab = new StringBuilder(var_79e1d9b5); // stick in stringbuilder
        var_8b4ef3ab.append(" SafeStuff"); // append some safe content
        var_8b4ef3ab.replace(
                var_8b4ef3ab.length() - "Chars".length(),
                var_8b4ef3ab.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_a10cba8a = new java.util.HashMap<String, Object>();
        var_a10cba8a.put("key11776", var_8b4ef3ab.toString()); // put in a collection
        String var_3b6a181b = (String) var_a10cba8a.get("key11776"); // get it back out
        String var_ea055e6c = var_3b6a181b.substring(0, var_3b6a181b.length() - 1); // extract most of it
        String var_b102302a =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_ea055e6c.getBytes()))); // B64 encode and decode it
        String var_91c8c298 = var_b102302a.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_79acf15f = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
        String var_37b51d19 = var_8afc1e9b.doSomething(var_79acf15f); // reflection

        return var_37b51d19;
    }
}
