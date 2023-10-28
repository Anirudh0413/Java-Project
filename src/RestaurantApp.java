import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class Restaurant {
    private String name;
    private String cuisine;
    private String address;
    private String phoneNumber;
    private Map<String, Double> menu;

    public Restaurant(String name, String cuisine, String address, String phoneNumber, Map<String, Double> menu) {
        this.name = name;
        this.cuisine = cuisine;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.menu = menu;
    }

    public void displayMenu() {
        System.out.println(name + " Menu:");
        for (Map.Entry<String, Double> item : menu.entrySet()) {
            System.out.println(item.getKey() + ": $" + String.format("%.2f", item.getValue()));
        }
    }

    public String getName() {
        return name;
    }

    public Map<String, Double> getMenu() {
        return menu;
    }
}

public class RestaurantApp {
    
    public static void main(String[] args) {
        // Create Restaurant objects
        Map<String, Double> menu1 = new HashMap<>();
        menu1.put("SpaghettiCarbonara", 12.99);
        menu1.put("Margherita" +
                "Pizza", 10.99);
        menu1.put("Tiramisu", 6.99);
        Restaurant restaurant1 = new Restaurant("Tasty Bites", "Italian", "123 Main Street, Cityville", "555-123-4567", menu1);

        Map<String, Double> menu2 = new HashMap<>();
        menu2.put("KungPaoChicken", 11.99);
        menu2.put("VegetableFriedRice", 8.99);
        menu2.put("SpringRolls", 5.99);
        Restaurant restaurant2 = new Restaurant("Sizzling Wok", "Chinese", "456 Elm Street, Townsville", "555-987-6543", menu2);

        Map<String, Double> menu3 = new HashMap<>();
        menu3.put("BurritoBowl", 9.99);
        menu3.put("TacoSampler", 7.99);
        menu3.put("Churros", 4.99);
        Restaurant restaurant3 = new Restaurant("MexiGrill", "Mexican", "789 Oak Avenue, Villageland", "555-543-7890", menu3);

        // Display restaurant names and let customers choose one
        Restaurant[] restaurants = {restaurant1, restaurant2, restaurant3};

        System.out.println("Welcome to the Food Court! Choose a restaurant:");
        for (int i = 0; i < restaurants.length; i++) {
            System.out.println((i + 1) + ". " + restaurants[i].getName());
        }

        Scanner scanner = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.print("Enter the number of the restaurant you want to order from (1, 2, 3): ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= restaurants.length) {
                    Restaurant selectedRestaurant = restaurants[choice - 1];
                    System.out.println("You've selected " + selectedRestaurant.getName() + ".");
                    selectedRestaurant.displayMenu();

                    // Task 2: Implement a simple shopping cart
                    List<String> shoppingCart = new ArrayList<>();
                    double totalCost = 0.0;

                    while (true) {
                        System.out.println("Select an item to add to your cart (or enter 'done' to place your order):");
                        String input = scanner.next();

                        if (input.equalsIgnoreCase("done")) {
                            break;
                        } else if (selectedRestaurant.getMenu().containsKey(input)) {
                            shoppingCart.add(input);
                            totalCost += selectedRestaurant.getMenu().get(input);
                            System.out.println(input + " added to your cart.");
                        } else {
                            System.out.println("Invalid item. Please select a valid item from the menu.");
                        }
                    }

                    // Task 3: Allow customers to proceed with placing an order
                    System.out.println("Your shopping cart:");
                    for (String item : shoppingCart) {
                        System.out.println(item);
                    }
                    System.out.println("Total cost: $" + String.format("%.2f", totalCost));

                    System.out.print("Would you like to place the order? (yes/no): ");
                    String orderConfirmation = scanner.next();
                    if (orderConfirmation.equalsIgnoreCase("yes")) {
                        System.out.println("Thank you for your order! Your food will be delivered shortly.");
                    } else {
                        System.out.println("Order canceled.");
                    }
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter a valid number.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Consume the invalid input
            }
        }
    }
}
