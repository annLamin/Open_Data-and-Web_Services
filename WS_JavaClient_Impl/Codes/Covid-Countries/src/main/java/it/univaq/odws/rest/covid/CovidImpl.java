package it.univaq.odws.rest.covid;
import java.io.ByteArrayOutputStream;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.riot.RDFDataMgr;

public class CovidImpl implements Covid{
	
	private static final String COVID_DATASET = "Economic-Data.rdf";
//	private static final String COVID_DATASET = "Countries-Budget.rdf";
//	private static final String COVID_DATASET = "COVID19-CaseReprt.rdf";
//	private static final String COVID_DATASET = "Covid19-Impact.rdf";
//	private static final String COVID_DATASET = "PublicHealthSafety.rdf";
	private Dataset loadDataset() {
		Dataset dataset = RDFDataMgr.loadDataset(COVID_DATASET);
		dataset.begin(ReadWrite.READ);
		return dataset;
	}
	
	private String convertResultSetToJSONString(ResultSet resultSet) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ResultSetFormatter.outputAsJSON(byteArrayOutputStream, resultSet);
		return byteArrayOutputStream.toString();
	}	
	

	@Override
	public String getCovidById(int id) {
		Dataset dataset = loadDataset();

		StringBuilder query = new StringBuilder();
		query.append("PREFIX dbp: <http://dbpedia.org/ontology/>").append(System.lineSeparator());
		query.append("PREFIX sch: <https://schema.org/>").append(System.lineSeparator());
		query.append("SELECT ?country  ?stockindex ?sameasstockindex ?gdppercent ?year ?indexprice ?inflationrate ?imdb_url ?wikidata_url").append(System.lineSeparator());
		query.append("WHERE {").append(System.lineSeparator());
		query.append("	<http://odws.univaq.it/countrieseconomicdata/"+id+">  dbp:country ?country.").append(System.lineSeparator());
		query.append("	<http://odws.univaq.it/countrieseconomicdata/"+id+">  dbp:stockindex ?stockindex.").append(System.lineSeparator());
		query.append("	<http://odws.univaq.it/countrieseconomicdata/"+id+">  dbp:sameasstockindex ?sameasstockindex.").append(System.lineSeparator());
	 	query.append("	<http://odws.univaq.it/countrieseconomicdata/"+id+"> dbp:gdppercent ?gdppercent.").append(System.lineSeparator());
		query.append("	<http://odws.univaq.it/countrieseconomicdata/"+id+"> dbp:year ?year.").append(System.lineSeparator());
		query.append("	<http://odws.univaq.it/countrieseconomicdata/"+id+"> dbp:indexprice ?indexprice.").append(System.lineSeparator());
		query.append("	<http://odws.univaq.it/countrieseconomicdata/"+id+"> dbp:inflationrate ?inflationrate").append(System.lineSeparator());
		query.append("}").append(System.lineSeparator());

		QueryExecution queryExecution = QueryExecutionFactory.create(query.toString(), dataset);
		ResultSet resultSet = queryExecution.execSelect();
		return convertResultSetToJSONString(resultSet);
	}
