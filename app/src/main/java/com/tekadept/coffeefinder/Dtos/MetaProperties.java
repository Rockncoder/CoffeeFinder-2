
package com.tekadept.coffeefinder.Dtos;

import java.util.List;

public class MetaProperties{
   	private String didYouMean;
   	private String errorCode;
   	private InputParams inputParams;
   	private Number listingCount;
   	private String message;
   	private RelatedCategories relatedCategories;
   	private String requestId;
   	private String resultCode;
   	private String searchCity;
   	private Number searchLat;
   	private Number searchLon;
   	private String searchState;
   	private String searchType;
   	private Number searchZip;
   	private Number totalAvailable;
   	private String trackingRequestURL;
   	private String ypcAttribution;

 	public String getDidYouMean(){
		return this.didYouMean;
	}
	public void setDidYouMean(String didYouMean){
		this.didYouMean = didYouMean;
	}
 	public String getErrorCode(){
		return this.errorCode;
	}
	public void setErrorCode(String errorCode){
		this.errorCode = errorCode;
	}
 	public InputParams getInputParams(){
		return this.inputParams;
	}
	public void setInputParams(InputParams inputParams){
		this.inputParams = inputParams;
	}
 	public Number getListingCount(){
		return this.listingCount;
	}
	public void setListingCount(Number listingCount){
		this.listingCount = listingCount;
	}
 	public String getMessage(){
		return this.message;
	}
	public void setMessage(String message){
		this.message = message;
	}
 	public RelatedCategories getRelatedCategories(){
		return this.relatedCategories;
	}
	public void setRelatedCategories(RelatedCategories relatedCategories){
		this.relatedCategories = relatedCategories;
	}
 	public String getRequestId(){
		return this.requestId;
	}
	public void setRequestId(String requestId){
		this.requestId = requestId;
	}
 	public String getResultCode(){
		return this.resultCode;
	}
	public void setResultCode(String resultCode){
		this.resultCode = resultCode;
	}
 	public String getSearchCity(){
		return this.searchCity;
	}
	public void setSearchCity(String searchCity){
		this.searchCity = searchCity;
	}
 	public Number getSearchLat(){
		return this.searchLat;
	}
	public void setSearchLat(Number searchLat){
		this.searchLat = searchLat;
	}
 	public Number getSearchLon(){
		return this.searchLon;
	}
	public void setSearchLon(Number searchLon){
		this.searchLon = searchLon;
	}
 	public String getSearchState(){
		return this.searchState;
	}
	public void setSearchState(String searchState){
		this.searchState = searchState;
	}
 	public String getSearchType(){
		return this.searchType;
	}
	public void setSearchType(String searchType){
		this.searchType = searchType;
	}
 	public Number getSearchZip(){
		return this.searchZip;
	}
	public void setSearchZip(Number searchZip){
		this.searchZip = searchZip;
	}
 	public Number getTotalAvailable(){
		return this.totalAvailable;
	}
	public void setTotalAvailable(Number totalAvailable){
		this.totalAvailable = totalAvailable;
	}
 	public String getTrackingRequestURL(){
		return this.trackingRequestURL;
	}
	public void setTrackingRequestURL(String trackingRequestURL){
		this.trackingRequestURL = trackingRequestURL;
	}
 	public String getYpcAttribution(){
		return this.ypcAttribution;
	}
	public void setYpcAttribution(String ypcAttribution){
		this.ypcAttribution = ypcAttribution;
	}
}
