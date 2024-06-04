
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/SilentEpsilon118-02/Class02243")
public class Class02243 extends HttpServlet {

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
            String[] var_f09cc7ee = var_1d78dc8e.get("Class02243");
            if (var_f09cc7ee != null) param_eca07335 = var_f09cc7ee[0];
        }

        String var_37b51d19 = doSomething(param_10573b87, param_eca07335);

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

        ProcessBuilder var_f09883b5 = new ProcessBuilder(var_b3de6633);

        try {
            Process var_83878c91 = var_f09883b5.start();
            org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.printOSCommandResults(var_83878c91, param_d1fc8eaf);
        } catch (IOException e) {
            System.out.println(
                    "Problem executing SilentEpsilon118 - java.lang.ProcessBuilder(java.util.List) Test Case");
            throw new ServletException(e);
        }
    } // end doPost

    private static String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
            throws ServletException, IOException {

        String var_37b51d19 = "safe!";
        java.util.HashMap<String, Object> var_e6a516b1 = new java.util.HashMap<String, Object>();
        var_e6a516b1.put("keyA-19941", "a-Value"); // put some stuff in the collection
        var_e6a516b1.put("keyB-19941", param_eca07335); // put it in a collection
        var_e6a516b1.put("keyC", "another-Value"); // put some stuff in the collection
        var_37b51d19 = (String) var_e6a516b1.get("keyB-19941"); // get it back out

        return var_37b51d19;
    }
}
