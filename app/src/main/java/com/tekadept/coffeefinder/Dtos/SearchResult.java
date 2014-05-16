
package com.tekadept.coffeefinder.Dtos;

import java.util.List;

public class SearchResult{
   	private MetaProperties metaProperties;
   	private SearchListings searchListings;

 	public MetaProperties getMetaProperties(){
		return this.metaProperties;
	}
	public void setMetaProperties(MetaProperties metaProperties){
		this.metaProperties = metaProperties;
	}
 	public SearchListings getSearchListings(){
		return this.searchListings;
	}
	public void setSearchListings(SearchListings searchListings){
		this.searchListings = searchListings;
	}
}
