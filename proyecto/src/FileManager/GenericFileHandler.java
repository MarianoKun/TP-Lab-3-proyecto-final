package FileManager;
import PlanePackage.Flight;
import UserPackage.User;
import java.io.*;
import java.util.List;

public class GenericFileHandler {

    public static final String usersFilePath = "userFile.txt";
    public static final String flightsFilePath = "flightsFile.txt";
    public static final String planesFilePath = "planesFile.txt";

    public GenericFileHandler() {
    }

    /**
     * Salva listado en formato Json en el directorio correspondiente
     * @param list <T>  lista generica (Planes/Users/Flights)
     */
    public <T> void saveFile (List<T> list) {

        String filePath = filePathSelector(list);

        try {
            File file = new File(filePath);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));

            objectOutputStream.writeObject(list);

            objectOutputStream.close();

        } catch (IOException ioe) {
            System.out.println("Error en la lectura del archivo " + filePath);
            ioe.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            // cerrar bufferedwriter
        }

    }

    /**
     * Retorna lista desde archivo Json
     * @param list Lista generica (Planes/Users/Flights)
     * @param filePath directorio del filePath a levantar
     */
    public <T> List<T> readFile(List<T> list, String filePath) {

        try {
            File file = new File(filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));

            list = (List<T>) objectInputStream.readObject();

            objectInputStream.close();

        }catch(FileNotFoundException fnfe){
            System.out.println("Archivo no encontrado");
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            System.out.println("Error en la lectura del archivo " + filePath);
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Elije el directorio indicado segun el tipo de lista
     * @param list Lista generica
     * @return String - directory path
     */
    public <T> String filePathSelector(List<T> list){

        if(list.get(0) instanceof User) {
            return usersFilePath;
        }else if(list.get(0) instanceof Flight){
            return flightsFilePath;
        }else{
            return planesFilePath;
        }

    }



}