//		
	@Override
	public String getCovidByCountry(String country) {
		// TODO Auto-generated method stub
		Dataset dataset = loadDataset();

		StringBuilder query = new StringBuilder();
		query.append("PREFIX dbp: <http://dbpedia.org/ontology/>").append(System.lineSeparator());
		query.append("PREFIX sch: <https://schema.org/>").append(System.lineSeparator());
		query.append("SELECT ?country ?year ?gdppercent ?indexprice ?stockindex ?sameasstockindex ?inflationrate ").append(System.lineSeparator());
		query.append("WHERE {").append(System.lineSeparator());
		query.append("	?countrieseconomicdata dbp:country ?country.").append(System.lineSeparator());
		query.append("	?countrieseconomicdata dbp:stockindex ?stockindex.").append(System.lineSeparator());
		query.append("	?countrieseconomicdata dbp:sameasstockindex ?sameasstockindex.").append(System.lineSeparator());
		query.append("	?countrieseconomicdata dbp:year ?year.").append(System.lineSeparator());
		query.append("	?countrieseconomicdata dbp:gdppercent ?gdppercent.").append(System.lineSeparator());
		query.append("	?countrieseconomicdata dbp:indexprice ?indexprice.").append(System.lineSeparator());
		query.append("	?countrieseconomicdata dbp:inflationrate ?inflationrate.").append(System.lineSeparator());
		query.append("	FILTER((LCASE(?country) = \"" + country.toLowerCase() + "\"))").append(System.lineSeparator());
		query.append("}").append(System.lineSeparator());

		QueryExecution queryExecution = QueryExecutionFactory.create(query.toString(), dataset);
		ResultSet resultSet = queryExecution.execSelect();
		return convertResultSetToJSONString(resultSet);
	}

	@Override
	public String getCovidByGDPpercent(String gdppercent) {
		Dataset dataset = loadDataset();
		StringBuilder query = new StringBuilder();
		query.append("PREFIX dbp: <http://dbpedia.org/ontology/>").append(System.lineSeparator());
		query.append("PREFIX sch: <https://schema.org/>").append(System.lineSeparator());
		query.append("SELECT ?country ?year ?gdppercent ?indexprice ?stockindex ?sameasstockindex ?inflationrate ").append(System.lineSeparator());
		query.append("WHERE {").append(System.lineSeparator());
		query.append("	?countrieseconomicdata dbp:country ?country.").append(System.lineSeparator());
		query.append("	?countrieseconomicdata dbp:year ?year.").append(System.lineSeparator());
		query.append("	?countrieseconomicdata dbp:stockindex ?stockindex.").append(System.lineSeparator());
		query.append("	?countrieseconomicdata dbp:sameasstockindex ?sameasstockindex.").append(System.lineSeparator());
		query.append("	?countrieseconomicdata dbp:gdppercent ?gdppercent.").append(System.lineSeparator());
		query.append("	?countrieseconomicdata dbp:indexprice ?indexprice.").append(System.lineSeparator());
		query.append("	?countrieseconomicdata dbp:inflationrate ?inflationrate.").append(System.lineSeparator());
		query.append("	FILTER((LCASE(?gdppercent) = \"" + gdppercent.toLowerCase() + "\"))").append(System.lineSeparator());
		query.append("}").append(System.lineSeparator());

		QueryExecution queryExecution = QueryExecutionFactory.create(query.toString(), dataset);
		ResultSet resultSet = queryExecution.execSelect();
		return convertResultSetToJSONString(resultSet);
	}

	@Override
	public String getCovidByYear(int year) {
		StringBuilder query = new StringBuilder();
		Dataset dataset = loadDataset();
		query.append("PREFIX dbp: <http://dbpedia.org/ontology/>").append(System.lineSeparator());
		query.append("PREFIX sch: <https://schema.org/>").append(System.lineSeparator());
		query.append("SELECT ?country ?year ?gdppercent ?indexprice ?stockindex ?sameasstockindex ?inflationrate ").append(System.lineSeparator());
		query.append("WHERE {").append(System.lineSeparator());
		query.append("	?countrieseconomicdata dbp:country ?country.").append(System.lineSeparator());
		query.append("	?countrieseconomicdata dbp:year ?year.").append(System.lineSeparator());
		query.append("	?countrieseconomicdata dbp:stockindex ?stockindex.").append(System.lineSeparator());
		query.append("	?countrieseconomicdata dbp:sameasstockindex ?sameasstockindex.").append(System.lineSeparator());
		query.append("	?countrieseconomicdata dbp:gdppercent ?gdppercent.").append(System.lineSeparator());
		query.append("	?countrieseconomicdata dbp:indexprice ?indexprice.").append(System.lineSeparator());
		query.append("	?countrieseconomicdata dbp:inflationrate ?inflationrate.").append(System.lineSeparator());
		query.append("	FILTER((LCASE(?year) = \"" + "\"))").append(System.lineSeparator());
		query.append("}").append(System.lineSeparator());

		QueryExecution queryExecution = QueryExecutionFactory.create(query.toString(), dataset);
		ResultSet resultSet = queryExecution.execSelect();
		return convertResultSetToJSONString(resultSet);
	
	}

	@Override
	public String getCovidByindexprice(double indexprice) {
		StringBuilder query = new StringBuilder();
		Dataset dataset = loadDataset();
		query.append("PREFIX dbp: <http://dbpedia.org/ontology/>").append(System.lineSeparator());
		query.append("PREFIX sch: <https://schema.org/>").append(System.lineSeparator());
		query.append("SELECT ?country ?year ?gdppercent ?indexprice ?stockindex ?sameasstockindex ?inflationrate ").append(System.lineSeparator());
		query.append("WHERE {").append(System.lineSeparator());
		query.append("	?covid dbp:country ?country.").append(System.lineSeparator());
		query.append("	?covid dbp:year ?year.").append(System.lineSeparator());
		query.append("	?covid dbp:stockindex ?stockindex.").append(System.lineSeparator());
		query.append("	?covid dbp:sameasstockindex ?sameasstockindex.").append(System.lineSeparator());
		query.append("	?covid dbp:gdppercent ?gdppercent.").append(System.lineSeparator());
		query.append("	?covid dbp:indexprice ?indexprice.").append(System.lineSeparator());
		query.append("	?covid dbp:inflationrate ?inflationrate.").append(System.lineSeparator());
		query.append("	FILTER((LCASE(?indexprice) = \"" + "\"))").append(System.lineSeparator());
		query.append("}").append(System.lineSeparator());

		QueryExecution queryExecution = QueryExecutionFactory.create(query.toString(), dataset);
		ResultSet resultSet = queryExecution.execSelect();
		return convertResultSetToJSONString(resultSet);
	}

	@Override
	public String getCovidByinflationrate(double inflationrate) {
		StringBuilder query = new StringBuilder();
		Dataset dataset = loadDataset();
		query.append("PREFIX dbp: <http://dbpedia.org/ontology/>").append(System.lineSeparator());
		query.append("PREFIX sch: <https://schema.org/>").append(System.lineSeparator());
		query.append("SELECT ?country ?year ?gdppercent ?indexprice ?stockindex ?sameasstockindex ?inflationrate ").append(System.lineSeparator());
		query.append("WHERE {").append(System.lineSeparator());
		query.append("	?covid dbp:country ?country.").append(System.lineSeparator());
		query.append("	?covid dbp:year ?year.").append(System.lineSeparator());
		query.append("	?covid dbp:stockindex ?stockindex.").append(System.lineSeparator());
		query.append("	?covid dbp:sameasstockindex ?sameasstockindex.").append(System.lineSeparator());
		query.append("	?covid dbp:gdppercent ?gdppercent.").append(System.lineSeparator());
		query.append("	?covid dbp:indexprice ?indexprice.").append(System.lineSeparator());
		query.append("	?covid dbp:inflationrate ?inflationrate.").append(System.lineSeparator());
		query.append("	FILTER((LCASE(?inflationrate) = \"" + "\"))").append(System.lineSeparator());
		query.append("}").append(System.lineSeparator());

		QueryExecution queryExecution = QueryExecutionFactory.create(query.toString(), dataset);
		ResultSet resultSet = queryExecution.execSelect();
		return convertResultSetToJSONString(resultSet);
	}

	@Override
	public String getCovidByStockIndex(String stockindex) {
		Dataset dataset = loadDataset();

		StringBuilder query = new StringBuilder();
		query.append("PREFIX dbp: <http://dbpedia.org/ontology/>").append(System.lineSeparator());
		query.append("PREFIX sch: <https://schema.org/>").append(System.lineSeparator());
		query.append("SELECT ?country ?year ?gdppercent ?indexprice ?stockindex ?sameasstockindex ?inflationrate ").append(System.lineSeparator());
		query.append("WHERE {").append(System.lineSeparator());
		query.append("	?covid dbp:country ?country.").append(System.lineSeparator());
		query.append("	?covid dbp:stockindex ?stockindex.").append(System.lineSeparator());
		query.append("	?covid dbp:sameasstockindex ?sameasstockindex.").append(System.lineSeparator());
		query.append("	?covid dbp:year ?year.").append(System.lineSeparator());
		query.append("	?covid dbp:gdppercent ?gdppercent.").append(System.lineSeparator());
		query.append("	?covid dbp:indexprice ?indexprice.").append(System.lineSeparator());
		query.append("	?covid dbp:inflationrate ?inflationrate.").append(System.lineSeparator());
		query.append("	FILTER((LCASE(?stockindex) = \"" + stockindex.toLowerCase() + "\"))").append(System.lineSeparator());
		query.append("}").append(System.lineSeparator());

		QueryExecution queryExecution = QueryExecutionFactory.create(query.toString(), dataset);
		ResultSet resultSet = queryExecution.execSelect();
		return convertResultSetToJSONString(resultSet);
	}

