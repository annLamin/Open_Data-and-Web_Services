package it.univaq.odws.rest.covid;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
//Country data 
@Path("/countrieseconomicdata")

//country budget
//@Path("/countriesbudget")

//Country Case Report
//@Path("/covidcasereport")

//Covideconomicimpact Report
//@Path("/covideconomicimpact")
//
////Covid Health Measures
//@Path("/healthmeasures")

public interface Covid {
	
	//	Country Economic Data
//	@GET
//	@Path("/{id}")
//	@Produces({MediaType.APPLICATION_JSON})
//	String getCovidById (@PathParam("id")int id);

	//Country Budget	
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
	@Path("/gdppercent/{gdppercent}")
	@Produces({MediaType.APPLICATION_JSON})
	String getCovidByGDPpercent (@PathParam("gdppercent")String gdppercent);
	
	@GET
	@Path("/year/{year}")
	@Produces({MediaType.APPLICATION_JSON})
	String getCovidByYear (@PathParam("year")int year);
	
	@GET
	@Path("/indexprice/{indexprice}")
	@Produces({MediaType.APPLICATION_JSON})
	String getCovidByindexprice (@PathParam("indexprice")double indexprice);
	
	@GET
	@Path("/inflationrate/{inflationrate}")
	@Produces({MediaType.APPLICATION_JSON}  )
	String getCovidByinflationrate(@PathParam("inflationrate")double inflationrate);	
			
	@GET
	@Path("/covidbycountry/{country}")
	@Produces({MediaType.APPLICATION_JSON}  )
	String getCovidCaseByCountry(@PathParam("country")String country);	
	



	
}