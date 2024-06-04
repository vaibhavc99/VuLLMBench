
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/BrightEpsilon574-00/Class01633")
public class Class01633 extends HttpServlet {

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

        String[] var_f09cc7ee = param_10573b87.getParameterValues("Class01633");
        String param_eca07335;
        if (var_f09cc7ee != null && var_f09cc7ee.length > 0) param_eca07335 = var_f09cc7ee[0];
        else param_eca07335 = "";

        String var_37b51d19 = new Class_0cbc6611().doSomething(param_10573b87, param_eca07335);

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

    private class Class_0cbc6611 {

        public String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
                throws ServletException, IOException {

            // Chain a bunch of propagators in sequence
            String var_9597b9e4 = param_eca07335; // assign
            StringBuilder var_34e3c67a = new StringBuilder(var_9597b9e4); // stick in stringbuilder
            var_34e3c67a.append(" SafeStuff"); // append some safe content
            var_34e3c67a.replace(
                    var_34e3c67a.length() - "Chars".length(),
                    var_34e3c67a.length(),
                    "Chars"); // replace some of the end content
            java.util.HashMap<String, Object> var_39686fc0 = new java.util.HashMap<String, Object>();
            var_39686fc0.put("key31144", var_34e3c67a.toString()); // put in a collection
            String var_e43ac3c6 = (String) var_39686fc0.get("key31144"); // get it back out
            String var_d4f4e88c = var_e43ac3c6.substring(0, var_e43ac3c6.length() - 1); // extract most of it
            String var_58d5b48f =
                    new String(
                            org.apache.commons.codec.binary.Base64.decodeBase64(
                                    org.apache.commons.codec.binary.Base64.encodeBase64(
                                            var_d4f4e88c.getBytes()))); // B64 encode and decode it
            String var_caca0a53 = var_58d5b48f.split(" ")[0]; // split it on a space
            org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                    org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
            String var_0d950ec8 = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
            String var_37b51d19 = var_8afc1e9b.doSomething(var_0d950ec8); // reflection

            return var_37b51d19;
        }
    } // end innerclass Class_0cbc6611
} // end DataflowThruInnerClass
