public class Elite extends Customer implements CustomerProp{
    Elite(Customer c){
        super(c);
    }

    public int getMemdiscount(){
        return 10;
    }

    public int getDeliveryPer(){
        return 0;
    }

    public int getDeliveryMinDay(){
        return 1;
    }

    public int getDeliveryMaxDay(){
        return 2;
    }

    public int getMinCouponCnt(){
        return 3;
    }

    public int getMaxCouponCnt(){
        return 4;
    }

    String Type() {
        return "ELITE";
    }
}