package com.tapfoods.DAO;

import java.util.List;

import com.tapfoods.Model.Order;

public interface OrderDAO {
	
	int addOrder(Order order);

	Order getOrder(int orderId);

	void updateOrder(Order order);

	void deleteOrder(int orderId);

	List<Order> getAllOrders();

	List<Order> getAllOrdersByUser(int userId);
		

}
