public class Admin{
    private final String name;
    private String password;

    Admin(String name, String passwd){
        this.name=name;
        this.password=passwd;
    }

    String getName(){
        return name;
    }

    String getPassword(){
        return password;
    }

}