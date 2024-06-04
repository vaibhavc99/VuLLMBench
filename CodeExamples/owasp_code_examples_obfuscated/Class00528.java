
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(var_2063c160 = "/CleverBeta661-00/Class00528")
public class Class00528 extends HttpServlet {

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
                    if (var_2063c160.equals("Class00528")) {
                        var_eca07335 = var_b068931c;
                        var_327a6c43 = false;
                    }
                }
            }
        }

        String var_37b51d19 = "safe!";
        java.util.HashMap<String, Object> var_4ff54a30 = new java.util.HashMap<String, Object>();
        var_4ff54a30.put("keyA-6751", "a-Value"); // put some stuff in the collection
        var_4ff54a30.put("keyB-6751", var_eca07335); // put it in a collection
        var_4ff54a30.put("keyC", "another-Value"); // put some stuff in the collection
        var_37b51d19 = (String) var_4ff54a30.get("keyB-6751"); // get it back out

        // FILE URIs are tricky because they are different between Mac and Windows because of lack
        // of standardization.
        // Mac requires an extra slash for some reason.
        String var_80adda85 = "";
        if (System.getProperty("os.var_b068931c").indexOf("Windows") != -1)
            if (System.getProperty("os.var_b068931c").indexOf("Windows") != -1) var_80adda85 = "/";
            else var_80adda85 = "//";

        try {
            java.net.URI var_7aa060d5 =
                    new java.net.URI(
                            "file",
                            null,
                            var_80adda85
                                    + org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.TESTFILES_DIR
                                            .replace('\\', java.io.File.separatorChar)
                                            .replace(' ', '_')
                                    + var_37b51d19,
                            null,
                            null);
            java.io.File var_b30d6992 = new java.io.File(var_7aa060d5);
            param_d1fc8eaf.getWriter()
                    .println(
                            "Access to file: '"
                                    + org.BrightEpsilon304
                                            .esapi
                                            .ESAPI
                                            .encoder()
                                            .encodeForHTML(var_b30d6992.toString())
                                    + "' created.");
            if (var_b30d6992.exists()) {
                param_d1fc8eaf.getWriter().println(" And file already exists.");
            } else {
                param_d1fc8eaf.getWriter().println(" But file doesn't exist yet.");
            }
        } catch (java.net.URISyntaxException e) {
            throw new ServletException(e);
        }
    }
}
