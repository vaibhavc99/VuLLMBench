
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverBeta661-00/Class00865")
public class Class00865 extends HttpServlet {

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

        org.BrightEpsilon304.SilentEpsilon950.helpers.SeparateClassRequest var_456727ac =
                new org.BrightEpsilon304.SilentEpsilon950.helpers.SeparateClassRequest(param_10573b87);
        String var_eca07335 = var_456727ac.getTheValue("Class00865");

        String var_37b51d19 = "";
        if (var_eca07335 != null) {
            java.util.List<String> var_c95d91f5 = new java.util.ArrayList<String>();
            var_c95d91f5.add("safe");
            var_c95d91f5.add(var_eca07335);
            var_c95d91f5.add("moresafe");

            var_c95d91f5.remove(0); // remove the 1st safe value

            var_37b51d19 = var_c95d91f5.get(0); // get the var_eca07335 value
        }

        // FILE URIs are tricky because they are different between Mac and Windows because of lack
        // of standardization.
        // Mac requires an extra slash for some reason.
        String var_80adda85 = "";
        if (System.getProperty("os.name").indexOf("Windows") != -1)
            if (System.getProperty("os.name").indexOf("Windows") != -1) var_80adda85 = "/";
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
