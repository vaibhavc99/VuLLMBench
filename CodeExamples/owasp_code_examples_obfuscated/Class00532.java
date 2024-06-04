
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(var_2063c160 = "/BrightAlpha455-00/Class00532")
public class Class00532 extends HttpServlet {

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
                    if (var_2063c160.equals("Class00532")) {
                        var_eca07335 = var_b068931c;
                        var_327a6c43 = false;
                    }
                }
            }
        }

        // Chain a bunch of propagators in sequence
        String var_e0320dc3 = var_eca07335; // assign
        StringBuilder var_04f1ee91 = new StringBuilder(var_e0320dc3); // stick in stringbuilder
        var_04f1ee91.append(" SafeStuff"); // append some safe content
        var_04f1ee91.replace(
                var_04f1ee91.length() - "Chars".length(),
                var_04f1ee91.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_9a51ac98 = new java.util.HashMap<String, Object>();
        var_9a51ac98.put("key25409", var_04f1ee91.toString()); // put in a collection
        String var_dc7bcb75 = (String) var_9a51ac98.get("key25409"); // get it back out
        String var_92b1f452 = var_dc7bcb75.substring(0, var_dc7bcb75.length() - 1); // extract most of it
        String var_dc7b4d48 =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_92b1f452.getBytes()))); // B64 encode and decode it
        String var_052acfee = var_dc7b4d48.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_37b51d19 = var_8afc1e9b.doSomething(var_052acfee); // reflection

        try {
            java.security.MessageDigest var_793914c9 = java.security.MessageDigest.getInstance("SHA1", "SUN");
            byte[] var_a43c1b0a = {(byte) '?'};
            Object var_c0b514e4 = var_37b51d19;
            if (var_c0b514e4 instanceof String) var_a43c1b0a = ((String) var_c0b514e4).getBytes();
            if (var_c0b514e4 instanceof java.io.InputStream) {
                byte[] var_437dfbfd = new byte[1000];
                int var_865c0c0b = ((java.io.InputStream) var_c0b514e4).read(var_437dfbfd);
                if (var_865c0c0b == -1) {
                    param_d1fc8eaf.getWriter()
                            .println(
                                    "This var_a43c1b0a source requires a POST, not a GET. Incompatible UI for the InputStream source.");
                    return;
                }
                var_a43c1b0a = java.util.Arrays.copyOf(var_437dfbfd, var_865c0c0b);
            }
            var_793914c9.update(var_a43c1b0a);

            byte[] var_b4a88417 = var_793914c9.digest();
            java.io.File var_b30d6992 =
                    new java.io.File(
                            new java.io.File(org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.TESTFILES_DIR),
                            "passwordFile.txt");
            java.io.FileWriter var_8f51ef3b =
                    new java.io.FileWriter(var_b30d6992, true); // the true will append the new data
            var_8f51ef3b.write(
                    "hash_value="
                            + org.BrightEpsilon304.esapi.ESAPI.encoder().encodeForBase64(var_b4a88417, true)
                            + "\n");
            var_8f51ef3b.close();
            param_d1fc8eaf.getWriter()
                    .println(
                            "Sensitive var_2063c160 '"
                                    + org.BrightEpsilon304
                                            .esapi
                                            .ESAPI
                                            .encoder()
                                            .encodeForHTML(new String(var_a43c1b0a))
                                    + "' hashed and stored<br/>");

        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(
                    "Problem executing BrightAlpha455 - TestCase java.security.MessageDigest.getInstance(java.lang.String,java.lang.String)");
            throw new ServletException(e);
        } catch (java.security.NoSuchProviderException e) {
            System.out.println(
                    "Problem executing BrightAlpha455 - TestCase java.security.MessageDigest.getInstance(java.lang.String,java.lang.String)");
            throw new ServletException(e);
        }

        param_d1fc8eaf.getWriter()
                .println(
                        "BrightAlpha455 Test java.security.MessageDigest.getInstance(java.lang.String,java.lang.String) executed");
    }
}
