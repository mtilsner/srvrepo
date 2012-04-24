package de.cocktail.srvrepo;

//import java.util.Map;
//import srvrepo.Keyword;
import srvrepo.Specification;
import srvrepo.Vendor;

public class ServiceQuery extends SpecificationQuery {
	
	private Specification specification;
	public Specification getSpecification() { return specification; }
	public void setSpecification(Specification specification) { this.specification = specification; }
	
	private Vendor vendor;
	public Vendor getVendor() { return vendor; }
	public void setVendor(Vendor vendor) { this.vendor = vendor; }
	
/*	private Map<Keyword,String> details;
	public Map<Keyword,String> getDetails() { return details; }
	public void setDetails(Map<Keyword,String> details) { this.details = details; }
	
	private Map<Keyword,String> feedbacks;
	public Map<Keyword,String> getFeedbacks() { return feedbacks; }
	public void setFeedbacks(Map<Keyword,String> feedbacks) { this.feedbacks = feedbacks; }
	
	private Map<Keyword,String> qualitites;
	public Map<Keyword,String> getQualitites() { return qualitites; }
	public void setQualities(Map<Keyword,String> qualities) { this.qualitites = qualitites; }*/
	
}