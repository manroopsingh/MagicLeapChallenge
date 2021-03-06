package com.example.user.magicleapchallenge.model;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity
public class CoffeeItem {

    @PrimaryKey
    @NonNull
    @SerializedName("id")
    private String id;

    @SerializedName("image_url")
	private String imageUrl;

    @SerializedName("name")
	private String name;


	@SerializedName("desc")
	private String desc;

	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}

	public String getImageUrl(){
		return imageUrl;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setDesc(String desc){
		this.desc = desc;
	}

	public String getDesc(){
		return desc;
	}

	@Override
 	public String toString(){
		return 
			"CoffeeItem{" +
			"image_url = '" + imageUrl + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",desc = '" + desc + '\'' + 
			"}";
		}
}