//	Filtering by Stockindex NASDAQ in the Data 
	@Override
	public String getCovidCaseByCountry(String country) {

		Dataset dataset = loadDataset();
				
		StringBuilder query = new StringBuilder();
		query.append("PREFIX dbp: <http://dbpedia.org/ontology/>").append(System.lineSeparator());
		query.append("PREFIX sch: <https://schema.org/>").append(System.lineSeparator());
		query.append("SELECT ?country ?year ?gdppercent ?indexprice ?stockindex ?sameasstockindex ?inflationrate ").append(System.lineSeparator());
		query.append("WHERE {").append(System.lineSeparator());
		query.append("	?countrieseconomicdata dbp:country ?country.").append(System.lineSeparator());
		query.append("	?countrieseconomicdata dbp:year ?year.").append(System.lineSeparator());
		query.append("	?countrieseconomicdata dbp:gdppercent ?gdppercent.").append(System.lineSeparator());
		query.append("	?countrieseconomicdata dbp:indexprice ?indexprice.").append(System.lineSeparator());
		query.append("	?countrieseconomicdata dbp:stockindex ?stockindex.").append(System.lineSeparator());
		query.append("	FILTER(CONTAINS(?stockindex, \"NASDAQ\") && ?country = \"" + country + "\")").append(System.lineSeparator());
		query.append("}").append(System.lineSeparator());

		QueryExecution queryExecution = QueryExecutionFactory.create(query.toString(), dataset);
		ResultSet resultSet = queryExecution.execSelect();
		return convertResultSetToJSONString(resultSet);
	}



