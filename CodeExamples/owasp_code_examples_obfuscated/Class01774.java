
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-03/Class01774")
public class Class01774 extends HttpServlet {

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
        String param_eca07335 = var_456727ac.getTheValue("Class01774");

        String var_37b51d19 = new Class_0cbc6611().doSomething(param_10573b87, param_eca07335);

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        param_d1fc8eaf.getWriter().write(var_37b51d19);
    } // end doPost

    private class Class_0cbc6611 {

        public String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
                throws ServletException, IOException {

            String var_37b51d19 = "safe!";
            java.util.HashMap<String, Object> var_a3f3f709 = new java.util.HashMap<String, Object>();
            var_a3f3f709.put("keyA-58318", "a_Value"); // put some stuff in the collection
            var_a3f3f709.put("keyB-58318", param_eca07335); // put it in a collection
            var_a3f3f709.put("keyC", "another_Value"); // put some stuff in the collection
            var_37b51d19 = (String) var_a3f3f709.get("keyB-58318"); // get it back out
            var_37b51d19 = (String) var_a3f3f709.get("keyA-58318"); // get safe value back out

            return var_37b51d19;
        }
    } // end innerclass Class_0cbc6611
} // end DataflowThruInnerClass
