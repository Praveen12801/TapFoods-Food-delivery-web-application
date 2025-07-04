package com.tapfoods.Model;

public class Restaurant {
	
	private int restaurantId;
	private String name;
	private String address;
	private String phone;
	private String cuisineType;
	private String time;
	private int adminUserId;
	private float rating;
	private String isActive;
	private String imagePath;
	
	public Restaurant() {
		
	}
	
	public Restaurant(int restaurantId, String name, String address, String phone, String cuisineType, String time,
			int adminUserId, float rating, String isActive, String imagePath) {
		super();
		this.restaurantId = restaurantId;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.cuisineType = cuisineType;
		this.time = time;
		this.adminUserId = adminUserId;
		this.rating = rating;
		this.isActive = isActive;
		this.imagePath = imagePath;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCusineType() {
		return cuisineType;
	}

	public void setCusineType(String cusineType) {
		this.cuisineType = cusineType;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(int adminUserId) {
		this.adminUserId = adminUserId;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}	
	
	
}