// Country Budget

//@Override
//public String getCovidById(int id) {
//	Dataset dataset = loadDataset();
//
//	StringBuilder query = new StringBuilder();
//	query.append("PREFIX dbp: <http://dbpedia.org/ontology/>").append(System.lineSeparator());
//	query.append("PREFIX sch: <https://schema.org/>").append(System.lineSeparator());
//	query.append("SELECT ?country  ?sameas ?budgetpercapita ?year ?imdb_url ?wikidata_url").append(System.lineSeparator());
//	query.append("WHERE {").append(System.lineSeparator());
//	query.append("	<http://odws.univaq.it/countriesbudget/"+id+">  dbp:country ?country.").append(System.lineSeparator());
//	query.append("	<http://odws.univaq.it/countriesbudget/"+id+">  dbp:sameas ?sameas.").append(System.lineSeparator());
//	query.append("	<http://odws.univaq.it/countriesbudget/"+id+">  dbp:budgetpercapita ?budgetpercapita.").append(System.lineSeparator());
//	query.append("	<http://odws.univaq.it/countriesbudget/"+id+"> dbp:year ?year.").append(System.lineSeparator());
//	query.append("}").append(System.lineSeparator());
//
//	QueryExecution queryExecution = QueryExecutionFactory.create(query.toString(), dataset);
//	ResultSet resultSet = queryExecution.execSelect();
//	return convertResultSetToJSONString(resultSet);
//}



