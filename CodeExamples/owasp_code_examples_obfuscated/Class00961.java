
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/BrightAlpha455-01/Class00961")
public class Class00961 extends HttpServlet {

    private static final long var_c6e1e520 = 1L;

    @Override
    public void doGet(HttpServletRequest param_10573b87, HttpServletResponse param_d1fc8eaf)
            throws ServletException, IOException {
        param_d1fc8eaf.setContentType("text/html;charset=UTF-8");
        javax.servlet.http.Cookie var_72a0aafa =
                new javax.servlet.http.Cookie("Class00961", "someSecret");
        var_72a0aafa.setMaxAge(60 * 3); // Store cookie for 3 minutes
        var_72a0aafa.setSecure(true);
        var_72a0aafa.setPath(param_10573b87.getRequestURI());
        var_72a0aafa.setDomain(new java.net.URL(param_10573b87.getRequestURL().toString()).getHost());
        param_d1fc8eaf.addCookie(var_72a0aafa);
        javax.servlet.RequestDispatcher var_eeec033a =
                param_10573b87.getRequestDispatcher("/BrightAlpha455-01/Class00961.html");
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
                if (var_1f87f66c.getName().equals("Class00961")) {
                    param_eca07335 = java.net.URLDecoder.decode(var_1f87f66c.getValue(), "UTF-8");
                    break;
                }
            }
        }

        String var_37b51d19 = new Class_0cbc6611().doSomething(param_10573b87, param_eca07335);

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
                            "Sensitive value '"
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
                        "BrightAlpha455 Class_0cbc6611 java.security.MessageDigest.getInstance(java.lang.String,java.lang.String) executed");
    } // end doPost

    private class Class_0cbc6611 {

        public String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
                throws ServletException, IOException {

            String var_37b51d19 = "safe!";
            java.util.HashMap<String, Object> var_8e34e553 = new java.util.HashMap<String, Object>();
            var_8e34e553.put("keyA-47722", "a_Value"); // put some stuff in the collection
            var_8e34e553.put("keyB-47722", param_eca07335); // put it in a collection
            var_8e34e553.put("keyC", "another_Value"); // put some stuff in the collection
            var_37b51d19 = (String) var_8e34e553.get("keyB-47722"); // get it back out
            var_37b51d19 = (String) var_8e34e553.get("keyA-47722"); // get safe value back out

            return var_37b51d19;
        }
    } // end innerclass Class_0cbc6611
} // end DataflowThruInnerClass
