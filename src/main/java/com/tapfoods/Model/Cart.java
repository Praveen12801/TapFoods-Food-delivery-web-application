package com.tapfoods.Model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	
	private Map<Integer, CartItem> items = new HashMap<>();
	
	public Cart()
	{
		items = new HashMap<Integer, CartItem>();
	}
	
	public void addCartItem(CartItem item)
	{
		int menuId = item.getMenuId();
		
		if(items.containsKey(menuId))
		{
			CartItem existingCartItem = items.get(menuId);
			existingCartItem.setQuantity(item.getQuantity() + existingCartItem.getQuantity() );
		}else {
			items.put(menuId, item);
		}
	}
	
	public void updateCartItem(int menuId, int quantity)
	{
		if(items.containsKey(menuId)) {
			if(quantity<=0) {
				items.remove(menuId);
			}else {
				items.get(menuId).setQuantity(quantity);
			}
		}
	}
	
	public void removeItem(int menuId) {
		items.remove(menuId);
	}
	
	public Map<Integer, CartItem> getItems(){
		return items;
	}
	
	public double getTotalPrice() {
		return items.values().stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
	}
	
	public void clear() {
		items.clear();
	}


	
}
