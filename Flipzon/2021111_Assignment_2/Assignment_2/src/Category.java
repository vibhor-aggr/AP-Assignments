import java.util.LinkedList;
import java.util.List;

public class Category {
    private final String name;
    private final int categoryId;
    private List<String> pids;

    Category(String name, int categoryId){
        this.name=name;
        this.categoryId=categoryId;
        this.pids=new LinkedList<String>();
    }

    String getName(){
        return name;
    }

    List<String> getPids(){
        return pids;
    }

    boolean isProduct(String pid){
        return pids.contains(pid);
    }

    boolean addProduct(String pid){
        if(isProduct(pid))
            return false;
        pids.add(pid);
        return true;
    }

    boolean delProduct(String pid){
        return pids.remove(pid);
    }

    int numProduct(){
        return this.pids.size();
    }
}
