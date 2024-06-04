
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/SilentGamma375-00/Class00189")
public class Class00189 extends HttpServlet {

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
        if (param_10573b87.getHeader("Class00189") != null) {
            var_eca07335 = param_10573b87.getHeader("Class00189");
        }

        // URL Decode the header value since req.getHeader() doesn't. Unlike req.getParameter().
        var_eca07335 = java.net.URLDecoder.decode(var_eca07335, "UTF-8");

        // Chain a bunch of propagators in sequence
        String var_8f0eed68 = var_eca07335; // assign
        StringBuilder var_ea0c8b99 = new StringBuilder(var_8f0eed68); // stick in stringbuilder
        var_ea0c8b99.append(" SafeStuff"); // append some safe content
        var_ea0c8b99.replace(
                var_ea0c8b99.length() - "Chars".length(),
                var_ea0c8b99.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_9ba23b36 = new java.util.HashMap<String, Object>();
        var_9ba23b36.put("key14330", var_ea0c8b99.toString()); // put in a collection
        String var_fafc7159 = (String) var_9ba23b36.get("key14330"); // get it back out
        String var_210148aa = var_fafc7159.substring(0, var_fafc7159.length() - 1); // extract most of it
        String var_12aadd24 =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_210148aa.getBytes()))); // B64 encode and decode it
        String var_26ab6077 = var_12aadd24.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_1b8a4a2f = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
        String var_37b51d19 = var_8afc1e9b.doSomething(var_1b8a4a2f); // reflection

        // javax.servlet.http.HttpSession.setAttribute(java.lang.String,java.lang.Object^)
        param_10573b87.getSession().setAttribute("userid", var_37b51d19);

        param_d1fc8eaf.getWriter()
                .println(
                        "Item: 'userid' with value: '"
                                + org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.encodeForHTML(var_37b51d19)
                                + "' saved in session.");
    }
}
