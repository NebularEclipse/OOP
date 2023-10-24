import java.util.Scanner;

public class Gcbb{
  public static void main(String args[]) {
    HondaMotorcycle a[] = new HondaMotorcycle[2];
    a[0] = new HondaMotorcycle("Beat", 64000, "Scooter", 1);
    a[1] = new HondaMotorcycle("Click", 72000, "Scooter", 3);
    
    Scanner scanner = new Scanner(System.in);
    
    while (true) {
      int choice = menu(scanner);
      switch (choice) {
        case 0:
          list(a);
          break;
        case 1:
          System.out.println("[0] Search by Model");
          System.out.println("[1] Search Price Range");
          System.out.println("[2] Search by Type");
          System.out.println("[3] Cancel");
          System.out.print("Choice: ");
          int x = scanner.nextInt();
          scanner.nextLine();
          if (x == 0) {
            System.out.print("Enter model: ");
            String model = scanner.nextLine();
            int h = searchModel(model, a);
            if (h < 0) break;
            System.out.println(a[h].getModel() + ", ₱" + a[h].getPrice() + ", " + a[h].getType());
          } else if (x == 1) {
            System.out.print("Enter price floor: ");
            int min = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter price ceiling: ");
            int max = scanner.nextInt();
            scanner.nextLine();
            list(searchPriceRange(min, max, a));
          } else if (x == 2) {
            System.out.print("Enter type: ");
            String model = scanner.nextLine();
            HondaMotorcycle h[] = searchType(model, a);
            if (h.length <= 0) {
              System.out.println("Nothing of that type found has been found.");
              break;
            }
            list(h);
          }
          break;
        case 2:
          System.out.print("Model: ");
          String s = scanner.nextLine();
          buy(s, a);
          break;
        case 3:
          System.out.println("Thank you!");
          return;
      }
    }
  }
  
  public static int menu(Scanner scanner) {
    System.out.println("[0] List all available Motorcycle Models");
    System.out.println("[1] Search for a Motorcycle");
    System.out.println("[2] Buy a Motorcycle");
    System.out.println("[3] Exit");
    System.out.print("Choice: ");
    int choice = scanner.nextInt();
    scanner.nextLine();
    if (choice < 0 || choice > 3) {
      System.out.println("Invalid choice!");
      return menu(scanner);
    }
    return choice;
  }
  
  public static void list(HondaMotorcycle m[]) {
    for (int i = 0; i < m.length; i++) {
      if (m[i].getAvailability()) {
        System.out.println(m[i].getModel() + ", ₱" + m[i].getPrice() + ", " + m[i].getType());
      }
    }
  }
  
  public static int searchModel(String model, HondaMotorcycle motorcycles[]) {
    for (int i = 0; i < motorcycles.length; i++) {    
      if (motorcycles[i].getModel().equals(model)){      
        return i;
      }
    }
    return -1;
  }
  
  public static HondaMotorcycle[] searchPriceRange(int min, int max, HondaMotorcycle motorcycles[]) {
    int count = 0;
    for (int i = 0; i < motorcycles.length; i++) {
      if (motorcycles[i].getPrice() >= min && motorcycles[i].getPrice() <= max) {
        count++; 
      }
    }
    
    HondaMotorcycle m[] = new HondaMotorcycle[count];
    for (int i = 0; i < motorcycles.length; i++) {
      if (motorcycles[i].getPrice() <= max && motorcycles[i].getPrice() >= min) {
        m[count - 1] = motorcycles[i];
        count--;
      }
    }
    return m;
  }
  
  public static HondaMotorcycle[] searchType(String type, HondaMotorcycle m[]) {   
    int count = 0;
    for (int i = 0; i < m.length; i++) {
      if (m[i].getType().equals(type)) {
        count++;
      }
    }
    HondaMotorcycle n[] = new HondaMotorcycle[count];
    for (int i = 0; i < m.length; i++) {
      if (m[i].getType().equals(type)) {
        n[count - 1] = m[i];
        count--;
      }
    }
    return n;
  }
  public static void buy(String model, HondaMotorcycle motorcycles[]) {
    for (int i = 0; i < motorcycles.length; i++) {
      if (motorcycles[i].getModel().equals(model)){
        if (motorcycles[i].getAvailability()) {
          motorcycles[i].setStock(motorcycles[i].getStock() - 1);
          System.out.println("Thank you for your patronage!");
        } else {
          System.out.println("I'm sorry, there's no available stock for that model.");
        }
        return;
      }
    }
    System.out.println(model + " not found!");
  }
}

class HondaMotorcycle {
  private String model;
  private int price;
  private String type;
  private int stock;
  
  // Constructor
  public HondaMotorcycle(String init_model, int init_price, String init_type, int init_stock) {
    this.model = init_model;
    this.price = init_price;
    this.type = init_type;
    this.stock = init_stock;
  }
  
  // Model getter
  public String getModel() {
    return model;
  }
  
  // Price getter
  public int getPrice() {
    return price;
  }
  
  // Type getter
  public String getType() {
    return type;
  }
  
  // Availability getter
  public boolean getAvailability() {
    if (stock > 0) {
      return true;
    } else {
      return false;
    }
  }
  // Stock getter
  public int getStock() {
    return stock;
  }
  
  // Price setter
  public void setPrice(int value) {
    price = value;
  }
  
  // Stock setter
  public void setStock(int value) {
    stock = value;
  }  
}
