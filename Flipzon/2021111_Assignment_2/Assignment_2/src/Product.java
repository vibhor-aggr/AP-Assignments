public class Product {
    private final String name;
    private final String id;
    private String detail;
    private int qty;
    private int[] discount;
    private int price;
    private final int cid;

    Product(int cid, String name, String id, String detail, int price, int qty){
        this.name=name;
        this.id=id;
        this.detail=detail;
        this.price=price;
        this.qty=qty;
        this.cid=cid;
        this.discount=new int[3];
    }

    int getPrice(){
        return price;
    }

    int getQty(){
        return qty;
    }

    int getDiscount(Customer c){
        if(c instanceof Normal)
            return discount[0];
        else if(c instanceof Prime)
            return discount[1];
        else{
            return discount[2];
        }
    }

    void setQty(int qty){
        this.qty=qty;
    }

    void setDiscount(int nd, int pd, int ed){
        discount[0]=nd;
        discount[1]=pd;
        discount[2]=ed;
    }

    public String toString(){
        return "Product Name: "+name+"\nProduct ID: "+id+"\nProduct Details: "+detail+"\nPrice: "+price+"\n";
    }

    public String toString(String label){
        return label + " Name: "+name+"\n"+label+" ID: "+id+"\n"+label+" Details: "+detail+"\n";
    }
}