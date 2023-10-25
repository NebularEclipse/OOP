import java.util.Scanner;

public class Salabat {
  public static void main(String args[]) {
    HondaMotorcycle a[] = new HondaMotorcycle[31];
    
    a[0] = new HondaMotorcycle("CRF125F", 189000, "Off-road Competition Bike", 1);
    a[1] = new HondaMotorcycle("CRF150R", 306000, "Off-road Competition Bike", 1);
    a[2] = new HondaMotorcycle("CRF250R", 459000, "Off-road Competition Bike", 1);
    a[3] = new HondaMotorcycle("CRF250RX", 469000, "Off-road Competition Bike", 1);
    a[4] = new HondaMotorcycle("CRF450R", 500000, "Off-road Competition Bike", 1);
    
    a[5] = new HondaMotorcycle("XR150L", 96900, "Off-road Sports", 2);
    a[6] = new HondaMotorcycle("CRF150L", 147900, "Off-road Sports", 2);
    a[7] = new HondaMotorcycle("CRF300L", 269900, "Off-road Sports", 2);
    a[8] = new HondaMotorcycle("CRF300 Rally", 309900, "Off-road Sports", 2);
    
    a[9] = new HondaMotorcycle("DIO", 62400, "Scooter", 4);
    a[10] = new HondaMotorcycle("BeAT (Playful)", 71400, "Scooter", 4);
    a[11] = new HondaMotorcycle("BeAT (Premium)", 72400, "Scooter", 4);
    a[12] = new HondaMotorcycle("BeAT (Limited Edition)", 74400, "Scooter", 4);
    a[13] = new HondaMotorcycle("CLICK125", 80900, "Scooter", 4);
    a[14] = new HondaMotorcycle("CLICK125 (Limited Edition)", 83900, "Scooter", 4);
    a[15] = new HondaMotorcycle("CLICK160", 122900, "Scooter", 4);
    a[16] = new HondaMotorcycle("AirBlade160", 125900, "Scooter", 4);
    a[17] = new HondaMotorcycle("PCX160-CBS", 131900, "Scooter", 4);
    a[18] = new HondaMotorcycle("PCS160-ABS", 149900, "Scooter", 4);
    a[19] = new HondaMotorcycle("ADV160", 166900, "Scooter", 4);
    
    a[20] = new HondaMotorcycle("CB150X", 173900, "On Road Sports", 2);
    a[21] = new HondaMotorcycle("CBR150R", 183900, "On Road Sports", 2);
    
    a[22] = new HondaMotorcycle("Wave RSX (Drum)", 62900, "Underbone", 4);
    a[23] = new HondaMotorcycle("Wave RSX (DISC)", 64900, "Underbone", 4);
    a[24] = new HondaMotorcycle("XRM125 DS", 71900, "Underbone", 4);
    a[25] = new HondaMotorcycle("XRM125 DSX", 74900, "Underbone", 4);
    a[26] = new HondaMotorcycle("XRM125 MOTARD", 76900, "Underbone", 4);
    a[27] = new HondaMotorcycle("RS125", 75900, "Underbone", 4);
    a[28] = new HondaMotorcycle("Supra GTR150", 106900, "Underbone", 4);
    
    a[29] = new HondaMotorcycle("TMX125 Alpha", 56900, "Business", 4);
    a[30] = new HondaMotorcycle("TMX SUPREMO", 78900, "Business", 4);
    
    Scanner scanner = new Scanner(System.in);
    
    System.out.println("***************************************");
    System.out.println("* Honda Motorcycle Dealership Program *");
    System.out.println("***************************************");
    System.out.println();
    
    while (true) {
      int choice = menu(scanner);
      switch (choice) {
        case 0:
          System.out.println("Model, Price, Category");
          list(a);
          System.out.println();
          break;
        case 1:
          System.out.println("[0] Search by Model");
          System.out.println("[1] Search Price Range");
          System.out.println("[2] Search by Category");
          System.out.println("[3] Cancel");
          System.out.print("Choice: ");
          int x = scanner.nextInt();
          scanner.nextLine();
          System.out.println();
          if (x == 0) {
            System.out.print("Enter model: ");
            String model = scanner.nextLine();
            System.out.println();
            int h = searchModel(model, a);
            if (h < 0) {
            	System.out.println("Model not found.");
                System.out.println();
                break;
            }
            System.out.println("Model, Price, Category, Available");
            System.out.println(a[h].getModel() + ", ₱" + a[h].getPrice() + ", " + a[h].getType() + ", " + a[h].getAvailability());
            System.out.println();
          } else if (x == 1) {
            System.out.print("Enter price floor: ");
            int min = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter price ceiling: ");
            int max = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            HondaMotorcycle m[] = searchPriceRange(min, max, a);
            if (m.length == 0) {
            	System.out.println("No models of that price range is available.");
                System.out.println();
                break;
            }
            System.out.println("Model, Price, Category");
            list(m);
            System.out.println();
          } else if (x == 2) {
            System.out.println("Categories: Off-road Competition Bike, Off-road Sports, Scooter, On Road Sports, Underbone, Business");
            System.out.print("Enter category: ");
            String model = scanner.nextLine();
            System.out.println();
            HondaMotorcycle h[] = searchType(model, a);
            if (h.length <= 0) {
              System.out.println("Nothing of that type is available.");
              break;
            }
            System.out.println("Model, Price, Category");
            list(h);
            System.out.println();
          }
          break;
        case 2:
          System.out.print("Model: ");
          String s = scanner.nextLine();
          System.out.println();
          buy(s, a);
          System.out.println();
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
    System.out.println();
    if (choice < 0 || choice > 3) {
      System.out.println("Invalid choice!");
      System.out.println();
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
  
  public static int searchModel(String model, HondaMotorcycle m[]) {
    for (int i = 0; i < m.length; i++) {    
      if (m[i].getModel().equals(model)){      
        return i;
      }
    }
    return -1;
  }
  
  public static HondaMotorcycle[] searchPriceRange(int min, int max, HondaMotorcycle m[]) {
    int count = 0;
    for (int i = 0; i < m.length; i++) {
      if (m[i].getPrice() >= min && m[i].getPrice() <= max) {
        count++; 
      }
    }
    
    HondaMotorcycle n[] = new HondaMotorcycle[count];
    for (int i = 0; i < m.length; i++) {
      if (m[i].getPrice() <= max && m[i].getPrice() >= min) {
        n[count - 1] = m[i];
        count--;
      }
    }
    return n;
  }
  
  public static HondaMotorcycle[] searchType(String category, HondaMotorcycle m[]) {   
    int count = 0;
    for (int i = 0; i < m.length; i++) {
      if (m[i].getType().equals(category)) {
        count++;
      }
    }
    HondaMotorcycle n[] = new HondaMotorcycle[count];
    for (int i = 0; i < m.length; i++) {
      if (m[i].getType().equals(category)) {
        n[count - 1] = m[i];
        count--;
      }
    }
    return n;
  }
  public static void buy(String model, HondaMotorcycle m[]) {
    for (int i = 0; i < m.length; i++) {
      if (m[i].getModel().equals(model)){
        if (m[i].getAvailability()) {
          m[i].setStock(m[i].getStock() - 1);
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
