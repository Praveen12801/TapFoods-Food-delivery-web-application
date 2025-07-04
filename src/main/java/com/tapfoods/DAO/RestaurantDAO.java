package com.tapfoods.DAO;

import java.util.List;

import com.tapfoods.Model.Restaurant;

public interface RestaurantDAO {
	
	void addRestaurant(Restaurant restaurant);

	Restaurant getRestaurantById(int restaurantId);

	void updateRestaurant(Restaurant restaurant);

	void deleteRestaurant(int restaurantId);

	List<Restaurant> getAllRestaurants();
}
