package de.azubi.spiele.rest.basic;

import java.io.File;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.Encoded;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("basic")
public class KommunikationsRestService {

	@GET
	@Path("ping")
	@Produces("text/plain")
	public String pingPlain(@QueryParam("s") String txt) {
		txt = txt == null ? "NULL" : txt;
		return txt.toUpperCase();
	}
}
