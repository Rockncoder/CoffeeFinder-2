
package com.tekadept.coffeefinder.Dtos;

import java.util.List;

public class InputParams{
   	private String appId;
   	private String dnt;
   	private String format;
   	private Number listingCount;
   	private boolean phoneSearch;
   	private Number radius;
   	private String requestId;
   	private String searchLocation;
   	private boolean shortUrl;
   	private String sort;
   	private String term;
   	private String termType;
   	private String test;
   	private String userAgent;
   	private String userIpAddress;
   	private String userReferrer;
   	private String visitorId;

 	public String getAppId(){
		return this.appId;
	}
	public void setAppId(String appId){
		this.appId = appId;
	}
 	public String getDnt(){
		return this.dnt;
	}
	public void setDnt(String dnt){
		this.dnt = dnt;
	}
 	public String getFormat(){
		return this.format;
	}
	public void setFormat(String format){
		this.format = format;
	}
 	public Number getListingCount(){
		return this.listingCount;
	}
	public void setListingCount(Number listingCount){
		this.listingCount = listingCount;
	}
 	public boolean getPhoneSearch(){
		return this.phoneSearch;
	}
	public void setPhoneSearch(boolean phoneSearch){
		this.phoneSearch = phoneSearch;
	}
 	public Number getRadius(){
		return this.radius;
	}
	public void setRadius(Number radius){
		this.radius = radius;
	}
 	public String getRequestId(){
		return this.requestId;
	}
	public void setRequestId(String requestId){
		this.requestId = requestId;
	}
 	public String getSearchLocation(){
		return this.searchLocation;
	}
	public void setSearchLocation(String searchLocation){
		this.searchLocation = searchLocation;
	}
 	public boolean getShortUrl(){
		return this.shortUrl;
	}
	public void setShortUrl(boolean shortUrl){
		this.shortUrl = shortUrl;
	}
 	public String getSort(){
		return this.sort;
	}
	public void setSort(String sort){
		this.sort = sort;
	}
 	public String getTerm(){
		return this.term;
	}
	public void setTerm(String term){
		this.term = term;
	}
 	public String getTermType(){
		return this.termType;
	}
	public void setTermType(String termType){
		this.termType = termType;
	}
 	public String getTest(){
		return this.test;
	}
	public void setTest(String test){
		this.test = test;
	}
 	public String getUserAgent(){
		return this.userAgent;
	}
	public void setUserAgent(String userAgent){
		this.userAgent = userAgent;
	}
 	public String getUserIpAddress(){
		return this.userIpAddress;
	}
	public void setUserIpAddress(String userIpAddress){
		this.userIpAddress = userIpAddress;
	}
 	public String getUserReferrer(){
		return this.userReferrer;
	}
	public void setUserReferrer(String userReferrer){
		this.userReferrer = userReferrer;
	}
 	public String getVisitorId(){
		return this.visitorId;
	}
	public void setVisitorId(String visitorId){
		this.visitorId = visitorId;
	}
}
