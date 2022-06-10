import java.util.List;

public interface IGenericFileManager <T> {

    void saveFile(List<T> list, String filePath);

    List<T> readFile(List<T> list, String filePath); // ver si esta bien usar el wildcard

}
