import java.util.Date;

public class Student{
    private String name;
    private int roll_no;
    private double cgpa;
    private String branch;
    private String status;
    private Date reg;
    private Company offer_best;
    Company[] applied;
    private Company[] offers;
    private String acceptance;

    Student(String name, int roll_no, double cgpa, String branch){
        this.name=name;
        this.roll_no=roll_no;
        this.cgpa=cgpa;
        this.branch=branch;
        this.status="unoffered";
        this.acceptance="rejected";
    }
    
    String getName(){
        return this.name;
    }

    int getRoll_no(){
        return this.roll_no;
    }

    double getCgpa(){
        return this.cgpa;
    }

    String getStatus(){
        return this.status;
    }

    Company getOffer_best(){
        return this.offer_best;
    }

    Company[] getApplied(){
        return this.applied;
    }

    Company[] getOffers(){
        return this.offers;
    }

    String getAcceptance(){
        return this.acceptance;
    }

    void setCgpa(double cgpa){
        this.cgpa=cgpa;
    }

    boolean regPlacement(Placement p, Date reg){
        this.reg=reg;
        return p.regStudent(this, reg);
    }

    void regCompany(String comp, Placement p){
        if(this.status.equals("blocked"))
            return;
        p.addStudent(this, comp);
    }

    void getCompany(Placement p){
        if(this.status.equals("blocked")){
            System.out.println("Unavailable");
            return;
        }
        int count=0;
        for(int i=0; i<p.getReg_company().length; i++){
            if(p.getReg_company()[i].getCgpa()<=this.cgpa &&
             (this.status.equals("unoffered") || (this.status.equals("offered") && 
             !this.acceptance.equals("rejected") && 
             p.getReg_company()[i].getPackage_offer()>=3*this.getOffer_best().getPackage_offer()))){
                count++;
                if (count == 1) {
                    System.out.println("List of All available companies is as follows:\n");
                }
                System.out.println(count+")"+p.getReg_company()[i]);
                System.out.print("\n");
             }
        }
        if (count == 0) {
            System.out.println("No company available");
        }
    }


    void updateCGPA(Placement p, double old_cgpa, double new_cgpa){
        p.updateInfo(this, old_cgpa, new_cgpa);
    }

    void getCur(){
        if(this.status.equals("blocked"))
            System.out.println("You have been blocked.");
        else if(this.status.equals("unoffered") && this.applied == null)
            System.out.println("You have no offers.");
        else if(this.status.equals("unoffered") && this.applied != null) {
            int maxPackage = -1;
            Company best = null;
            for (int i = 0; i < this.applied.length; i++) {
                if (this.applied[i].getPackage_offer() > maxPackage) {
                    best = this.applied[i];
                    maxPackage = this.applied[i].getPackage_offer();
                }
            }
            System.out.println("You have been offered by "+best.getName()+"!! Please accept the offer.");
            System.out.println(best);
        } else if(this.status.equals("offered")){
            System.out.println("You have been offered by "+offer_best.getName());
            System.out.println(offer_best);
        }
    }

    void accept(boolean flag){
        if (this.applied == null) return;
       
        if(flag){                 //check offer_best not null
            int maxPackage = -1;
            Company prev_offer_best = this.offer_best;
            for (int i = 0; i < this.applied.length; i++) {
                if (this.applied[i].getPackage_offer() > maxPackage) {
                    this.offer_best = this.applied[i];
                    maxPackage = this.applied[i].getPackage_offer();
                }
            }
            if (prev_offer_best != null && prev_offer_best != this.offer_best) {
                prev_offer_best.removeTheSelectedStudent(this);
            }
            if (prev_offer_best != this.offer_best) {
                this.offer_best.addTheSelectedStudent(this);
            }
            this.acceptance="accepted";
            this.status="offered";
            System.out.println("Congratulations "+this.name+"!!! You have accepted the offer by "+offer_best.getName()+"!!");
        }
        else{
            this.acceptance="rejected";
            this.status="blocked";
            if (this.offer_best != null) {
                this.offer_best.removeTheSelectedStudent(this);
            }
            System.out.println("You have rejected the offer by "+offer_best.getName());
        }
    }

    public String toString(){
        return "Name: "+this.name+"\nRollNo: "+this.roll_no+"\nCGPA: "+this.cgpa+"\nBranch: "+this.branch;
    }
}

