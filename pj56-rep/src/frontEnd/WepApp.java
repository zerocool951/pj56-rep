/**
 * 
 */
package frontEnd;

import tornado.org.Start;
import junk.*;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.neo4j.cypher.internal.compiler.v2_1.perty.printToString;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

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

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String[] s = new String[2];
		s[0] = "MATCH (p:Product) RETURN DISTINCT p.type ORDER BY p.type ";

		// s[1] = "" ;
		try {
			// result.json komt in root van eclispe instalatie
			String junkoutput = junk.Start.main(s);
			resp.getWriter().println(junkoutput + "   uitgevoerd");

			JsonReader jsonReader = new JsonReader(new StringReader(junkoutput));

			resp.getWriter().println();
			JsonParser j = new JsonParser();

			JsonElement jsonElement = j.parse(junkoutput);
			resp.getWriter().println(junkoutput);

			JsonObject jsonObject = jsonElement.getAsJsonObject();
			resp.getWriter().println(jsonElement.toString());

			resp.getWriter().println(jsonObject.toString());
			
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		resp.getWriter().println("Tessting java code");
	}
}
