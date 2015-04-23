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
		// TODO Auto-generated method stub
		String[] command = new String[2];

		String[] s = req.getParameterValues("producttypes");
		command[0] = "productsofatype";
		if (s == null) {
			s = req.getParameterValues("productsofatype");
			command[0] = "productattributes";
			if (s == null) {
				s = req.getParameterValues("productattributes");
				command[0] = "producttypes";
				// optie add shoppingcart session 
				req.getSession().getId();
					
			}
		}
		PrintWriter out = resp.getWriter();

		// query product types
		for (String paramvalue : s) {
			out.println("<h1>" + paramvalue + "</h1>");

			ArrayList<String> gOutput = new ArrayList<String>();
			Get nosql = new Get();
			command[1] = paramvalue;
			gOutput = nosql.start(command);
			printPreamble(command[0], resp.getWriter());
			for (String g : gOutput) {

				out.println("<option>" + g + "</option>");

			}
			printPostamble(resp.getWriter());

		}
		// save product in shopping cart over een sessie

		// display shopping cart

		// display product info

		// super.doPost(req, resp);
	}

	private void printPreamble(String selectId, PrintWriter out) {
		out.println("<html><head</head><body><form method=\"POST\" action=\"dowat\">");
		out.println("<select name="+selectId+" id=" + selectId
				+ "  size=\"10\" style=\"width: 400px;\">");
	}

	private void printPostamble(PrintWriter out) {
		out.println("</select><button type=\"Submit\">post</button>");
		out.println("</form></body></html>");
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String[] s = new String[2];
		s[0] = "producttypes";

		try {

			ArrayList<String> gOutput = new ArrayList<String>();
			Get get = new Get();
			gOutput = get.start(s);

			printPreamble(s[0], resp.getWriter());

			for (int i = 0; i < gOutput.size(); i++) {
				resp.getWriter().println(
						"<option>" + gOutput.get(i) + "</option>");
			}

			printPostamble(resp.getWriter());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		resp.getWriter().println("Tessting java code");
	}
}
