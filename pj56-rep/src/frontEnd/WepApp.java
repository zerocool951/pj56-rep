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
		String s = req.getParameter("productTypes");

		PrintWriter out = resp.getWriter();
		out.println("<h1>" + s + "</h1>");

		super.doPost(req, resp);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String[] s = new String[2];
		// s[0] = "MATCH (p:Product) RETURN DISTINCT p.type ORDER BY p.type ";

		s[0] = "producttypes";

		// s[1] = "" ;
		try {

			ArrayList<String> gOutput = new ArrayList<String>();

			Get get = new Get();
			gOutput = get.start(s);
			resp.getWriter().println("<html><head</head><body><form method=\"post\" action=\"dowat\">") ; 
			resp.getWriter().println("<select id=\"productTypes\" onChange=\"fillProducts()\" size=\"10\" style=\"width: 200px;\">");

			for (int i = 0; i < gOutput.size(); i++) {
				resp.getWriter().println(
						"<option type=\"submit\">" + gOutput.get(i) + "</option>");
			}

			resp.getWriter().println("</select>");
			resp.getWriter().println("</html></body></form>") ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		resp.getWriter().println("Tessting java code");
	}
}
