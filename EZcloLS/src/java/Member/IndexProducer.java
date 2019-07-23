
package Member;
import java.util.ArrayList;
public class IndexProducer {
    private String deleteIndex = "deleteindex";
    private String renameIndex = "renameindex";
    private int i = 1 ;
    private int j = 1 ;
    private ArrayList<String> arr ;
    public IndexProducer() {
        
    }
    public String getDeleteIndex() {
        deleteIndex = "deleteindex" + (i++) ;
        return deleteIndex;
    }

    public String getRenameIndex() {
        renameIndex = "renameindex" + (j++) ;
        return renameIndex;
    }
    
}
