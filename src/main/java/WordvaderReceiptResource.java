import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.sun.jersey.multipart.FormDataParam;

@Path("/receipt")
public class WordvaderReceiptResource {
	@POST @Consumes("application/x-www-form-urlencoded") @Produces("text/plain")
	public Response receipt(@FormParam("udid") String udid, @FormParam("receipt") String receipt) {
		System.out.println(new Date().toString() + " /receipt/" + udid + "/" + receipt);
		return Response.ok().build();
	}
}
