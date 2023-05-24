import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class Flipzon {
    private Admin admin;
    private List<Customer> u;
    private Map<String, Product> pmap;
    private Map<Integer, Category> cmap;
    private List<Giveaway> give;

    Flipzon(String admin_name, String admin_pd){
        admin=new Admin(admin_name, admin_pd);
        u=new LinkedList<Customer>();
        pmap=new HashMap<String, Product>();
        cmap=new HashMap<Integer, Category>();
        give=new LinkedList<Giveaway>();
    }

    Admin isAdmin(String name, String passwd){
        if(this.admin.getName().equals(name) && this.admin.getPassword().equals(passwd))
            return admin;
        return null; 
    }

    boolean addUser(String name, String passwd){
        for(Customer c:this.u){
            if(c.getName().equals(name))
                return false;
        }
        u.add(new Normal(name, passwd));
        return true;
    }

    Customer loginUser(String name, String passwd){
        for(Customer c:this.u){
            if(c.getName().equals(name) && c.getPassword().equals(passwd))
                return c;
        }
        return null;
    }

    Product getProduct(String pid){
        return pmap.get(pid);
    }

    boolean isProduct(String pid){
        return pmap.containsKey(pid);
    }

    boolean isGiveaway(String pid1, String pid2){
        return findDeal(pid1, pid2)!=null ? true : false;
    }

    boolean isCategory(int cid){
        return cmap.containsKey(cid);
    }

    boolean setDiscount(String pid, int nd, int pd, int ed){
        if(this.isProduct(pid)){
            this.getProduct(pid).setDiscount(nd, pd, ed);
            return true;
        }
        return false;
    }

    boolean addCategory(int cid, String name){
        if(!this.isCategory(cid)){
            cmap.put(cid, new Category(name, cid));
            return true;
        }
        return false;
    }

    boolean addProduct(int cid, String pid, String name, String details, int price, int quantity){
        if(this.isCategory(cid) && !this.isProduct(pid)){
            pmap.put(pid, new Product(cid, name, pid, details, price, quantity));
            cmap.get(cid).addProduct(pid);
            return true;
        }
        return false;
    }

    boolean addDeal(String pid1, String pid2, int np, int pp, int ep){
        if(!this.isProduct(pid1) || !this.isProduct(pid2))
            return false;

        // check that individual price is > than deal price
        int indprice = pmap.get(pid1).getPrice() + pmap.get(pid2).getPrice();
        int mprice = Math.max(Math.max(np, pp), ep);
        if (mprice >= indprice) {
            return false;
        }
        Giveaway g=findDeal(pid1, pid2);
        if(g==null)
            this.give.add(new Giveaway(pid1, pid2, np, pp, ep));
        else{
            g.updatePrice(np, pp, ep);
        }
        return true;    
    }

    Giveaway findDeal(String pid1, String pid2){
        for(Giveaway g: this.give){
            if(g.getPid1().equals(pid1) && g.getPid2().equals(pid2) || (g.getPid1().equals(pid2) && g.getPid2().equals(pid1)))
                return g;
        }
        return null;
    }

    boolean delCategory(int cid){
        if(this.isCategory(cid)){
            List<String> cpids = new LinkedList<String>(cmap.get(cid).getPids());
            for(String p:cpids)
                delProduct(cid, p);
            cmap.remove(cid);
            return true;
        }
        return false;
    }


    void delDeal(String pid){
        ListIterator<Giveaway> itr = this.give.listIterator();
        while(itr.hasNext()){
            Giveaway g = itr.next();
            if(g.getPid1().equals(pid) || g.getPid2().equals(pid))
                itr.remove();
        }
    }

    boolean delProduct(int cid, String pid){
        if(this.isProduct(pid) && this.isCategory(cid)){
            Category cat=cmap.get(cid);
            if(cat.isProduct(pid)){
                cat.delProduct(pid);
                pmap.remove(pid);
                this.delDeal(pid);
                for(Customer c: u){
                    c.emptyCart(pid);
                }
                if(cat.numProduct()==0)
                    cmap.remove(cid);
                return true;
            }
        }
        return false;
    }
    
    Customer upgradeCustomer(Customer c, String type){ 
        if(!type.equalsIgnoreCase("PRIME") && !type.equalsIgnoreCase("ELITE"))
            return c;
        Customer newc = c;
        if(c instanceof Normal){
            if(type.equalsIgnoreCase("PRIME") && c.getBalance()>=200){
                newc=new Prime(c);
                newc.setBalance(newc.getBalance()-200);
            }
            if(type.equalsIgnoreCase("ELITE") && c.getBalance()>=300){
                newc=new Elite(c);
                newc.setBalance(newc.getBalance()-300);
            }
        }
        if(c instanceof Prime){
            if(type.equalsIgnoreCase("ELITE") && c.getBalance()>=100){
                newc=new Elite(c);
                newc.setBalance(newc.getBalance()-100);
            }
        }
        if (newc != c){
            u.remove(c);
            u.add(newc);
        }
        return newc;
    }

    boolean addCart(Customer c, String pid, int qty){
        if(!this.isProduct(pid) || this.pmap.get(pid).getQty()<qty)
            return false;
        c.addCart(pid, qty);
        
        return true;
    }

    boolean addCart(Customer c, String pid1, String pid2, int qty){
        if(!this.isGiveaway(pid1, pid2) || this.pmap.get(pid1).getQty()<qty || this.pmap.get(pid2).getQty()<qty)
            return false;
        c.addCart(pid1, pid2, qty);
        
        
        return true;
    }

    void viewCart(Customer c){
        if (c.getCart() == null) {
            System.out.println("Dear Customer, cart is empty!!!\n");
            return;
        }
        for (Item it : c.getCart()) {
            if(it.isGiveaway()){
                System.out.print(pmap.get(it.getPid1()).toString("Product1"));
                System.out.print(pmap.get(it.getPid2()).toString("Product2"));
                System.out.print("Price: "+findDeal(it.getPid1(),it.getPid2()).getPrice(c)+"\n");            
            }
            else{
                System.out.print(pmap.get(it.getPid1()).toString());
            }
            System.out.println("Quantity: "+it.getQty()+"\n");
        }
    }

    // pick max of (product discount, membership discount, coupon max discount) for each product
    boolean placeOrder(Customer c){
        double tprice=0;
        double dprice=0;
        if(c.getCart()==null){
            System.out.println("Cart is empty!! Please try again");
            return false;
        }
        boolean couponUsed=false;
        Map<String,Integer> prodUsed=new HashMap<String,Integer>();
        Map<Item,Double> prodDisc=new HashMap<Item,Double>();
        for(Item it: c.getCart()){
            if(it.isGiveaway()){
                Giveaway g=findDeal(it.getPid1(), it.getPid2());
                if(this.pmap.get(it.getPid1()).getQty()<it.getQty() || this.pmap.get(it.getPid2()).getQty()<it.getQty()){
                    System.out.println("Insufficient quantity!! Please try again");
                    return false;
                }
                int prevQty=0;
                if(prodUsed.containsKey(it.getPid1()))
                    prevQty=prodUsed.get(it.getPid1());
                prodUsed.put(it.getPid1(), prevQty+it.getQty());
                prevQty=0;
                if(prodUsed.containsKey(it.getPid2()))
                    prevQty=prodUsed.get(it.getPid2());
                prodUsed.put(it.getPid2(), prevQty+it.getQty());
                tprice+=g.getPrice(c)*it.getQty();
                dprice+=g.getPrice(c)*it.getQty();
            }
            else if(isProduct(it.getPid1())){
                
                if(this.pmap.get(it.getPid1()).getQty()<it.getQty()){
                    System.out.println("Insufficient quantity!! Please try again");
                    return false;
                }
                int prevQty=0;
                if(prodUsed.containsKey(it.getPid1()))
                    prevQty=prodUsed.get(it.getPid1());
                prodUsed.put(it.getPid1(), prevQty+it.getQty());
                double disc=0;
                if(c.maxCoupon()>Math.max(pmap.get(it.getPid1()).getDiscount(c), c.getMemdiscount())){
                    disc=c.maxCoupon();
                    couponUsed=true;
                }
                else
                    disc=Math.max(pmap.get(it.getPid1()).getDiscount(c), c.getMemdiscount());
                prodDisc.put(it, disc);
                tprice+=pmap.get(it.getPid1()).getPrice()*it.getQty();
                dprice+=(1-disc/100)*pmap.get(it.getPid1()).getPrice()*it.getQty();
            }
            else{
                System.out.println("Product in cart does not exist!! Please try again");
                return false;
            }
        }
        for (Map.Entry<String,Integer> pu : prodUsed.entrySet()){  
            if(pmap.get(pu.getKey()).getQty()<pu.getValue()){
                System.out.println("Insufficient quantity!! Please try again");
                return false;
            }
        }
        double checkoutAmt=dprice+100+c.getDeliveryPer()*tprice/100;
        if(checkoutAmt>c.getBalance()){
            System.out.println("Insufficient balance!! Please try again");
            return false;
        }
        c.setBalance(c.getBalance()-checkoutAmt);        
        System.out.println("Your order is placed successfully. Details:\n");
        for(Item it : c.getCart()){
            if(it.isGiveaway()){
                this.pmap.get(it.getPid1()).setQty(this.pmap.get(it.getPid1()).getQty()-it.getQty());
                this.pmap.get(it.getPid2()).setQty(this.pmap.get(it.getPid2()).getQty()-it.getQty());
                System.out.print(pmap.get(it.getPid1()).toString("Product1"));
                System.out.print(pmap.get(it.getPid2()).toString("Product2"));
                System.out.print("Price: "+findDeal(it.getPid1(),it.getPid2()).getPrice(c)+"\n");
                System.out.println("Quantity: "+it.getQty()+"\n");            
            }
            else{
                this.pmap.get(it.getPid1()).setQty(this.pmap.get(it.getPid1()).getQty()-it.getQty());
                System.out.print(pmap.get(it.getPid1()).toString());
                System.out.print("Quantity: "+it.getQty()+"\n");
                System.out.println("Discount: " + prodDisc.get(it) + "% of " + this.pmap.get(it.getPid1()).getPrice()*it.getQty() + 
                                    " = " + this.pmap.get(it.getPid1()).getPrice()*it.getQty()*prodDisc.get(it)/100.0 + "\n");
            }
        }
        System.out.println("Delivery charges: Rs 100 + " + c.getDeliveryPer() + "% of " + tprice + 
                            " = 100 + " + c.getDeliveryPer()*tprice/100 + " = Rs " + (100 + c.getDeliveryPer()*tprice/100) + "\n");
        System.out.println("Total Price = Rs " + checkoutAmt + "\n");
        System.out.println("Order placed. It will be delivered in " + c.getDeliveryMinDay() + "-" + c.getDeliveryMaxDay() + " days.\n");

        if(couponUsed){
            c.removeCoupon();
        }
        c.emptyCart();
        c.genCoupon();
        return true;
    }

    void exploreProduct(){
        if(pmap.size()==0){
            System.out.println("Dear User, there are no products for now!!! Please check regularly for products.");
            return;
        }
        for (Map.Entry<Integer, Category> cat : cmap.entrySet()){  
            System.out.println("Category ID: " + cat.getKey() + "\nCategory Name: " + cat.getValue().getName());
            for(String pid : cat.getValue().getPids()){
                System.out.println(pmap.get(pid).toString());
            }
        }         
    }

    void exploreProduct(Customer c){ 
        if(pmap.size()==0){
            System.out.println("Dear User, there are no products for now!!! Please check regularly for products.");
            return;
        }
        for (Map.Entry<Integer, Category> cat : cmap.entrySet()){  
            System.out.println("Category ID: " + cat.getKey() + "\nCategory Name: " + cat.getValue().getName());
            for(String pid : cat.getValue().getPids()){
                System.out.println(pmap.get(pid).toString());
            }
        }
    } 

    void availableDeal(){
        if(give.size()==0){
            System.out.println("Dear User, there are no deals for now!!! Please check regularly for exciting deals.\n");
            return;
        }
        for(Giveaway deal : give){
            System.out.print(pmap.get(deal.getPid1()).toString("Product1"));
            System.out.print(pmap.get(deal.getPid2()).toString("Product2"));
            System.out.println("Normal Price: "+deal.getNprice()+"\nPrime Price: "+deal.getPprice()+"\nElite Price: "+deal.getEprice()+"\n");            
        }
    }
    void availableDeal(Customer c){ 
        if(give.size()==0){
            System.out.println("Dear User, there are no deals for now!!! Please check regularly for exciting deals.\n");
            return;
        }
        for(Giveaway deal : give){
            System.out.print(pmap.get(deal.getPid1()).toString("Product1"));
            System.out.print(pmap.get(deal.getPid2()).toString("Product2"));
            System.out.println("Price: "+deal.getPrice(c)+"\n");            
        }
    } 
}