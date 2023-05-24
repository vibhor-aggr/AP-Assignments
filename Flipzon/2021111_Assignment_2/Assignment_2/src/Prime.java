public class Prime extends Customer implements CustomerProp{
    Prime(Customer c){
        super(c);
    }

    public int getMemdiscount(){
        return 5;
    }

    public int getDeliveryPer(){
        return 2;
    }

    public int getDeliveryMinDay(){
        return 3;
    }

    public int getDeliveryMaxDay(){
        return 6;
    }

    public int getMinCouponCnt(){
        return 1;
    }

    public int getMaxCouponCnt(){
        return 2;
    }

    String Type() {
        return "PRIME";
    }
}
