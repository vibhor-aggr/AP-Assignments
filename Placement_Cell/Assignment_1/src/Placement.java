import java.util.Date;

public class Placement{
    private Date open_student;
    private Date end_student;
    private Date open_company;
    private Date end_company;
    private Student[] reg_student;
    private Company[] reg_company;

    Company[] getReg_company(){
        return this.reg_company;
    }

    void openStudent(Date open_reg, Date end_reg){
        
        this.open_student=open_reg;
        this.end_student=end_reg;
    }

    void openCompany(Date open_reg, Date end_reg){
        
        this.open_company=open_reg;
        this.end_company=end_reg;
    }

    //include student/comp after deadline in reg_type
    void numStudent(){
        System.out.println("Number of student registrations:\n");
        int c = 0;
        if (this.reg_student != null) {
            c = this.reg_student.length;
        }
        System.out.println(c);
    }

    void numCompany(){
        System.out.println("Number of company registrations:\n");
        int c = 0;
        if (this.reg_company != null) {
            c = this.reg_company.length;
        }
        System.out.println(c);
    }

    //decide inclusion comp print in numoffered
    void numOffered(int flag){
        if(flag==1){
            int offer=0;
            if (this.reg_student != null) {
            for(int i=0;i<this.reg_student.length;i++){
                if(this.reg_student[i].getStatus().equals("offered") && 
                this.reg_student[i].getAcceptance().equals("accepted")){
                    offer+=1;
                }
            }
            }
            System.out.println("Number of offered students: "+offer);
        }
        else if (flag==2){
            int unoffer=0;
            if (this.reg_student != null) {
            for(int i=0;i<this.reg_student.length;i++){
                if(this.reg_student[i].getStatus().equals("unoffered")){
                    unoffer+=1;
                }
            }
            }
            System.out.println("Number of unoffered students: "+unoffer);
        }
        else{
            int block=0;
            if (this.reg_student != null) {
            for(int i=0;i<this.reg_student.length;i++){
                if(this.reg_student[i].getStatus().equals("blocked")){
                    block+=1;
                }
            }
            }
            System.out.println("Number of blocked students: "+block);
        }
    }

    void getStudent(String name, int roll_no){
        if (this.reg_student == null) return;
        for(int i=0;i<this.reg_student.length;i++){
            if(this.reg_student[i].getName().equals(name) &&
            this.reg_student[i].getRoll_no()==roll_no){
                int k=0;
                System.out.println("Applied\n");
                if(this.reg_student[i].getApplied()!=null){
                for(k=0;k<this.reg_student[i].getApplied().length;k++)
                    System.out.println(this.reg_student[i].getApplied()[k]);
                    System.out.print("\n");
                }
                System.out.println("Not applied\n");
                for(int j=0;j<this.reg_company.length;j++){
                    int flag=1;
                    if(this.reg_student[i].getApplied()!=null){
                    for(k=0;k<this.reg_student[i].getApplied().length;k++){
                        if(this.reg_company[j]==this.reg_student[i].getApplied()[k]){
                            flag=0;
                            break;
                        }
                    }
                    }
                    if(flag==1)
                        System.out.println(this.reg_company[j]);
                }
                if(this.reg_student[i].getOffers()!=null){
                for(k=0;k<this.reg_student[i].getOffers().length;k++){
                    System.out.println(this.reg_student[i].getOffers()[k]);
                    System.out.print("\n");
                }
                }
            }
        }
    }

    void getCompany(String name){
        if (this.reg_company == null) return;
        for(int i=0;i<this.reg_company.length;i++){
            if(this.reg_company[i].getName().equals(name)){
                System.out.println(this.reg_company[i]);
                System.out.print("\n");
                if (this.reg_company[i].getSelected() == null) continue;
                for(int j=0;j<this.reg_company[i].getSelected().length;i++){
                    System.out.println("Name "+this.reg_company[i].getSelected()[j].getName());
                    System.out.println("Roll No "+this.reg_company[i].getSelected()[j].getRoll_no());                    
                    System.out.print("\n");
                }
            }
        }
    }

