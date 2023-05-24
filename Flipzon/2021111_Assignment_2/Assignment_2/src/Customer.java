import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public abstract class Customer {
    private final String name;
    private String password;
    private List<Item> cart; 
    private double balance;
    private List<Integer> coupons;
    protected int memdiscount;
    protected int minCouponCnt;
    protected int maxCouponCnt;
    protected int deliveryPer;
    protected int deliveryMinDay;
    protected int deliveryMaxDay;
    
    Customer(String name, String passwd){
        this.name=name;
        this.password=passwd;
        this.balance=1000;
    }

    Customer(Customer c){
        this.name=c.name;
        this.password=c.password;
        this.balance=c.balance;
        this.coupons=c.coupons;
    }

    String getName(){
        return name;
    }

    String getPassword(){
        return password;
    }

    List<Item> getCart(){
        return cart;
    }

    abstract int getMemdiscount();
    abstract int getDeliveryPer();
    abstract int getDeliveryMinDay();
    abstract int getDeliveryMaxDay();
    abstract int getMinCouponCnt();
    abstract int getMaxCouponCnt();

    void setBalance(double balance){
        this.balance=balance;
    }

    void setMemdiscount(int memdiscount){
        this.memdiscount=memdiscount;
    }

    void setMinCouponCnt(int minCouponCnt){
        this.minCouponCnt=minCouponCnt;
    }

    void setMaxCouponCnt(int maxCouponCnt){
        this.maxCouponCnt=maxCouponCnt;
    }

    void setDeliveryPer(int deliveryPer){
        this.deliveryPer=deliveryPer;
    }

    void setDeliveryMinDay(int deliveryMinDay){
        this.deliveryMinDay=deliveryMinDay;
    }

    void setDeliveryMaxDay(int deliveryMaxDay){
        this.deliveryMaxDay=deliveryMaxDay;
    }

    String Type() {
        return "";
    }

    void addBalance(double newbal){
        balance+=newbal;
    }

    double getBalance(){
        return balance;
    }

    void addCart(String pid, int qty){
        if(cart==null)
            cart=new LinkedList<Item>();
        ListIterator<Item> itr = cart.listIterator();
        while(itr.hasNext()){
            Item it = itr.next();
            if(!it.isGiveaway() && it.getPid1().equals(pid)){
                it.setQty(it.getQty()+qty);
                return;
            }
        }
        cart.add(new Item(pid,null, qty));        
    }

    void addCart(String pid1, String pid2, int qty){
        if(cart==null)
            cart=new LinkedList<Item>();
        ListIterator<Item> itr = cart.listIterator();
        while(itr.hasNext()){
            Item it = itr.next();
            if(!it.isGiveaway())
                continue;
            if(it.getPid1().equals(pid1) && it.getPid2().equals(pid2) || (it.getPid1().equals(pid2) && it.getPid2().equals(pid1))){
                it.setQty(it.getQty()+qty);
                return;
            }
        }
        cart.add(new Item(pid1, pid2, qty));        
    }

    void emptyCart(String pid){
        if(cart==null)
            return;
        ListIterator<Item> itr = cart.listIterator();
        while(itr.hasNext()){
            Item it = itr.next();
            if(it.getPid1().equals(pid) || (it.isGiveaway() && it.getPid2().equals(pid)))
                itr.remove();
        }
    }

    void emptyCart(){
        if(cart==null)
            return;
        this.cart.clear();
        cart=null;
    }

    void genCoupon(){
        Random gen=new Random();
        if(coupons==null)
            coupons=new LinkedList<Integer>();
        int couponCnt=gen.nextInt(getMinCouponCnt(), getMaxCouponCnt()+1);
        if (couponCnt > 0) {
            System.out.println("Congratulations!! You have won " + couponCnt + " coupons: ");
            for(int i=1;i<=couponCnt;i++){
                int c = gen.nextInt(5, 16);
                coupons.add(c);
                System.out.println(i+") "+c+"%");
            }
        }
        Collections.sort(coupons);
        Collections.reverse(coupons);
    }

    void viewCoupon(){
        if(coupons==null || coupons.size() == 0){
            System.out.println("No coupons available");
            return;
        }
        int i=1;
        System.out.println("Following coupons are available: ");
        for(int coupon:coupons){ 
            System.out.println(i+") "+coupon+"%");
            i+=1;
        }
    }
    
    Integer maxCoupon(){
        if(coupons==null || coupons.size() == 0)
            return 0;
        return coupons.get(0);
    }
    
    void removeCoupon(){
        if(coupons==null || coupons.size() == 0)
            return;
        coupons.remove(this.maxCoupon());
        Collections.sort(coupons);
        Collections.reverse(coupons);
    }
}
