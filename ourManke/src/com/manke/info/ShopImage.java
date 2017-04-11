package com.manke.info;

public class ShopImage {

	private String id;
	private ShopImage bitmap;
	private String name;
	private String price;
	private String pictureID;
	private String address; 
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public ShopImage getBitmap() {
		return bitmap;
	}
	public void setBitmap(ShopImage bitmap) {
		this.bitmap = bitmap;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPictureID() {
		return pictureID;
	}
	public void setPictureID(String pictureID) {
		this.pictureID = pictureID;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
