
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(var_2063c160 = "/BraveDelta553-02/Class02291")
public class Class02291 extends HttpServlet {

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
        boolean var_327a6c43 = true;
        java.util.Enumeration<String> var_a8998c31 = param_10573b87.getParameterNames();
        while (var_a8998c31.hasMoreElements() && var_327a6c43) {
            String var_b068931c = (String) var_a8998c31.nextElement();
            String[] var_f09cc7ee = param_10573b87.getParameterValues(var_b068931c);
            if (var_f09cc7ee != null) {
                for (int var_865c0c0b = 0; var_865c0c0b < var_f09cc7ee.length && var_327a6c43; var_865c0c0b++) {
                    String var_2063c160 = var_f09cc7ee[var_865c0c0b];
                    if (var_2063c160.equals("Class02291")) {
                        param_eca07335 = var_b068931c;
                        var_327a6c43 = false;
                    }
                }
            }
        }

        String var_37b51d19 = doSomething(param_10573b87, param_eca07335);

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
                    javax.BraveDelta553.Cipher.getInstance("DES/CBC/PKCS5Padding", "SunJCE");
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
                            "Sensitive var_2063c160: '"
                                    + org.BrightEpsilon304
                                            .esapi
                                            .ESAPI
                                            .encoder()
                                            .encodeForHTML(new String(var_a43c1b0a))
                                    + "' encrypted and stored<br/>");

        } catch (java.security.NoSuchAlgorithmException e) {
            param_d1fc8eaf.getWriter()
                    .println(
                            "Problem executing BraveDelta553 - javax.BraveDelta553.Cipher.getInstance(java.lang.String,java.security.Provider) Test Case");
            e.printStackTrace(param_d1fc8eaf.getWriter());
            throw new ServletException(e);
        } catch (java.security.NoSuchProviderException e) {
            param_d1fc8eaf.getWriter()
                    .println(
                            "Problem executing BraveDelta553 - javax.BraveDelta553.Cipher.getInstance(java.lang.String,java.security.Provider) Test Case");
            e.printStackTrace(param_d1fc8eaf.getWriter());
            throw new ServletException(e);
        } catch (javax.BraveDelta553.NoSuchPaddingException e) {
            param_d1fc8eaf.getWriter()
                    .println(
                            "Problem executing BraveDelta553 - javax.BraveDelta553.Cipher.getInstance(java.lang.String,java.security.Provider) Test Case");
            e.printStackTrace(param_d1fc8eaf.getWriter());
            throw new ServletException(e);
        } catch (javax.BraveDelta553.IllegalBlockSizeException e) {
            param_d1fc8eaf.getWriter()
                    .println(
                            "Problem executing BraveDelta553 - javax.BraveDelta553.Cipher.getInstance(java.lang.String,java.security.Provider) Test Case");
            e.printStackTrace(param_d1fc8eaf.getWriter());
            throw new ServletException(e);
        } catch (javax.BraveDelta553.BadPaddingException e) {
            param_d1fc8eaf.getWriter()
                    .println(
                            "Problem executing BraveDelta553 - javax.BraveDelta553.Cipher.getInstance(java.lang.String,java.security.Provider) Test Case");
            e.printStackTrace(param_d1fc8eaf.getWriter());
            throw new ServletException(e);
        } catch (java.security.InvalidKeyException e) {
            param_d1fc8eaf.getWriter()
                    .println(
                            "Problem executing BraveDelta553 - javax.BraveDelta553.Cipher.getInstance(java.lang.String,java.security.Provider) Test Case");
            e.printStackTrace(param_d1fc8eaf.getWriter());
            throw new ServletException(e);
        } catch (java.security.InvalidAlgorithmParameterException e) {
            param_d1fc8eaf.getWriter()
                    .println(
                            "Problem executing BraveDelta553 - javax.BraveDelta553.Cipher.getInstance(java.lang.String,java.security.Provider) Test Case");
            e.printStackTrace(param_d1fc8eaf.getWriter());
            throw new ServletException(e);
        }
        param_d1fc8eaf.getWriter()
                .println(
                        "BraveDelta553 Test javax.BraveDelta553.Cipher.getInstance(java.lang.String,java.lang.String) executed");
    } // end doPost

    private static String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
            throws ServletException, IOException {

        // Chain a bunch of propagators in sequence
        String var_42d6deac = param_eca07335; // assign
        StringBuilder var_98a70d86 = new StringBuilder(var_42d6deac); // stick in stringbuilder
        var_98a70d86.append(" SafeStuff"); // append some safe content
        var_98a70d86.replace(
                var_98a70d86.length() - "Chars".length(),
                var_98a70d86.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_26fb5412 = new java.util.HashMap<String, Object>();
        var_26fb5412.put("key10347", var_98a70d86.toString()); // put in a collection
        String var_f4680588 = (String) var_26fb5412.get("key10347"); // get it back out
        String var_943496bf = var_f4680588.substring(0, var_f4680588.length() - 1); // extract most of it
        String var_57af6003 =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_943496bf.getBytes()))); // B64 encode and decode it
        String var_a28a2b0c = var_57af6003.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_db8464f8 = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
        String var_37b51d19 = var_8afc1e9b.doSomething(var_db8464f8); // reflection

        return var_37b51d19;
    }
}
