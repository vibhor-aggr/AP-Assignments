import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat; 
import java.util.Date;

public class Main{
    public static void main(String[] args){
        Placement p=new Placement();
        Student[] st=null;
        Company[] comp=null;
        Scanner sc = new Scanner(System.in);
            while(true){
                System.out.println("Welcome to FutureBuilder:\n" +
                "1) Enter the Application\n" +
                "2) Exit the Application");
                    int entry=sc.nextInt();
                    sc.nextLine();
                    if(entry==1){
                        while(true){
                            System.out.println("Choose The mode you want to Enter in:-\n" +
                            "1) Enter as Student Mode\n" +
                            "2) Enter as Company Mode\n" +
                            "3) Enter as Placement Cell Mode\n" +
                            "4) Return To Main Application");
                            
                            int mode=sc.nextInt();
                            sc.nextLine();
                            
                            if(mode==1){
                                while(true){
                                    System.out.print("Choose the Student Query to perform-\n" +
                                    "1) Enter as a Student(Give Student Name, and Roll No.)\n" +
                                    "2) Add students\n" +
                                    "3) Back\n");
                                    int op=sc.nextInt();
                                    sc.nextLine();
                                    int subop;
                                    if(op==1){
                                        String fname=sc.next();
                                        String lname=sc.next();
                                        int roll_no=sc.nextInt();
                                        sc.nextLine();
                                        Student s1=null;
                                        String name=fname+" "+lname;
                                        for(int i=0;i<st.length;i++){
                                            if(st[i].getName().equals(name) && st[i].getRoll_no()==roll_no){
                                                s1=st[i];
                                                break;
                                            }
                                        }
                                        if(s1!=null){
                                            while(true){
                                                System.out.println("Welcome "+fname+"!!\n" +
                                                "1) Register For Placement Drive\n" +
                                                "2) Register For Company\n" +
                                                "3) Get All available companies\n" +
                                                "4) Get Current Status\n" +
                                                "5) Update CGPA\n" +
                                                "6) Accept offer\n" +
                                                "7) Reject offer\n" +
                                                "8) Back");
                                                subop=sc.nextInt();
                                                sc.nextLine();
                                                if(subop==1){
                                                    try{
                                                    String string =sc.nextLine();
                                                    SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
                                                    Date date = format.parse(string);
                                                    if(s1.regPlacement(p, date)){
                                                        System.out.println(fname+" registered for the Placement Drive at IIITD!!!!" +
                                                        "\nYour details are:\n");
                                                        System.out.println(s1.toString());
                                                    } 
                                                    else{
                                                        System.out.println(fname+" not registered for the Placement Drive at IIITD!!!!");
                                                    }
                                                    }
                                                    catch(ParseException e){
                                                        System.out.println("Exception");
                                                    }
                                                }
                                                else if(subop==2){
                                                    String tcomp=sc.nextLine();
                                                    s1.regCompany(tcomp, p);
                                                }
                                                else if(subop==3){
                                                    s1.getCompany(p);
                                                }
                                                else if(subop==4){
                                                    s1.getCur();
                                                }
                                                else if(subop==5){
                                                    System.out.println("Enter old CGPA:\n");
                                                    double old_cgpa=sc.nextDouble();
                                                    System.out.println("Enter new CGPA:\n");
                                                    double new_cgpa=sc.nextDouble();
                                                    sc.nextLine();
                                                    s1.updateCGPA(p, old_cgpa, new_cgpa);
                                                }
                                                else if(subop==6){
                                                    s1.accept(true);
                                                }
                                                else if(subop==7){
                                                    s1.accept(false);
                                                }
                                                else
                                                    break;
                                            }
                                        }
                                    }
                                    else if(op==2){
                                        System.out.println("Number of students to add\n");
                                        subop=sc.nextInt();
                                        sc.nextLine();
                                        System.out.println("Please add students Name, Roll No, CGPA, Branch(in order):\n");
                                        for(int i=0;i<subop;i++){
                                            String name=sc.nextLine();
                                            int roll_no=sc.nextInt();
                                            double cgpa=sc.nextDouble();
                                            String branch=sc.next();
                                            sc.nextLine();
                                            sc.nextLine();
                                            Student student=new Student(name,roll_no,cgpa,branch);
                                            if(st==null){
                                                st=new Student[1];
                                                st[0]=student;
                                            }
                                            else{
                                                st = addStudentType(st.length, st, student);
                                            }
                                        }
                                    }
                                    else{
                                        break;
                                    }
                                } 
                            }
                            else if(mode==2){
                                while(true){
                                    System.out.println("Choose the Company Query to perform-\n" +
                                    "1) Add Company and Details\n" +
                                    "2) Choose Company\n" +
                                    "3) Get Available Companies\n" +
                                    "4) Back");
                                    int op=sc.nextInt();
                                    sc.nextLine();
                                    if(op==1){
                                        String name=sc.nextLine();
                                        String role=sc.nextLine();
                                        int package_offer=sc.nextInt();
                                        double cgpa=sc.nextDouble();
                                        sc.nextLine();
                                        Company company=new Company(name, role, package_offer, cgpa);
                                        if(comp==null){
                                            comp=new Company[1];
                                            comp[0]=company;
                                        }
                                        else{
                                            comp = addCompanyType(comp.length, comp, company);
                                        }
                                    }
                                    else if(op==2){
                                        if(comp==null)
                                            System.out.println("No companies available.");
                                        else{
                                            System.out.println("Choose To enter into mode of Available Companies:");
                                            for(int z=0;z<comp.length;z++){
                                                System.out.println(z+1+") "+comp[z].getName());
                                            }
                                            int subop=sc.nextInt();
                                            while(true){
                                                System.out.println("Welcome " + comp[subop-1].getName() +
                                                "\n1) Update Role\n" +
                                                "2) Update Package\n" +
                                                "3) Update CGPA criteria\n" +
                                                "4) Register To Institute Drive\n" +
                                                "5) Back");
                                                int comp_specific=sc.nextInt();
                                                sc.nextLine();
                                                if(comp_specific==1){
                                                    String role=sc.nextLine();
                                                    comp[subop-1].updateRole(role);
                                                }   
                                                else if(comp_specific==2){
                                                    int package_offer=sc.nextInt();
                                                    sc.nextLine();
                                                    comp[subop-1].update_package(package_offer);
                                                }
                                                else if(comp_specific==3){
                                                    double cgpa=sc.nextDouble();
                                                    sc.nextLine();
                                                    comp[subop-1].update_cgpa(cgpa);
                                                }
                                                else if(comp_specific==4){
                                                    try{
                                                    String strg =sc.nextLine();
                                                    SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
                                                    Date date = format.parse(strg);
                                                    comp[subop-1].regInstituteDrive(p, date);
                                                    }
                                                    catch(ParseException e){
                                                        System.out.println("Exception");
                                                    }
                                                }
                                                else{
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                    else if(op==3){
                                        if(comp==null)
                                            System.out.println("No available companies.");
                                        else{
                                            System.out.println("List of All available companies is as follows:");
                                            for(int z=0;z<comp.length;z++){
                                                System.out.println(z+1+") "+comp[z].toString()+"\n");
                                            }
                                        }
                                    }
                                    else
                                        break;
                                }
                            }
                            else if(mode==3){
                                while(true){
                                    System.out.println("Welcome to IIITD Placement Cell\n" +
                                    "1) Open Student Registrations\n" +
                                    "2) Open Company Registrations\n" +
                                    "3) Get Number of Student Registrations\n" +
                                    "4) Get Number of Company Registrations\n" +
                                    "5) Get Number of Offered/Unoffered/Blocked Students\n" +
                                    "6) Get Student Details\n" +
                                    "7) Get Company Details\n" +
                                    "8) Get Average Package\n" +
                                    "9) Get Company Process Results\n" +
                                    "10) Back");
                                    int op=sc.nextInt();
                                    sc.nextLine();
                                    if(op==1){
                                        System.out.println("Fill in the details:-\n" +
                                        "1) Set the Opening time for Student registrations\n" +
                                        "2) Set the Ending time for Student registrations.\n");
                                        try{
                                        String strg =sc.nextLine();
                                        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
                                        Date open_reg = format.parse(strg);
                                        strg =sc.nextLine();
                                        Date end_reg = format.parse(strg);
                                        p.openStudent(open_reg, end_reg);
                                        }
                                        catch(ParseException e){
                                            System.out.println("Exception");
                                        }
                                    }
                                    else if(op==2){
                                        System.out.println("Fill in the details:-\n" +
                                        "1) Set the Opening time for company registrations\n" +
                                        "2) Set the Closing time for company registrations\n");
                                        try{
                                        String strg =sc.nextLine();
                                        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
                                        Date open_reg = format.parse(strg);
                                        strg =sc.nextLine();
                                        Date end_reg = format.parse(strg);
                                        p.openCompany(open_reg, end_reg);
                                        }
                                        catch(ParseException e){
                                            System.out.println("Exception");
                                        }
                                    }
                                    else if(op==3){
                                        p.numStudent();
                                    }
                                    else if(op==4){
                                        p.numCompany();
                                    }
                                    else if(op==5){
                                        System.out.println("Choose the offer type-\n" +
                                        "1)Offered\n" +
                                        "2)Unoffered\n" +
                                        "3)Blocked");
                                        int subop=sc.nextInt();
                                        sc.nextLine();
                                        p.numOffered(subop);
                                    }
                                    else if(op==6){
                                        System.out.println("Name:\n");
                                        String name=sc.nextLine();
                                        System.out.println("Roll No:\n");
                                        int roll_no=sc.nextInt();
                                        sc.nextLine();
                                        p.getStudent(name, roll_no);
                                    }
                                    else if(op==7){
                                        System.out.println("Company Name:\n");
                                        String name=sc.nextLine();
                                        p.getCompany(name);
                                    }
                                    else if(op==8){
                                        p.getAveragePackage();
                                    }
                                    else if(op==9){
                                        System.out.println("Company Name:\n");
                                        String name=sc.nextLine();
                                        p.getCompanyResult(name);
                                    }
                                    else
                                        break;
                                }   
                            }
                            else{
                                break;
                            }
                        }
                    }
                    else{
                        System.out.println("Thanks For Using FutureBuilder!!!!!!");
                        break;
                    }
            }
        sc.close();
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
}