//@Override
//public String getCovidByCountry(String country) {
//	// TODO Auto-generated method stub
//	Dataset dataset = loadDataset();
//
//	StringBuilder query = new StringBuilder();
//	query.append("PREFIX dbp: <http://dbpedia.org/ontology/>").append(System.lineSeparator());
//	query.append("PREFIX sch: <https://schema.org/>").append(System.lineSeparator());
//	query.append("SELECT ?country  ?sameas ?budgetpercapita ?year ?imdb_url ?wikidata_url").append(System.lineSeparator());
//	query.append("WHERE {").append(System.lineSeparator());
//	query.append("	?countriesbudget dbp:country ?country.").append(System.lineSeparator());
//	query.append("	?countriesbudget dbp:sameas ?sameas.").append(System.lineSeparator());
//	query.append("	?countriesbudget dbp:budgetpercapita ?budgetpercapita.").append(System.lineSeparator());
//	query.append("	?countriesbudget dbp:year ?year.").append(System.lineSeparator());
//	query.append("	FILTER((LCASE(?country) = \"" + country.toLowerCase() + "\"))").append(System.lineSeparator());
//	query.append("}").append(System.lineSeparator());
//
//	QueryExecution queryExecution = QueryExecutionFactory.create(query.toString(), dataset);
//	ResultSet resultSet = queryExecution.execSelect();
//	return convertResultSetToJSONString(resultSet);
//}



//COVID19 CaseReport
//
//@Override
//public String getCovidById(int id) {
//	Dataset dataset = loadDataset();
//
//	StringBuilder query = new StringBuilder();
//	query.append("PREFIX dbp: <http://dbpedia.org/ontology/>").append(System.lineSeparator());
//	query.append("PREFIX sch: <https://schema.org/>").append(System.lineSeparator());
//	query.append("SELECT ?country  ?sameascountry ?continent ?population ?totalcases ?totaldeaths ?criticalcases ?casespermillionpeople ?deathspermillionpeople ?totaltests ?testspermillionpeople ?whoregion ?imdb_url ?wikidata_url").append(System.lineSeparator());
//	query.append("WHERE {").append(System.lineSeparator());
//	query.append("	<http://odws.univaq.it/covidcasereport/"+id+">  dbp:country ?country.").append(System.lineSeparator());
//	query.append("	<http://odws.univaq.it/covidcasereport/"+id+">  dbp:continent ?continent.").append(System.lineSeparator());
//	query.append("	<http://odws.univaq.it/covidcasereport/"+id+">  dbp:population ?population.").append(System.lineSeparator());
//	query.append("	<http://odws.univaq.it/covidcasereport/"+id+"> dbp:totalcases ?totalcases.").append(System.lineSeparator());
//	query.append("	<http://odws.univaq.it/covidcasereport/"+id+">  dbp:totaldeaths ?totaldeaths.").append(System.lineSeparator());
//	query.append("	<http://odws.univaq.it/covidcasereport/"+id+">  dbp:criticalcases ?criticalcases.").append(System.lineSeparator());
//	query.append("	<http://odws.univaq.it/covidcasereport/"+id+">  dbp:casespermillionpeople ?casespermillionpeople.").append(System.lineSeparator());
//	query.append("	<http://odws.univaq.it/covidcasereport/"+id+"> dbp:deathspermillionpeople ?deathspermillionpeople.").append(System.lineSeparator());
//	query.append("	<http://odws.univaq.it/covidcasereport/"+id+"> dbp:whoregion ?whoregion.").append(System.lineSeparator());
//	query.append("}").append(System.lineSeparator());
//
//	QueryExecution queryExecution = QueryExecutionFactory.create(query.toString(), dataset);
//	ResultSet resultSet = queryExecution.execSelect();
//	return convertResultSetToJSONString(resultSet);
//}

