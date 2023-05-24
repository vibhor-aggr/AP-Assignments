public class Giveaway {
    private final String pid1;
    private final String pid2;
    private int nprice;
    private int pprice;
    private int eprice;

    Giveaway(String pid1, String pid2, int nprice, int pprice, int eprice){
        this.pid1=pid1;
        this.pid2=pid2;
        this.nprice=nprice;
        this.pprice=pprice;
        this.eprice=eprice;
    }

    String getPid1(){
        return pid1;
    }

    String getPid2(){
        return pid2;
    }

    int getNprice(){
        return nprice;
    }

    int getPprice(){
        return pprice;
    }

    int getEprice(){
        return eprice;
    }

    int getPrice(Customer c){
        if(c instanceof Normal)
            return nprice;
        else if(c instanceof Prime)
            return pprice;
        else{
            return eprice;
        }
    }

    void updatePrice(int np, int pp, int ep){
        this.nprice=np;
        this.pprice=pp;
        this.eprice=ep;
    }
}
