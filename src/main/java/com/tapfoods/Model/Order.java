package com.tapfoods.Model;

import java.sql.Timestamp;

public class Order {
	
	private int orderId;
	private int restaurantId;
	private int userId;
	private Timestamp orderDate;
	private double totalAmount;
	private String status;
	private PaymentMode paymentMode;
	
	
	public Order(){
		
	}
	
	
	public Order(int orderId, int restaurantId, int userId, Timestamp orderDate, double totalAmount, String status,
			PaymentMode paymentMode) {
		super();
		this.orderId = orderId;
		this.restaurantId = restaurantId;
		this.userId = userId;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.status = status;
		this.paymentMode = paymentMode;
	}
	
	

	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public int getRestaurantId() {
		return restaurantId;
	}


	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public Timestamp getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}


	public double getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public enum PaymentMode {
		CASH_ON_DELIVERY, UPI, CARD
	}
	
	
	public PaymentMode getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(PaymentMode paymentMode) {
		this.paymentMode = paymentMode;
	}

}
