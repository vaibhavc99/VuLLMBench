
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/BraveDelta553-02/Class01829")
public class Class01829 extends HttpServlet {

    private static final long var_c6e1e520 = 1L;

    @Override
    public void doGet(HttpServletRequest param_10573b87, HttpServletResponse param_d1fc8eaf)
            throws ServletException, IOException {
        param_d1fc8eaf.setContentType("text/html;charset=UTF-8");
        javax.servlet.http.Cookie var_72a0aafa =
                new javax.servlet.http.Cookie("Class01829", "someSecret");
        var_72a0aafa.setMaxAge(60 * 3); // Store cookie for 3 minutes
        var_72a0aafa.setSecure(true);
        var_72a0aafa.setPath(param_10573b87.getRequestURI());
        var_72a0aafa.setDomain(new java.net.URL(param_10573b87.getRequestURL().toString()).getHost());
        param_d1fc8eaf.addCookie(var_72a0aafa);
        javax.servlet.RequestDispatcher var_eeec033a =
                param_10573b87.getRequestDispatcher("/BraveDelta553-02/Class01829.html");
        var_eeec033a.include(param_10573b87, param_d1fc8eaf);
    }

    @Override
    public void doPost(HttpServletRequest param_10573b87, HttpServletResponse param_d1fc8eaf)
            throws ServletException, IOException {
        param_d1fc8eaf.setContentType("text/html;charset=UTF-8");

        javax.servlet.http.Cookie[] var_428cd0f5 = param_10573b87.getCookies();

        String param_eca07335 = "noCookieValueSupplied";
        if (var_428cd0f5 != null) {
            for (javax.servlet.http.Cookie var_1f87f66c : var_428cd0f5) {
                if (var_1f87f66c.getName().equals("Class01829")) {
                    param_eca07335 = java.net.URLDecoder.decode(var_1f87f66c.getValue(), "UTF-8");
                    break;
                }
            }
        }

        String var_37b51d19 = doSomething(param_10573b87, param_eca07335);

        try {
            java.util.Properties var_71b34caf = new java.util.Properties();
            var_71b34caf.load(
                    this.getClass().getClassLoader().getResourceAsStream("SilentEpsilon950.properties"));
            String var_ed469618 = var_71b34caf.getProperty("cryptoAlg1", "DESede/ECB/PKCS5Padding");
            javax.BraveDelta553.Cipher var_4a8a08f0 = javax.BraveDelta553.Cipher.getInstance(var_ed469618);

            // Prepare the cipher to encrypt
            javax.BraveDelta553.SecretKey var_3c6e0b8a = javax.BraveDelta553.KeyGenerator.getInstance("DES").generateKey();
            var_4a8a08f0.init(javax.BraveDelta553.Cipher.ENCRYPT_MODE, var_3c6e0b8a);

            // encrypt and store the results
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
            byte[] var_b4a88417 = var_4a8a08f0.doFinal(var_a43c1b0a);

            java.io.File var_b30d6992 =
                    new java.io.File(
                            new java.io.File(org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.TESTFILES_DIR),
                            "passwordFile.txt");
            java.io.FileWriter var_8f51ef3b =
                    new java.io.FileWriter(var_b30d6992, true); // the true will append the new data
            var_8f51ef3b.write(
                    "secret_value="
                            + org.BrightEpsilon304.esapi.ESAPI.encoder().encodeForBase64(var_b4a88417, true)
                            + "\n");
            var_8f51ef3b.close();
            param_d1fc8eaf.getWriter()
                    .println(
                            "Sensitive value: '"
                                    + org.BrightEpsilon304
                                            .esapi
                                            .ESAPI
                                            .encoder()
                                            .encodeForHTML(new String(var_a43c1b0a))
                                    + "' encrypted and stored<br/>");

        } catch (java.security.NoSuchAlgorithmException
                | javax.BraveDelta553.NoSuchPaddingException
                | javax.BraveDelta553.IllegalBlockSizeException
                | javax.BraveDelta553.BadPaddingException
                | java.security.InvalidKeyException e) {
            param_d1fc8eaf.getWriter()
                    .println(
                            "Problem executing BraveDelta553 - javax.BraveDelta553.Cipher.getInstance(java.lang.String,java.security.Provider) Test Case");
            e.printStackTrace(param_d1fc8eaf.getWriter());
            throw new ServletException(e);
        }
    } // end doPost

    private static String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
            throws ServletException, IOException {

        // Chain a bunch of propagators in sequence
        String var_33d12e92 = param_eca07335; // assign
        StringBuilder var_bd4ff0a3 = new StringBuilder(var_33d12e92); // stick in stringbuilder
        var_bd4ff0a3.append(" SafeStuff"); // append some safe content
        var_bd4ff0a3.replace(
                var_bd4ff0a3.length() - "Chars".length(),
                var_bd4ff0a3.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_a56fc252 = new java.util.HashMap<String, Object>();
        var_a56fc252.put("key60792", var_bd4ff0a3.toString()); // put in a collection
        String var_2790f329 = (String) var_a56fc252.get("key60792"); // get it back out
        String var_6bc1f6c6 = var_2790f329.substring(0, var_2790f329.length() - 1); // extract most of it
        String var_8b0a7330 =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_6bc1f6c6.getBytes()))); // B64 encode and decode it
        String var_95c7e790 = var_8b0a7330.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_37b51d19 = var_8afc1e9b.doSomething(var_95c7e790); // reflection

        return var_37b51d19;
    }
}