//@Override
//public String getCovidByCountry(String country) {
//	// TODO Auto-generated method stub
//	Dataset dataset = loadDataset();
//
//	StringBuilder query = new StringBuilder();
//	query.append("PREFIX dbp: <http://dbpedia.org/ontology/>").append(System.lineSeparator());
//	query.append("PREFIX sch: <https://schema.org/>").append(System.lineSeparator());
//	query.append("SELECT ?country  ?sameascountry ?continent ?population ?totalcases ?totaldeaths ?criticalcases ?casespermillionpeople ?deathspermillionpeople ?totaltests ?testspermillionpeople ?whoregion ?imdb_url ?wikidata_url").append(System.lineSeparator());
//	query.append("WHERE {").append(System.lineSeparator());
//	query.append("	?covidcasereport dbp:country ?country.").append(System.lineSeparator());
//	query.append("	?covidcasereport dbp:sameascountry ?sameascountry.").append(System.lineSeparator());
//	query.append("	?covidcasereport dbp:continent ?continent.").append(System.lineSeparator());
//	query.append("	?covidcasereport dbp:population ?population.").append(System.lineSeparator());
//	query.append("	?covidcasereport dbp:totalcases ?totalcases.").append(System.lineSeparator());
//	query.append("	?covidcasereport dbp:totaldeaths ?totaldeaths.").append(System.lineSeparator());
//	query.append("	?covidcasereport dbp:criticalcases ?criticalcases.").append(System.lineSeparator());
//	query.append("	?covidcasereport dbp:casespermillionpeople ?casespermillionpeople.").append(System.lineSeparator());
//	query.append("	?covidcasereport dbp:deathspermillionpeople ?deathspermillionpeople.").append(System.lineSeparator());
//	query.append("	?covidcasereport dbp:totaltests ?totaltests.").append(System.lineSeparator());
//	query.append("	?covidcasereport dbp:testspermillionpeople ?testspermillionpeople.").append(System.lineSeparator());
//	query.append("	FILTER((LCASE(?country) = \"" + country.toLowerCase() + "\"))").append(System.lineSeparator());
//	query.append("}").append(System.lineSeparator());
//
//	QueryExecution queryExecution = QueryExecutionFactory.create(query.toString(), dataset);
//	ResultSet resultSet = queryExecution.execSelect();
//	return convertResultSetToJSONString(resultSet);
//}
	
//COVID19 Impact
//
//@Override
//public String getCovidById(int id) {
//	Dataset dataset = loadDataset();
//
//	StringBuilder query = new StringBuilder();
//	query.append("PREFIX dbp: <http://dbpedia.org/ontology/>").append(System.lineSeparator());
//	query.append("PREFIX sch: <https://schema.org/>").append(System.lineSeparator());
//	query.append("SELECT ?countryCode ?country  ?sameascountry ?date ?humanDevelopmentIndex ?tradediversion ?gdpPerCapita ?imdb_url ?wikidata_url").append(System.lineSeparator());
//	query.append("WHERE {").append(System.lineSeparator());
//	query.append("	<http://odws.univaq.it/covideconomicimpact/"+id+">  dbp:countryCode ?countryCode.").append(System.lineSeparator());
//	query.append("	<http://odws.univaq.it/covideconomicimpact/"+id+">  dbp:country ?country.").append(System.lineSeparator());
//	query.append("	<http://odws.univaq.it/covideconomicimpact/"+id+">  dbp:sameascounty ?sameascounty.").append(System.lineSeparator());
//	query.append("	<http://odws.univaq.it/covideconomicimpact/"+id+">  dbp:date ?date.").append(System.lineSeparator());
//	query.append("	<http://odws.univaq.it/covideconomicimpact/"+id+"> dbp:humanDevelopmentIndex ?humanDevelopmentIndex.").append(System.lineSeparator());
//	query.append("	<http://odws.univaq.it/covideconomicimpact/"+id+">  dbp:tradediversion ?tradediversion.").append(System.lineSeparator());
//	query.append("	<http://odws.univaq.it/covideconomicimpact/"+id+">  dbp:gdpPerCapita ?gdpPerCapita.").append(System.lineSeparator());
//	query.append("}").append(System.lineSeparator());
//
//	QueryExecution queryExecution = QueryExecutionFactory.create(query.toString(), dataset);
//	ResultSet resultSet = queryExecution.execSelect();
//	return convertResultSetToJSONString(resultSet);
//}

