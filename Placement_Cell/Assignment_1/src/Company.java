import java.util.Date;

public class Company{
    private final String name;
    private String role;
    private int package_offer;
    private double cgpa;
    private Date reg_date;
    private Student[] selected;
    Student[] reg;

    Company(String name, String role, int package_offer, double cgpa){
        this.name=name;
        this.role=role;
        this.package_offer=package_offer;
        this.cgpa=cgpa;
    }

    String getName(){
        return this.name;
    }
    
    String getRole(){
        return this.role;
    }

    int getPackage_offer(){
        return this.package_offer;
    }

    double getCgpa(){
        return this.cgpa;
    }

    Student[] getSelected(){
        return this.selected;
    }

    void regInstituteDrive(Placement p, Date date){
        this.reg_date=date;
        if(p.regCompany(this, date)){
           System.out.println("Registered!!!"); 
        }
        else{
            System.out.println("Cannot be registered.");
        }
    }

    void getSelected(Placement p){
        if(this.selected==null)
            System.out.println("No selected students");
        else{
            System.out.println("Following students are selected by "+this.name+" :");
            for(int i=0;i<this.selected.length;i++){
                System.out.println(this.selected[i].toString());
            }
        }
    }

    void updateRole(String role){
        this.role=role;
    }

    void update_package(int package_offer){
        this.package_offer=package_offer;
    }

    void update_cgpa(double cgpa){
        this.cgpa=cgpa;
    }

    void removeTheSelectedStudent(Student s){
        if (this.selected == null) return;

        int index = -1;
        for (int i = 0; i < this.selected.length; i++) {
            if (this.selected[i] == s) {
                index = i;
                break;
            }
        }

        if (this.selected == null || index < 0 || index >= this.selected.length) {
            return;
        }
 
        Student[] anotherArray = new Student[this.selected.length - 1];
 
        System.arraycopy(this.selected, 0, anotherArray, 0, index);
 
        System.arraycopy(this.selected, index + 1, anotherArray, index, this.selected.length - index - 1);

        this.selected = anotherArray;
    }

    void addTheSelectedStudent(Student s)
    {
        int n = 0;
        if (this.selected != null) {
            n = this.selected.length;
        }
        Student newarr[] = new Student[n + 1];
        for (int i = 0; i < n; i++)
            newarr[i] = this.selected[i];
 
        newarr[n] = s;
        this.selected = newarr;
    }

    public String toString(){
        return "Name: "+this.name+"\nRole: "+this.role+"\nPackage(in LPA): "+this.package_offer+"\nMinimum CGPA: "+this.cgpa;
    }
}