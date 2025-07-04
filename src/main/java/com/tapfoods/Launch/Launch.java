package com.tapfoods.Launch;

import java.util.List;

import com.tapfoods.DAO.UserDAO;
import com.tapfoods.DAOImple.UserDAOImple;
import com.tapfoods.Model.User;

public class Launch {

	 public static void main(String[] args) {
	        System.out.println("Program started...");
	        
	        UserDAO userDAO = new UserDAOImple();

	        // Step 2: Call getAllUsers() method
	        List<User> users = userDAO.getAllUsers();

	        // Step 3: Print the user data
	        for (User user : users) {
	            System.out.println(user);
	        }
	 }
}
	        
	        
	        
	        
       
////        user.setName("praveen");
////        user.setUsername("pk");
////        user.setPassword("pk123");
////        user.setEmail("praveen12801@gmail.com");
////        user.setPhone("1234578");
////        user.setAddress("bangalore");
////        user.setRole("customer");
//        user.setUserId(1);
////        
//        UserDAOImple u = new UserDAOImple();
//        
//        u.getUser(1);)
////        u.addUser(user);

//User user = userDao.getUser(4);  // Call the method and store the returned User object
//
//if (user != null) {
//    System.out.println("User ID: " + user.getUserId());
//    System.out.println("Name: " + user.getName());
//    System.out.println("Username: " + user.getUsername());
//    System.out.println("Email: " + user.getEmail());
//    System.out.println("Phone: " + user.getPhone());
//    System.out.println("Address: " + user.getAddress());
//    System.out.println("Role: " + user.getRole());
//    System.out.println("CreatedDate: " + user.getCreateDate());
//    
//    // You can print other fields similarly
//} else {
//    System.out.println("User not found!");
//}
	        
	        
//	        
//	        UserDAO userDao = new UserDAOImple();

	        // Create a user object with updated details
//	        User userToUpdate = new User();
//	        userToUpdate.setUserId(1); // ID of the user to update
//	        userToUpdate.setName("Updated Name");
//	        userToUpdate.setPassword("newpassword123");
//	        userToUpdate.setPhone("9876543210");
//	        userToUpdate.setAddress("New Address");

	        // ✅ Call the method to update the user
//	        userDao.updateUser(userToUpdate);
//	    }
//}       


//UserDAOImple u = new UserDAOImple();
//
//User userDelete = new User();
//
//u.deleteUser(1);
        
   
        

   