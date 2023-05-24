public class Normal extends Customer implements CustomerProp{
    Normal(String name, String passwd){
        super(name, passwd);
    }

    public int getMemdiscount(){
        return 0;
    }

    public int getDeliveryPer(){
        return 5;
    }

    public int getDeliveryMinDay(){
        return 7;
    }

    public int getDeliveryMaxDay(){
        return 10;
    }

    public int getMinCouponCnt(){
        return 0;
    }

    public int getMaxCouponCnt(){
        return 0;
    }

    String Type() {
        return "NORMAL";
    }
}