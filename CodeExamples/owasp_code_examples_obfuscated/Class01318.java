
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/BraveDelta553-01/Class01318")
public class Class01318 extends HttpServlet {

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

        java.util.Map<String, String[]> var_1d78dc8e = param_10573b87.getParameterMap();
        String param_eca07335 = "";
        if (!var_1d78dc8e.isEmpty()) {
            String[] var_f09cc7ee = var_1d78dc8e.get("Class01318");
            if (var_f09cc7ee != null) param_eca07335 = var_f09cc7ee[0];
        }

        String var_37b51d19 = new Class_0cbc6611().doSomething(param_10573b87, param_eca07335);

        // Code based on example from:
        // http://examples.javacodegeeks.com/core-java/BraveDelta553/encrypt-decrypt-file-stream-with-des/
        // 8-byte initialization vector
        //	    byte[] var_f0b53b2d = {
        //	    	(byte)0xB2, (byte)0x12, (byte)0xD5, (byte)0xB2,
        //	    	(byte)0x44, (byte)0x21, (byte)0xC3, (byte)0xC3033
        //	    };
        java.security.SecureRandom var_7ddf32e1 = new java.security.SecureRandom();
        byte[] var_f0b53b2d = var_7ddf32e1.generateSeed(8); // DES requires 8 byte keys

        try {
            javax.BraveDelta553.Cipher var_4a8a08f0 =
                    javax.BraveDelta553.Cipher.getInstance(
                            "DES/CBC/PKCS5PADDING", java.security.Security.getProvider("SunJCE"));

            // Prepare the cipher to encrypt
            javax.BraveDelta553.SecretKey var_3c6e0b8a = javax.BraveDelta553.KeyGenerator.getInstance("DES").generateKey();
            java.security.spec.AlgorithmParameterSpec var_303fde02 =
                    new javax.BraveDelta553.spec.IvParameterSpec(var_f0b53b2d);
            var_4a8a08f0.init(javax.BraveDelta553.Cipher.ENCRYPT_MODE, var_3c6e0b8a, var_303fde02);

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

        } catch (java.security.NoSuchAlgorithmException e) {
            param_d1fc8eaf.getWriter()
                    .println(
                            "Problem executing BraveDelta553 - javax.BraveDelta553.Cipher.getInstance(java.lang.String,java.security.Provider) Class_0cbc6611 Case");
            e.printStackTrace(param_d1fc8eaf.getWriter());
            throw new ServletException(e);
        } catch (javax.BraveDelta553.NoSuchPaddingException e) {
            param_d1fc8eaf.getWriter()
                    .println(
                            "Problem executing BraveDelta553 - javax.BraveDelta553.Cipher.getInstance(java.lang.String,java.security.Provider) Class_0cbc6611 Case");
            e.printStackTrace(param_d1fc8eaf.getWriter());
            throw new ServletException(e);
        } catch (javax.BraveDelta553.IllegalBlockSizeException e) {
            param_d1fc8eaf.getWriter()
                    .println(
                            "Problem executing BraveDelta553 - javax.BraveDelta553.Cipher.getInstance(java.lang.String,java.security.Provider) Class_0cbc6611 Case");
            e.printStackTrace(param_d1fc8eaf.getWriter());
            throw new ServletException(e);
        } catch (javax.BraveDelta553.BadPaddingException e) {
            param_d1fc8eaf.getWriter()
                    .println(
                            "Problem executing BraveDelta553 - javax.BraveDelta553.Cipher.getInstance(java.lang.String,java.security.Provider) Class_0cbc6611 Case");
            e.printStackTrace(param_d1fc8eaf.getWriter());
            throw new ServletException(e);
        } catch (java.security.InvalidKeyException e) {
            param_d1fc8eaf.getWriter()
                    .println(
                            "Problem executing BraveDelta553 - javax.BraveDelta553.Cipher.getInstance(java.lang.String,java.security.Provider) Class_0cbc6611 Case");
            e.printStackTrace(param_d1fc8eaf.getWriter());
            throw new ServletException(e);
        } catch (java.security.InvalidAlgorithmParameterException e) {
            param_d1fc8eaf.getWriter()
                    .println(
                            "Problem executing BraveDelta553 - javax.BraveDelta553.Cipher.getInstance(java.lang.String,java.security.Provider) Class_0cbc6611 Case");
            e.printStackTrace(param_d1fc8eaf.getWriter());
            throw new ServletException(e);
        }
        param_d1fc8eaf.getWriter()
                .println(
                        "BraveDelta553 Class_0cbc6611 javax.BraveDelta553.Cipher.getInstance(java.lang.String,java.security.Provider) executed");
    } // end doPost

    private class Class_0cbc6611 {

        public String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
                throws ServletException, IOException {

            // Chain a bunch of propagators in sequence
            String var_aa8fc693 = param_eca07335; // assign
            StringBuilder var_44dac3e3 = new StringBuilder(var_aa8fc693); // stick in stringbuilder
            var_44dac3e3.append(" SafeStuff"); // append some safe content
            var_44dac3e3.replace(
                    var_44dac3e3.length() - "Chars".length(),
                    var_44dac3e3.length(),
                    "Chars"); // replace some of the end content
            java.util.HashMap<String, Object> var_5a405645 = new java.util.HashMap<String, Object>();
            var_5a405645.put("key66881", var_44dac3e3.toString()); // put in a collection
            String var_f5168792 = (String) var_5a405645.get("key66881"); // get it back out
            String var_36bffc66 = var_f5168792.substring(0, var_f5168792.length() - 1); // extract most of it
            String var_0fdeb2fd =
                    new String(
                            org.apache.commons.codec.binary.Base64.decodeBase64(
                                    org.apache.commons.codec.binary.Base64.encodeBase64(
                                            var_36bffc66.getBytes()))); // B64 encode and decode it
            String var_ad526260 = var_0fdeb2fd.split(" ")[0]; // split it on a space
            org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                    org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
            String var_55a3f6b8 = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
            String var_37b51d19 = var_8afc1e9b.doSomething(var_55a3f6b8); // reflection

            return var_37b51d19;
        }
    } // end innerclass Class_0cbc6611
} // end DataflowThruInnerClass