//@Override
//public String getCovidByCountry(String country) {
//	Dataset dataset = loadDataset();
//
//	StringBuilder query = new StringBuilder();
//query.append("PREFIX dbp: <http://dbpedia.org/ontology/>").append(System.lineSeparator());
//query.append("PREFIX sch: <https://schema.org/>").append(System.lineSeparator());
//query.append("SELECT ?countryCode ?country  ?sameascountry ?date ?humanDevelopmentIndex ?tradediversion ?gdpPerCapita ?imdb_url ?wikidata_url").append(System.lineSeparator());
//query.append("WHERE {").append(System.lineSeparator());
//query.append("	?covideconomicimpact  dbp:countryCode ?countryCode.").append(System.lineSeparator());
//query.append("	?covideconomicimpact  dbp:country ?country.").append(System.lineSeparator());
//query.append("	?covideconomicimpact  dbp:sameascounty ?sameascounty.").append(System.lineSeparator());
//query.append("	?covideconomicimpact  dbp:date ?date.").append(System.lineSeparator());
//query.append("	?covideconomicimpact dbp:humanDevelopmentIndex ?humanDevelopmentIndex.").append(System.lineSeparator());
//query.append("	?covideconomicimpact  dbp:tradediversion ?tradediversion.").append(System.lineSeparator());
//query.append("	?covideconomicimpact  dbp:gdpPerCapita ?gdpPerCapita.").append(System.lineSeparator());
//query.append("	FILTER((LCASE(?country) = \"" + country.toLowerCase() + "\"))").append(System.lineSeparator());
//query.append("}").append(System.lineSeparator());
//
//	QueryExecution queryExecution = QueryExecutionFactory.create(query.toString(), dataset);
//	ResultSet resultSet = queryExecution.execSelect();
//	return convertResultSetToJSONString(resultSet);
//}
	
	
	//Public Health Safety

