/**
 * 
 */
package frontEnd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gatherer.*;

/**
 * @author Zero
 *
 */
public class WepApp extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8980604275816486757L;

	/**
	 * 
	 */
	public WepApp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String[] command = new String[2];
		ArrayList<String> shoplist;
		shoplist = (ArrayList<String>) req.getSession().getAttribute("cart");
		if (shoplist == null) {
			shoplist = new ArrayList<String>();
		}
		String fac = "";
		fac = req.getParameter("factuur");
		// String emptycart = "";
		// fac = req.getParameter("empty");

		String[] s = req
				.getParameterValues(gatherer.constants.Constants.PRODUCT_TYPE_STATE);
		command[0] = gatherer.constants.Constants.PRODUCT_OF_A_TYPE_STATE;
		if (s == null) {
			s = req.getParameterValues(gatherer.constants.Constants.PRODUCT_OF_A_TYPE_STATE);
			command[0] = gatherer.constants.Constants.PRODUCT_ATTRIBUTES_STATE;
			if (s != null) {
				for (String paramvalue : s) {
					shoplist.add(paramvalue);
					req.getSession(true).setAttribute("cart", shoplist);
				}

			}
			if (s == null) {
				s = req.getParameterValues(gatherer.constants.Constants.PRODUCT_ATTRIBUTES_STATE);
				command[0] = gatherer.constants.Constants.PRODUCT_TYPE_STATE;

				req.getSession().getId();

			}
		}
		PrintWriter out = resp.getWriter();

		// query product types
		for (String paramvalue : s) {
			// out.println("<h1>" + fac + req.getSession().getId() + paramvalue
			// + req.getSession().getAttribute("cart") + "</h1>");

			ArrayList<String> gOutput = new ArrayList<String>();
			Get nosql = new Get();
			command[1] = paramvalue;
			gOutput = nosql.start(command);
			printPreamble(command[0], resp.getWriter());
			for (String g : gOutput) {

				if (gOutput.indexOf(g) == 0) {
					out.println("<option selected>" + g + "</option>");

				}
				out.println("<option >" + g + "</option>");

			}

			printPostForm(resp.getWriter());
			/*
			 * if (emptycart == "empty") { shoplist = new ArrayList<String>() ;
			 * req.getSession(true).setAttribute("cart", shoplist);
			 * 
			 * }
			 */

			if (fac != null) {
				float TotalPrice = 0;

				out.println("<table>");
				for (int i = 0; i < shoplist.size(); i++) {
					command[0] = gatherer.constants.Constants.PRODUCT_PRICE_STATE;
					command[1] = shoplist.get(i);
					ArrayList<String> pOutput = new ArrayList<String>();
					pOutput = nosql.start(command);

					command[0] = gatherer.constants.Constants.PRODUCT_LINK_STATE;
					command[1] = shoplist.get(i);
					;
					ArrayList<String> lOutput = new ArrayList<String>();
					lOutput = nosql.start(command);
					for (String p : pOutput) {
						TotalPrice = TotalPrice + Float.parseFloat(p);
						for (String l : lOutput) {
							out.println("<tr><td><b>Product : </b>"
									+ shoplist.get(i)
									+ "</td><td><b>Price :</b> "
									+ Float.parseFloat(p)
									+ "</td><td><a href=\"" + l
									+ "\">LINK</a></td></tr>");

						}
					}

				}
				out.println("<tr><td></td><td><b>TotalPrice : </b></td><td>"
						+ TotalPrice + "</td></tr>");
				out.println("</table>");

			}

			printPostamble(resp.getWriter());

		}
		// save product in shopping cart over een sessie

		// display shopping cart

		// display product info

		// super.doPost(req, resp);
	}

	private void printPreamble(String selectId, PrintWriter out) {
		out.println("<html><head> <meta http-equiv=\"Content-Type\" content=\"text/html;charset=ANSI\"> </head><body><img src=\"/pj56-rep/generic-logo.jpg\"> <form method=\"POST\" action=\"dowat\">");
		out.println("<select name=" + selectId + " id=" + selectId
				+ "  size=\"10\" style=\"width: 400px;\">");
	}

	private void printPostamble(PrintWriter out) {

		out.println("</body></html>");
	}

	private void printPostForm(PrintWriter out) {
		out.println("</select><button type=\"Submit\">post</button>");
		out.println("</select><button name=\"factuur\" value=\"factuur\" type=\"Submit\">factuur</button></form>");
		// out.println("</select><button name=\"empty\" type=\"Submit\">empty shopping cart</button></form>");

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String[] s = new String[2];
		s[0] = gatherer.constants.Constants.PRODUCT_TYPE_STATE;

		try {

			ArrayList<String> gOutput = new ArrayList<String>();
			Get get = new Get();
			gOutput = get.start(s);

			printPreamble(s[0], resp.getWriter());

			for (int i = 0; i < gOutput.size(); i++) {
				resp.getWriter().println(
						"<option>" + gOutput.get(i) + "</option>");
			}

			printPostForm(resp.getWriter());
			printPostamble(resp.getWriter());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		resp.getWriter().println("Tessting java code");
	}
}
