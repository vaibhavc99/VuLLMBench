
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/SilentGamma375-00/Class00427")
public class Class00427 extends HttpServlet {

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

        String var_eca07335 = param_10573b87.getParameter("Class00427");
        if (var_eca07335 == null) var_eca07335 = "";

        // Chain a bunch of propagators in sequence
        String var_d115f98d = var_eca07335; // assign
        StringBuilder var_62e640fa = new StringBuilder(var_d115f98d); // stick in stringbuilder
        var_62e640fa.append(" SafeStuff"); // append some safe content
        var_62e640fa.replace(
                var_62e640fa.length() - "Chars".length(),
                var_62e640fa.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_c6cd38b3 = new java.util.HashMap<String, Object>();
        var_c6cd38b3.put("key70670", var_62e640fa.toString()); // put in a collection
        String var_e48817d6 = (String) var_c6cd38b3.get("key70670"); // get it back out
        String var_2fb25e82 = var_e48817d6.substring(0, var_e48817d6.length() - 1); // extract most of it
        String var_d339dae6 =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_2fb25e82.getBytes()))); // B64 encode and decode it
        String var_13ee83a4 = var_d339dae6.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_37b51d19 = var_8afc1e9b.doSomething(var_13ee83a4); // reflection

        // javax.servlet.http.HttpSession.setAttribute(java.lang.String,java.lang.Object^)
        param_10573b87.getSession().setAttribute("userid", var_37b51d19);

        param_d1fc8eaf.getWriter()
                .println(
                        "Item: 'userid' with value: '"
                                + org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.encodeForHTML(var_37b51d19)
                                + "' saved in session.");
    }
}
