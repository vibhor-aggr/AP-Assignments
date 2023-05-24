import java.util.Scanner;

public class App {
    static Flipzon website;
    static Scanner sc;
    public static void main(String[] args) throws Exception {
        website=new Flipzon("Beff Jezos", "abc123");
        sc=new Scanner(System.in);
        System.out.println("WELCOME TO FLIPZON\n");
        main_menu();
        sc.close();
    }        

    static void main_menu()
    {
        System.out.println("Please choose any one of the following actions:\n"+
        "1) Enter as Admin\n"+
        "2) Explore the Product Catalog\n"+
        "3) Show Available Deals\n"+
        "4) Enter as Customer\n"+ 
        "5) Exit the Application\n");
        int mode=sc.nextInt();
        sc.nextLine();
        if(mode==1){
            System.out.println("\nDear Admin,\n\nPlease enter your username and password\n");
            String op1=sc.nextLine();
            String op2=sc.nextLine();
            if(website.isAdmin(op1, op2)==null){
                System.out.println("\nInvalid Username or Password\n");
            } else {
                System.out.println("\nWelcome Beff!!!!!\n");
                admin_menu();
            }
        } else if (mode==2){
            System.out.println("");
            website.exploreProduct();
        } else if (mode==3){
            System.out.println("");
            website.availableDeal();
        } else if(mode==4){
            System.out.println("");
            customer_login_menu();
        } else if(mode==5){
            System.out.println("");
            return;
        } else {
            System.out.println("\nInvalid Option\n");
        }
        main_menu();
    }
 
    static void admin_menu()
    {
        System.out.println("Please choose any one of the following actions:\n"+
        "1) Add category\n"+
        "2) Delete category\n"+
        "3) Add Product\n"+
        "4) Delete Product\n"+
        "5) Set Discount on Product\n"+
        "6) Add giveaway deal\n"+
        "7) Back\n");

        int mode=sc.nextInt();
        sc.nextLine();
        if(mode==1){
            System.out.println("\nAdd category ID\n");
            int c = sc.nextInt();
            sc.nextLine();
            System.out.println("\nAdd name of the category\n");
            String cs = sc.nextLine();
            boolean added = website.addCategory(c, cs);
            if (added == false) {
                System.out.println("\nDear Admin, the category ID is already used!!! Please set a different and a unique category ID\n");
            } else {
                // add product for category as empty category is not possible
                System.out.println("\nAdd a Product\n");
                System.out.print("Product ID: ");
                String pid = sc.next();
                sc.nextLine();
                System.out.print("Product Name: ");
                String pname = sc.nextLine();
                System.out.print("Details: ");
                String details = sc.nextLine();
                System.out.print("Price: Rs ");
                int price = sc.nextInt();
                sc.nextLine();
                System.out.print("Quantity: ");
                int qty = sc.nextInt();
                sc.nextLine();
                added = website.addProduct(c, pid, pname, details, price, qty);
                if (added == false) {
                    website.delCategory(c);
                    System.out.println("\nDear Admin, the product ID is already used!!! Please set a different and a unique product ID\n");
                } else {
                    System.out.println("");
                }
            }
        } else if (mode == 2) {
            System.out.println("\nEnter category ID\n");
            int c = sc.nextInt();
            sc.nextLine();
            boolean deleted = website.delCategory(c);
            if (deleted == false) {
                System.out.println("\nDear Admin, the category ID is invalid!!! Please specify a valid category ID\n");
            } else {
                System.out.println("");
            }
        } else if (mode == 3) {
            System.out.println("\nEnter category ID\n");
            int c = sc.nextInt();
            sc.nextLine();
            System.out.println("\nAdd a Product\n");
            System.out.print("Product ID: ");
            String pid = sc.next();
            sc.nextLine();
            System.out.print("Product Name: ");
            String pname = sc.nextLine();
            System.out.print("Details: ");
            String details = sc.nextLine();
            System.out.print("Price: Rs ");
            int price = sc.nextInt();
            sc.nextLine();
            System.out.print("Quantity: ");
            int qty = sc.nextInt();
            sc.nextLine();
            boolean added = website.addProduct(c, pid, pname, details, price, qty);
            if (added == false) {
                System.out.println("\nDear Admin, either the category ID is invalid or product ID is already used!!!\n");
            } else {
                System.out.println("");
            }
        } else if (mode == 4) {
            System.out.println("\nEnter category ID\n");
            int c = sc.nextInt();
            sc.nextLine();
            System.out.println("\nEnter Product ID\n");
            String pid = sc.next();
            sc.nextLine();
            boolean deleted = website.delProduct(c, pid);
            if (deleted == false) {
                System.out.println("Dear Admin, the category or product ID is invalid!!! Please specify a valid ID\n");
            } else {
                System.out.println("");
            }
        } else if (mode == 5) {
            System.out.println("\nEnter Product ID\n");
            String pid = sc.next();
            sc.nextLine();
            System.out.println("\nEnter discount for Elite, Prime and Normal customers respectively (in % terms)\n");
            int ed = sc.nextInt();
            int pd = sc.nextInt();
            int nd = sc.nextInt();
            sc.nextLine();
            boolean updated = website.setDiscount(pid, nd, pd, ed);
            if (updated == false) {
                System.out.println("\nDear Admin, the product ID is invalid!!! Please specify a valid product ID\n");
            } else {
                System.out.println("");
            }
        } else if (mode == 6) {
            System.out.println("\nEnter the first Product ID\n");
            String pid1 = sc.next();
            sc.nextLine();
            System.out.println("\nEnter the second Product ID\n");
            String pid2 = sc.next();
            sc.nextLine();
            System.out.println("\nEnter the combined price for Elite, Prime and Normal customers respectively (Should be less than their combined price)\n");
            int ep = sc.nextInt();
            int pp = sc.nextInt();
            int np = sc.nextInt();
            sc.nextLine();
            boolean added = website.addDeal(pid1, pid2, np, pp, ep);
            if (added == false) {
                System.out.println("\nDear Admin, either the product IDs or combined price is invalid!!! Please specify valid details\n");
            } else {
                System.out.println("");
            }      
        } else if (mode == 7) {
            System.out.println("");
            return;
        } else {
            System.out.println("\nInvalid Option\n");
        }
        admin_menu();
    }

