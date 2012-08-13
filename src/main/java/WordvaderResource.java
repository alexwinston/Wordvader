import java.io.InputStream;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import wordvader.Solution;
import wordvader.Wordvader;

import com.google.gson.GsonBuilder;
import com.sun.jersey.multipart.FormDataParam;


@Path("/solve")
public class WordvaderResource {
	@POST @Consumes("multipart/form-data") @Produces("text/plain")
	public Response solve(@FormDataParam("udid") String udid, @FormDataParam("image") InputStream image) {
		Wordvader solver = new Wordvader();
		
		Solution solution = new Solution();
		try {
			solution = solver.solve(image);
		} catch (Exception e) {
			e.printStackTrace();
//			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		
		System.out.println(new Date().toString() + " /solve/" + udid);
		return Response.ok().entity(
				new GsonBuilder().create().toJson(solution)).build();
	}
}
