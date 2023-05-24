public class Item {
    private String pid1;
    private String pid2;
    private int qty;

    Item(String pid1, String pid2, int qty) {
        this.pid1=pid1;
        this.pid2=pid2;
        this.qty=qty;
    }

    String getPid1(){
        return pid1;
    }

    String getPid2(){
        return pid2;
    }

    int getQty(){
        return qty;
    }

    void setQty(int qty){
        this.qty=qty;
    }

    boolean isGiveaway(){
        return pid2!=null;
    }
}
