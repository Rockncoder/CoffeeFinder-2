
package com.tekadept.coffeefinder.Dtos;

import java.util.List;

public class RelatedCategory{
   	private Number count;
   	private String name;

 	public Number getCount(){
		return this.count;
	}
	public void setCount(Number count){
		this.count = count;
	}
 	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
}