    static void customer_login_menu() {

        System.out.println("Please choose any one of the following actions:\n"+
        "1) Sign up\n"+
        "2) Log in\n"+
        "3) Back\n");

        int mode=sc.nextInt();
        sc.nextLine();
        if(mode==1){
            System.out.println("\nEnter name\n");
            String n = sc.nextLine();
            System.out.println("\nEnter password\n");
            String p = sc.nextLine();
            boolean added = website.addUser(n, p);
            if (added == false) {
                System.out.println("\nDear Customer, the username is already used!!! Please specify a different and a unique username\n");
            } else {
                System.out.println("\ncustomer successfully registered!!\n");
            }
        } else if (mode == 2) {
            System.out.println("\nEnter name\n");
            String n = sc.nextLine();
            System.out.println("\nEnter password\n");
            String p = sc.nextLine();
            Customer c = website.loginUser(n, p);
            if (c == null) {
                System.out.println("\nInvalid Username or Password\n");
            } else {
                System.out.println("\nWelcome " + c.getName() + " !!\n");
                customer_menu(c);
            }
        } else if (mode == 3) {
            System.out.println("");
            return;
        } else {
            System.out.println("\nInvalid Option\n");
        }
        customer_login_menu();
    }

    static void customer_menu(Customer c) {
        System.out.println("Please choose any one of the following actions:\n"+
        "1)  Browse products\n"+
        "2)  Browse deals\n"+
        "3)  Add a product to cart\n"+
        "4)  Add products in deal to cart\n"+
        "5)  View coupons\n"+
        "6)  Check account balance\n"+
        "7)  View cart\n"+
        "8)  Empty cart\n"+
        "9)  Checkout cart\n"+
        "10) Upgrade customer status\n"+
        "11) Add amount to wallet\n"+
        "12) Back\n");

        int mode=sc.nextInt();
        sc.nextLine();
        if(mode==1){
            System.out.println("");
            website.exploreProduct(c);
        } else if (mode == 2) {
            System.out.println("");
            website.availableDeal(c);
        } else if (mode == 3) {
            System.out.println("\nEnter product ID and quantity\n");
            String pid = sc.next();
            int qty = sc.nextInt();
            sc.nextLine();
            boolean added = website.addCart(c, pid, qty);
            if (added == false) {
                System.out.println("\nDear Customer, either the product ID is invalid or required quantity is unavailable!!!\n");
            } else {
                System.out.println("\nItem successfully added to cart\n");
            }
        } else if (mode == 4) {
            System.out.println("\nEnter the first Product ID, second Product ID and quantity respectively\n");
            String pid1 = sc.next();
            String pid2 = sc.next();
            int qty = sc.nextInt();
            sc.nextLine();
            boolean added = website.addCart(c, pid1, pid2, qty);
            if (added == false) {
                System.out.println("\nDear Customer, either the product ID(s) is invalid or required quanity is unavailable!!!\n");
            } else {
                System.out.println("\nDeal successfully added to cart\n");
            }
        } else if (mode == 5) {
            System.out.println("");
            c.viewCoupon();
            System.out.println("");
        } else if (mode == 6) {
            System.out.println("\nCurrent account balance is Rupees " + c.getBalance() + "\n");
        } else if (mode == 7) {
            System.out.println("");
            website.viewCart(c);
        } else if (mode == 8) {
            c.emptyCart();
            System.out.println("\nDear Customer, cart successfully emptied\n");
        } else if (mode == 9) {
            System.out.println("");
            boolean success = website.placeOrder(c);
            System.out.println("");
        } else if (mode == 10) {
            System.out.print("\nCurrent status: " + c.Type() + "\n\nChoose new status: ");
            String s = sc.next();
            sc.nextLine();
            Customer nc = website.upgradeCustomer(c, s);
            if (nc == null) {
                System.out.println("\nStatus not updated to " + s + "\n");
            } else {
                System.out.println("\nStatus updated to " + nc.Type() + "\n");
                c = nc;
            }
        } else if (mode == 11) {
            System.out.println("\nEnter amount to add\n");
            double amt = sc.nextDouble();
            sc.nextLine();
            if (amt <= 0) {
                System.out.println("\nInvalid amount\n");
            } else {
                c.addBalance(amt);
                System.out.println("\nAmount successfully added\n");
            }
        } else if (mode == 12) {
            System.out.println("");
            return;
        } else {
            System.out.println("\nInvalid Option\n");
        }
        customer_menu(c);
    }
}