
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/BrightEpsilon574-00/Class02457")
public class Class02457 extends HttpServlet {

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
        String param_eca07335 = var_456727ac.getTheParameter("Class02457");
        if (param_eca07335 == null) param_eca07335 = "";

        String var_37b51d19 = doSomething(param_10573b87, param_eca07335);

        try {
            java.io.FileInputStream var_8c7dd922 =
                    new java.io.FileInputStream(
                            org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.getFileFromClasspath(
                                    "employees.xml", this.getClass().getClassLoader()));
            javax.xml.parsers.DocumentBuilderFactory var_608b7009 =
                    javax.xml.parsers.DocumentBuilderFactory.newInstance();
            // Prevent XXE
            var_608b7009.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            javax.xml.parsers.DocumentBuilder var_c87a8ca6 = var_608b7009.newDocumentBuilder();
            org.w3c.dom.Document var_0959255e = var_c87a8ca6.parse(var_8c7dd922);
            javax.xml.xpath.XPathFactory var_e6d7203b = javax.xml.xpath.XPathFactory.newInstance();
            javax.xml.xpath.XPath var_f68da865 = var_e6d7203b.newXPath();

            String var_63973cd3 = "/Employees/Employee[@emplid='" + var_37b51d19 + "']";
            String var_b4a88417 = var_f68da865.evaluate(var_63973cd3, var_0959255e);

            param_d1fc8eaf.getWriter().println("Your query results are: " + var_b4a88417 + "<br/>");

        } catch (javax.xml.xpath.XPathExpressionException
                | javax.xml.parsers.ParserConfigurationException
                | org.xml.sax.SAXException e) {
            param_d1fc8eaf.getWriter()
                    .println(
                            "Error parsing XPath input: '"
                                    + org.BrightEpsilon304.esapi.ESAPI.encoder().encodeForHTML(var_37b51d19)
                                    + "'");
            throw new ServletException(e);
        }
    } // end doPost

    private static String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
            throws ServletException, IOException {

        String var_37b51d19 = "alsosafe";
        if (param_eca07335 != null) {
            java.util.List<String> var_c95d91f5 = new java.util.ArrayList<String>();
            var_c95d91f5.add("safe");
            var_c95d91f5.add(param_eca07335);
            var_c95d91f5.add("moresafe");

            var_c95d91f5.remove(0); // remove the 1st safe value

            var_37b51d19 = var_c95d91f5.get(1); // get the last 'safe' value
        }

        return var_37b51d19;
    }
}
