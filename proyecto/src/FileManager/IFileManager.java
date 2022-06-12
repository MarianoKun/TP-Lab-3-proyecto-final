package FileManager;

import java.util.List;

public interface IFileManager <T> {

    void saveFile(List<T> list);

    List<T> readFile(List<T> list);

}
