package it.univaq.odws.rest.covid.multiples;

import java.io.ByteArrayOutputStream;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.riot.RDFDataMgr;

public class CovidNewImpl implements CovidNew{
	
	private static final String COVID_DATASET = "Economic-Data.rdf";
	private static final String COVID_DATASET_NEW = "Covid19-Impact.rdf";
	
	private Dataset loadDataset() {
		Dataset dataset = RDFDataMgr.loadDataset(COVID_DATASET);
		RDFDataMgr.read(dataset, COVID_DATASET_NEW);
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
		query.append("SELECT ?country ?year ?gdppercent ?indexprice ?stockindex ?sameasstockindex ?inflationrate").append(System.lineSeparator());
		query.append("WHERE {").append(System.lineSeparator());
		query.append("	<http://odws.univaq.it/covid/"+id+">  dbp:country ?country.").append(System.lineSeparator());
		query.append("	<http://odws.univaq.it/covid/"+id+">  dbp:countryCode ?countryCode.").append(System.lineSeparator());
		query.append("	<http://odws.univaq.it/covid/"+id+">  dbp:stockindex ?stockindex.").append(System.lineSeparator());
		query.append("	<http://odws.univaq.it/covid/"+id+">  dbp:sameasstockindex ?sameasstockindex.").append(System.lineSeparator());
		query.append("	<http://odws.univaq.it/covid/"+id+"> dbp:gdppercent ?gdppercent.").append(System.lineSeparator());
		query.append("	<http://odws.univaq.it/covid/"+id+"> dbp:year ?year.").append(System.lineSeparator());
		query.append("	<http://odws.univaq.it/covid/"+id+"> dbp:indexprice ?indexprice.").append(System.lineSeparator());
		query.append("	<http://odws.univaq.it/covid/"+id+"> dbp:inflationrate ?inflationrate").append(System.lineSeparator());
		query.append("}").append(System.lineSeparator());

		QueryExecution queryExecution = QueryExecutionFactory.create(query.toString(), dataset);
		ResultSet resultSet = queryExecution.execSelect();
		return convertResultSetToJSONString(resultSet);
	}

	@Override
	public String getCovidByCountry(String country) {
		// TODO Auto-generated method stub
		Dataset dataset = loadDataset();

		StringBuilder query = new StringBuilder();
		query.append("PREFIX dbp: <http://dbpedia.org/ontology/>").append(System.lineSeparator());
		query.append("PREFIX sch: <https://schema.org/>").append(System.lineSeparator());
		query.append("SELECT ?country ?countryCode ?year ?gdppercent ?indexprice ?stockindex ?sameasstockindex ?inflationrate ").append(System.lineSeparator());
		query.append("WHERE {").append(System.lineSeparator());
		query.append("	?covid dbp:country ?country.").append(System.lineSeparator());
		query.append("	?covid dbp:countryCode ?countryCode.").append(System.lineSeparator());
		query.append("	?covid dbp:stockindex ?stockindex.").append(System.lineSeparator());
		query.append("	?covid dbp:sameasstockindex ?sameasstockindex.").append(System.lineSeparator());
		query.append("	?covid dbp:year ?year.").append(System.lineSeparator());
		query.append("	?covid dbp:gdppercent ?gdppercent.").append(System.lineSeparator());
		query.append("	?covid dbp:indexprice ?indexprice.").append(System.lineSeparator());
		query.append("	?covid dbp:inflationrate ?inflationrate.").append(System.lineSeparator());
		query.append("	FILTER((LCASE(?country) = \"" + country.toLowerCase() + "\"))").append(System.lineSeparator());
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
		@Override
		public String getCovidCaseByCountry(String country) {
//			String name = "NASDAQ";
			Dataset dataset = loadDataset();
					
			StringBuilder query = new StringBuilder();
			query.append("PREFIX dbp: <http://dbpedia.org/ontology/>").append(System.lineSeparator());
			query.append("PREFIX sch: <https://schema.org/>").append(System.lineSeparator());
			query.append("SELECT ?country ?countryCode ?year ?gdppercent ?indexprice ?stockindex ?sameasstockindex ?inflationrate ").append(System.lineSeparator());
			query.append("WHERE {").append(System.lineSeparator());
			query.append("	?covid dbp:country ?country.").append(System.lineSeparator());
			query.append("	?covidnew dbp:countryCode ?countryCode.").append(System.lineSeparator());
			query.append("	?covid dbp:year ?year.").append(System.lineSeparator());
			query.append("	?covid dbp:gdppercent ?gdppercent.").append(System.lineSeparator());
			query.append("	?covid dbp:indexprice ?indexprice.").append(System.lineSeparator());
			query.append("	?covid dbp:stockindex ?sameasstockindex.").append(System.lineSeparator());
			query.append("	FILTER(CONTAINS(?stockindex,\"NASDAQ\") && ?country = \"" + country + "\")").append(System.lineSeparator());
			query.append("}").append(System.lineSeparator());

			QueryExecution queryExecution = QueryExecutionFactory.create(query.toString(), dataset);
			ResultSet resultSet = queryExecution.execSelect();
			return convertResultSetToJSONString(resultSet);
		}

		@Override
		public String getCovidCaseByCountryCode(String countryCode) {
			Dataset dataset = loadDataset();

			StringBuilder query = new StringBuilder();
			query.append("PREFIX dbp: <http://dbpedia.org/ontology/>").append(System.lineSeparator());
			query.append("PREFIX sch: <https://schema.org/>").append(System.lineSeparator());
			query.append("SELECT ?country ?countryCode ?year ?gdppercent ?indexprice ?stockindex ?sameasstockindex ?inflationrate ").append(System.lineSeparator());
			query.append("WHERE {").append(System.lineSeparator());
			query.append("	?covid dbp:country ?country.").append(System.lineSeparator());
			query.append("	?covidnew dbp:countryCode ?countryCode.").append(System.lineSeparator());
			query.append("	?covid dbp:stockindex ?stockindex.").append(System.lineSeparator());
			query.append("	?covid dbp:sameasstockindex ?sameasstockindex.").append(System.lineSeparator());
			query.append("	?covid dbp:year ?year.").append(System.lineSeparator());
			query.append("	?covid dbp:gdppercent ?gdppercent.").append(System.lineSeparator());
			query.append("	?covid dbp:indexprice ?indexprice.").append(System.lineSeparator());
			query.append("	?covid dbp:inflationrate ?inflationrate.").append(System.lineSeparator());
			query.append("	FILTER((LCASE(?countryCode) = \"" + countryCode.toLowerCase() + "\"))").append(System.lineSeparator());
			query.append("}").append(System.lineSeparator());

			QueryExecution queryExecution = QueryExecutionFactory.create(query.toString(), dataset);
			ResultSet resultSet = queryExecution.execSelect();
			return convertResultSetToJSONString(resultSet);
		}

	

	
}
	