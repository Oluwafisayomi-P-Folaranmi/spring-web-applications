package com.opf.restaurant;

import com.opf.restaurant.model.MenuItem;
import com.opf.restaurant.service.MenuItemService;
import com.opf.restaurant.service.MenuItemServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class RestaurantApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(MenuItemServiceImpl menuItemServiceImpl) {
		return runner -> {
			System.out.println("Welcoming the developer. The application is running.");
			// saveMenuItemMethod(menuItemServiceImpl);
			// findMenuItemsMethod(menuItemServiceImpl);
			// findMenuItemByIdMethod(menuItemServiceImpl, 3);
		};
	}

	public static void saveMenuItemMethod(MenuItemServiceImpl menuItemServiceImpl) {
		/*
		 * 1. ('Cheeseburger', 5.99, 50, '2025-03-10 12:30:00'),
		 * 2. ('Margherita Pizza', 9.99, 30, '2025-03-10 12:35:00'),
		 * 3. ('Caesar Salad', 7.49, 40, '2025-03-10 12:40:00');
		 */
		// Create a menu item
		MenuItem menuItem1 = new MenuItem("Cheeseburger", 5.99, 50, "2025-03-10 12:30:00");
		MenuItem menuItem2 = new MenuItem("Margherita Pizza", 9.99, 30, "2025-03-10 12:35:00");
		MenuItem menuItem3 = new MenuItem("Caesar Salad", 7.49, 40, "2025-03-10 12:40:00");

		// Save the menu items to database
		menuItemServiceImpl.saveMenuItem(menuItem1);
		menuItemServiceImpl.saveMenuItem(menuItem2);
		menuItemServiceImpl.saveMenuItem(menuItem3);
		System.out.println("Menu items are saved successfully to database.");
	}

	public static void findMenuItemsMethod(MenuItemServiceImpl menuItemServiceImpl) {
		List<MenuItem> menuItems = menuItemServiceImpl.findAll();
		System.out.println("The menu items are:");
		for(MenuItem menuItem : menuItems) {
			System.out.println("\t\t" + menuItem.toString());
		}
	}

	public static void findMenuItemByIdMethod(MenuItemServiceImpl menuItemServiceImpl, Integer theId) {
		MenuItem menuItem = menuItemServiceImpl.findById(theId);
		System.out.println("The menu item with id of '" + theId + "' is: \n\t\t" +
				menuItem.toString());
	}
}