    void getAveragePackage(){
        int numPackage=0;
        double valuePackage=0;
        if (this.reg_student == null) {
            System.out.println("No student offered");
            return;
        }
        for(int i=0;i<this.reg_student.length;i++){
            if(this.reg_student[i].getStatus().equals("offered")){     //s.acceptance include or not
                numPackage+=1;
                valuePackage+=this.reg_student[i].getOffer_best().getPackage_offer();
            }   
        }
        if (numPackage > 0) {
            System.out.println(valuePackage/numPackage);
        } else {
            System.out.println("No student offered"); 
        }
    }

    void getCompanyResult(String name){
        if (this.reg_company == null) return;
        for(int i=0;i<this.reg_company.length;i++){
            if(this.reg_company[i].getName().equals(name)){
                if(this.reg_company[i].getSelected()==null)
                    System.out.println("No selected students by " + name);
                else{
                    System.out.println("Following students are selected by "+name+":\n");
                    for(int j=0;j<this.reg_company[i].getSelected().length;j++){
                        System.out.println(this.reg_company[i].getSelected()[j]);
                        System.out.print("\n");
                    }
                }
            }
        }
    }

    public static Student[] addStudentType(int n, Student arr[], Student x){
       Student newarr[] = new Student[n + 1];
       for (int i = 0; i < n; i++)
           newarr[i] = arr[i];

       newarr[n] = x;
       return newarr;
    }

    public static Company[] addCompanyType(int n, Company arr[], Company x){
        Company newarr[] = new Company[n + 1];
        for (int i = 0; i < n; i++)
            newarr[i] = arr[i];
 
        newarr[n] = x;
        return newarr;
     }

    boolean regStudent(Student s, Date reg){
        if (reg == null) return false;
        if(reg.compareTo(open_student)>=0 && reg.compareTo(end_student)<0){ 
        if(this.reg_student==null){
            this.reg_student=new Student[1];
            this.reg_student[0]=s;
        }
        else{
            addStudentType(this.reg_student.length, this.reg_student, s);
        }
        return true;
        }
        else{
            return false;
        }
    }

    void addStudent(Student s, String comp){
        if (this.reg_company == null) return;
        for(int i=0;i<this.reg_company.length;i++){
            if(this.reg_company[i].getName().equals(comp)){
                Company c=this.reg_company[i];
                if(s.getCgpa()>=c.getCgpa() && (s.getStatus().equals("unoffered") || 
                (s.getStatus().equals("offered") && s.getAcceptance().equals("accepted") && 
                c.getPackage_offer()>=3*s.getOffer_best().getPackage_offer()))){
                    if(c.reg==null){
                        c.reg=new Student[1];
                        c.reg[0]=s;
                    }
                    else{
                        c.reg = addStudentType(c.reg.length, c.reg, s);
                    }
                    if(s.applied==null){
                        s.applied=new Company[1];
                        s.applied[0]=reg_company[i];
                    }
                    else{
                        s.applied = addCompanyType(s.applied.length, s.applied, reg_company[i]);
                    }
                    System.out.println("Successfully Registered for "+reg_company[i].getRole()+" Role at "+comp+"!!!!");
                }
                else{
                    System.out.println("Not Registered at "+comp);
                }
                break;
            }
        }
    }

    boolean regCompany(Company c, Date date){
        if (date == null) return false;
        if(date.compareTo(open_company)>=0 && date.compareTo(end_company)<0){ 
            if(this.reg_company==null){
                this.reg_company=new Company[1];
                this.reg_company[0]=c;
            }
            else{
                this.reg_company = addCompanyType(this.reg_company.length, this.reg_company, c);
            }
            return true;
        }
        else{
            return false;
        }
    }

    void updateInfo(Student s, double old_cgpa, double new_cgpa){
        if (this.reg_student == null) return;
        for(int i=0;i<this.reg_student.length;i++){
            if(this.reg_student[i]==s && s.getCgpa()==old_cgpa){
                s.setCgpa(new_cgpa);
                System.out.println("CGPA updated!!!");
                return;
            }
        }
        System.out.println("CGPA cannot be updated.");
    }
}