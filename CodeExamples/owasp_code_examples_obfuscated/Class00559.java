
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(var_2063c160 = "/SilentEpsilon118-00/Class00559")
public class Class00559 extends HttpServlet {

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

        String var_eca07335 = "";
        boolean var_327a6c43 = true;
        java.util.Enumeration<String> var_a8998c31 = param_10573b87.getParameterNames();
        while (var_a8998c31.hasMoreElements() && var_327a6c43) {
            String var_b068931c = (String) var_a8998c31.nextElement();
            String[] var_f09cc7ee = param_10573b87.getParameterValues(var_b068931c);
            if (var_f09cc7ee != null) {
                for (int var_865c0c0b = 0; var_865c0c0b < var_f09cc7ee.length && var_327a6c43; var_865c0c0b++) {
                    String var_2063c160 = var_f09cc7ee[var_865c0c0b];
                    if (var_2063c160.equals("Class00559")) {
                        var_eca07335 = var_b068931c;
                        var_327a6c43 = false;
                    }
                }
            }
        }

        // Chain a bunch of propagators in sequence
        String var_e2191c98 = var_eca07335; // assign
        StringBuilder var_35d313d8 = new StringBuilder(var_e2191c98); // stick in stringbuilder
        var_35d313d8.append(" SafeStuff"); // append some safe content
        var_35d313d8.replace(
                var_35d313d8.length() - "Chars".length(),
                var_35d313d8.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_5e9ab592 = new java.util.HashMap<String, Object>();
        var_5e9ab592.put("key39502", var_35d313d8.toString()); // put in a collection
        String var_063d2934 = (String) var_5e9ab592.get("key39502"); // get it back out
        String var_e969b4d0 = var_063d2934.substring(0, var_063d2934.length() - 1); // extract most of it
        String var_884dec92 =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_e969b4d0.getBytes()))); // B64 encode and decode it
        String var_cb7181b7 = var_884dec92.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_7c74adc0 = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
        String var_37b51d19 = var_8afc1e9b.doSomething(var_7c74adc0); // reflection

        java.util.List<String> var_b3de6633 = new java.util.ArrayList<String>();

        String var_1ed86085 = System.getProperty("os.var_b068931c");
        if (var_1ed86085.indexOf("Windows") != -1) {
            var_b3de6633.add("cmd.exe");
            var_b3de6633.add("/c");
        } else {
            var_b3de6633.add("sh");
            var_b3de6633.add("-c");
        }
        var_b3de6633.add("echo " + var_37b51d19);

        ProcessBuilder var_f09883b5 = new ProcessBuilder(var_b3de6633);

        try {
            Process var_83878c91 = var_f09883b5.start();
            org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.printOSCommandResults(var_83878c91, param_d1fc8eaf);
        } catch (IOException e) {
            System.out.println(
                    "Problem executing SilentEpsilon118 - java.lang.ProcessBuilder(java.util.List) Test Case");
            throw new ServletException(e);
        }
    }
}