//	@Override
//	public String getCovidById(int id) {
//		Dataset dataset = loadDataset();
//		StringBuilder query = new StringBuilder();
//		query.append("PREFIX dbp: <http://dbpedia.org/ontology/>").append(System.lineSeparator());
//		query.append("PREFIX sch: <https://schema.org/>").append(System.lineSeparator());
//		query.append("SELECT ?date ?country  ?sameascountry ?isoCodeRegion ?whoregion ?masks ?gatherings ?schools ?businesses ?movement ?globalindex ?measuresinplace ?imdb_url ?wikidata_url").append(System.lineSeparator());
//		query.append("WHERE {").append(System.lineSeparator());
//		query.append("	<http://odws.univaq.it/healthmeasures/"+id+">  dbp:date ?date.").append(System.lineSeparator());
//		query.append("	<http://odws.univaq.it/healthmeasures/"+id+">  dbp:country ?country.").append(System.lineSeparator());
//		query.append("	<http://odws.univaq.it/healthmeasures/"+id+">  dbp:sameascounty ?sameascounty.").append(System.lineSeparator());
//		query.append("	<http://odws.univaq.it/healthmeasures/"+id+">  dbp:isoCodeRegion ?isoCodeRegion.").append(System.lineSeparator());
//		query.append("	<http://odws.univaq.it/healthmeasures/"+id+"> dbp:whoregion ?whoregion.").append(System.lineSeparator());
//		query.append("	<http://odws.univaq.it/healthmeasures/"+id+">  dbp:masks ?masks.").append(System.lineSeparator());
//		query.append("	<http://odws.univaq.it/healthmeasures/"+id+">  dbp:gatherings ?gatherings.").append(System.lineSeparator());
//		query.append("	<http://odws.univaq.it/healthmeasures/"+id+">  dbp:schools ?schools.").append(System.lineSeparator());
//		query.append("	<http://odws.univaq.it/healthmeasures/"+id+">  dbp:businesses ?businesses.").append(System.lineSeparator());
//		query.append("	<http://odws.univaq.it/healthmeasures/"+id+"> dbp:movement ?movement.").append(System.lineSeparator());
//		query.append("	<http://odws.univaq.it/healthmeasures/"+id+">  dbp:globalindex ?globalindex.").append(System.lineSeparator());
//		query.append("	<http://odws.univaq.it/healthmeasures/"+id+">  dbp:measuresinplace ?measuresinplace.").append(System.lineSeparator());
//		query.append("}").append(System.lineSeparator());
//
//		QueryExecution queryExecution = QueryExecutionFactory.create(query.toString(), dataset);
//		ResultSet resultSet = queryExecution.execSelect();
//		return convertResultSetToJSONString(resultSet);
//	}
	
//	@Override
//	public String getCovidByCountry(String country) {
//		Dataset dataset = loadDataset();
//		StringBuilder query = new StringBuilder();
//		query.append("PREFIX dbp: <http://dbpedia.org/ontology/>").append(System.lineSeparator());
//		query.append("PREFIX sch: <https://schema.org/>").append(System.lineSeparator());
//		query.append("SELECT ?date ?country  ?sameascountry ?isoCodeRegion ?whoregion ?masks ?gatherings ?schools ?businesses ?movement ?globalindex ?measuresinplace ?imdb_url ?wikidata_url").append(System.lineSeparator());
//		query.append("WHERE {").append(System.lineSeparator());
//		query.append("	?healthmeasures  dbp:date ?date.").append(System.lineSeparator());
//		query.append("	?healthmeasures  dbp:country ?country.").append(System.lineSeparator());
//		query.append("	?healthmeasures  dbp:sameascounty ?sameascounty.").append(System.lineSeparator());
//		query.append("	?healthmeasures  dbp:isoCodeRegion ?isoCodeRegion.").append(System.lineSeparator());
//		query.append("	?healthmeasures dbp:whoregion ?whoregion.").append(System.lineSeparator());
//		query.append("	?healthmeasures  dbp:masks ?masks.").append(System.lineSeparator());
//		query.append("	?healthmeasures  dbp:gatherings ?gatherings.").append(System.lineSeparator());
//		query.append("	?healthmeasures  dbp:schools ?schools.").append(System.lineSeparator());
//		query.append("	?healthmeasures  dbp:businesses ?businesses.").append(System.lineSeparator());
//		query.append("	?healthmeasures dbp:movement ?movement.").append(System.lineSeparator());
//		query.append("	?healthmeasures  dbp:globalindex ?globalindex.").append(System.lineSeparator());
//		query.append("	?healthmeasures  dbp:measuresinplace ?measuresinplace.").append(System.lineSeparator());
//		query.append("	FILTER((LCASE(?country) = \"" + country.toLowerCase() + "\"))").append(System.lineSeparator());
//		query.append("}").append(System.lineSeparator());
//
//		QueryExecution queryExecution = QueryExecutionFactory.create(query.toString(), dataset);
//		ResultSet resultSet = queryExecution.execSelect();
//		return convertResultSetToJSONString(resultSet);
//	}
}
	
	