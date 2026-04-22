import java.util.*;

// Product Interface
interface Product {
    void displayDetails();
}

// LegacyItem class
class LegacyItem {
    private String itemId;
    private String description;

    public LegacyItem(String itemId, String description) {
        this.itemId = itemId;
        this.description = description;
    }

    public void print() {
        System.out.println("Legacy Item -> ID: " + itemId + ", Description: " + description);
    }
}

// Adapter Class
class ProductAdapter implements Product {
    private LegacyItem legacyItem;

    public ProductAdapter(LegacyItem legacyItem) {
        this.legacyItem = legacyItem;
    }

    @Override
    public void displayDetails() {
        legacyItem.print();
    }
}

// NewProduct class
class NewProduct implements Product {
    private String name;

    public NewProduct(String name) {
        this.name = name;
    }

    @Override
    public void displayDetails() {
        System.out.println("New Product -> Name: " + name);
    }
}

// Singleton InventoryManager
class InventoryManager {
    private static InventoryManager instance;
    private List<Product> productList;

    private InventoryManager() {
        productList = new ArrayList<>();
    }

    public static synchronized InventoryManager getInstance() {
        if (instance == null) {
            instance = new InventoryManager();
        }
        return instance;
    }

    public void addProduct(Product product) {
        if (product != null) {
            productList.add(product);
        }
    }

    public Iterator<Product> returnInventory() {
        return productList.iterator();
    }
}

// Main class
public class InventorySystem {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        InventoryManager manager = InventoryManager.getInstance();

        int choice = 0;

        do {
            System.out.println("\n===== Inventory Menu =====");
            System.out.println("1. Add New Product");
            System.out.println("2. Add Legacy Product");
            System.out.println("3. Display Inventory");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            try {
                choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {

                    case 1:
                        System.out.print("Enter product name: ");
                        String name = sc.nextLine().trim();

                        if (!name.isEmpty()) {
                            manager.addProduct(new NewProduct(name));
                            System.out.println("New Product Added Successfully!");
                        } else {
                            System.out.println("Product name cannot be empty.");
                        }
                        break;

                    case 2:
                        System.out.print("Enter legacy item ID: ");
                        String id = sc.nextLine().trim();

                        System.out.print("Enter description: ");
                        String desc = sc.nextLine().trim();

                        if (!id.isEmpty() && !desc.isEmpty()) {
                            LegacyItem item = new LegacyItem(id, desc);
                            manager.addProduct(new ProductAdapter(item));
                            System.out.println("Legacy Product Added Successfully!");
                        } else {
                            System.out.println("ID and Description cannot be empty.");
                        }
                        break;

                    case 3:
                        System.out.println("\n--- Inventory List ---");

                        Iterator<Product> iterator = manager.returnInventory();

                        if (!iterator.hasNext()) {
                            System.out.println("Inventory is empty.");
                        } else {
                            while (iterator.hasNext()) {
                                Product product = iterator.next();
                                product.displayDetails();
                            }
                        }
                        break;

                    case 4:
                        System.out.println("Exiting program...");
                        break;

                    default:
                        System.out.println("Invalid choice! Try again.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine(); // clear invalid input
            }

        } while (choice != 4);

        sc.close();
    }
}
