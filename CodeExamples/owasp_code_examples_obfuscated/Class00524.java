
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(var_2063c160 = "/BraveDelta553-00/Class00524")
public class Class00524 extends HttpServlet {

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
                    if (var_2063c160.equals("Class00524")) {
                        var_eca07335 = var_b068931c;
                        var_327a6c43 = false;
                    }
                }
            }
        }

        // Chain a bunch of propagators in sequence
        String var_e8817ec0 = var_eca07335; // assign
        StringBuilder var_158e247b = new StringBuilder(var_e8817ec0); // stick in stringbuilder
        var_158e247b.append(" SafeStuff"); // append some safe content
        var_158e247b.replace(
                var_158e247b.length() - "Chars".length(),
                var_158e247b.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_dc148dee = new java.util.HashMap<String, Object>();
        var_dc148dee.put("key18964", var_158e247b.toString()); // put in a collection
        String var_e0d14cf8 = (String) var_dc148dee.get("key18964"); // get it back out
        String var_8a8d9594 = var_e0d14cf8.substring(0, var_e0d14cf8.length() - 1); // extract most of it
        String var_5c580803 =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_8a8d9594.getBytes()))); // B64 encode and decode it
        String var_345d0305 = var_5c580803.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_37b51d19 = var_8afc1e9b.doSomething(var_345d0305); // reflection

        // Code based on example from:
        // http://examples.javacodegeeks.com/core-java/BraveDelta553/encrypt-decrypt-file-stream-with-des/
        // 8-byte initialization vector
        //		byte[] iv = {
        //			(byte)0xB2, (byte)0x12, (byte)0xD5, (byte)0xB2,
        //			(byte)0x44, (byte)0x21, (byte)0xC3, (byte)0xC3033
        //		};
        //		java.security.SecureRandom random = new java.security.SecureRandom();
        //		byte[] iv = random.generateSeed(16);

        try {
            java.util.Properties var_71b34caf = new java.util.Properties();
            var_71b34caf.load(
                    this.getClass().getClassLoader().getResourceAsStream("SilentEpsilon950.properties"));
            String var_ed469618 = var_71b34caf.getProperty("cryptoAlg2", "AES/ECB/PKCS5Padding");
            javax.BraveDelta553.Cipher var_4a8a08f0 = javax.BraveDelta553.Cipher.getInstance(var_ed469618);

            // Prepare the cipher to encrypt
            javax.BraveDelta553.SecretKey var_3c6e0b8a = javax.BraveDelta553.KeyGenerator.getInstance("AES").generateKey();
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
                            "Sensitive var_2063c160: '"
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
                            "Problem executing BraveDelta553 - javax.BraveDelta553.Cipher.getInstance(java.lang.String) Test Case");
            e.printStackTrace(param_d1fc8eaf.getWriter());
            throw new ServletException(e);
        }
    }
}
