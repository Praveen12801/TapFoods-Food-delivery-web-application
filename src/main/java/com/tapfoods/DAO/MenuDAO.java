package com.tapfoods.DAO;

import java.util.List;

import com.tapfoods.Model.Menu;

public interface MenuDAO {
	
	void addMenu(Menu menu);
	
	Menu getMenu(int menuId);
	
	void updateMenu(Menu menu);
	
	void deleteMenu(int menuId);
	
	List<Menu> getAllMenu();
	
	List<Menu> getMenuByResId(int restaurantId);

}
