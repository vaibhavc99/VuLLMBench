
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/SilentEpsilon118-00/Class00480")
public class Class00480 extends HttpServlet {

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
        String var_eca07335 = "";
        if (!var_1d78dc8e.isEmpty()) {
            String[] var_f09cc7ee = var_1d78dc8e.get("Class00480");
            if (var_f09cc7ee != null) var_eca07335 = var_f09cc7ee[0];
        }

        String var_37b51d19;

        // Simple ? condition that assigns var_eca07335 to var_37b51d19 on false condition
        int var_0fc3cfbc = 106;

        var_37b51d19 = (7 * 42) - var_0fc3cfbc > 200 ? "This should never happen" : var_eca07335;

        java.util.List<String> var_b3de6633 = new java.util.ArrayList<String>();

        String var_1ed86085 = System.getProperty("os.name");
        if (var_1ed86085.indexOf("Windows") != -1) {
            var_b3de6633.add("cmd.exe");
            var_b3de6633.add("/c");
        } else {
            var_b3de6633.add("sh");
            var_b3de6633.add("-c");
        }
        var_b3de6633.add("echo " + var_37b51d19);

        ProcessBuilder var_f09883b5 = new ProcessBuilder();

        var_f09883b5.command(var_b3de6633);

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
