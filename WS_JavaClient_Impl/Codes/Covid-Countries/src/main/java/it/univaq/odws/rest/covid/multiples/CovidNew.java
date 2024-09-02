package it.univaq.odws.rest.covid.multiples;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/covidnew-info")
public interface CovidNew {
	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	String getCovidById (@PathParam("id")int id);
	
	@GET
	@Path("/country/{country}")
	@Produces({MediaType.APPLICATION_JSON})
	String getCovidByCountry (@PathParam("country")String country);
	
	@GET
	@Path("/stockindex/{stockindex}")
	@Produces({MediaType.APPLICATION_JSON})
	String getCovidByStockIndex (@PathParam("stockindex")String stockindex);
	
	@GET
	@Path("/covidbycountry/{country}")
	@Produces({MediaType.APPLICATION_JSON}  )
	String getCovidCaseByCountry(@PathParam("country")String country);	
	
	@GET
	@Path("/countryCode/{countryCode}")
	@Produces({MediaType.APPLICATION_JSON}  )
	String getCovidCaseByCountryCode(@PathParam("countryCode")String countryCode);	
	
	
}