package FileManager;

import UserPackage.Admin;
import UserPackage.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;



public class ManageUsers implements IFileManager <User>  {
    public static final String usersFilePath = "userFile.json";

    /**
     * Guarda en el directorio de User la lista pasada por parametro
     * crea adaptadores para la sublclase de User (Admin)
     * @param list <User>
     */
    public void saveFile(List<User> list) {

        RuntimeTypeAdapterFactory<User> adapter = RuntimeTypeAdapterFactory.of(User.class, "User").registerSubtype(User.class,"user").registerSubtype(Admin.class,"admin");
        GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapterFactory(adapter);

        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateConverter()).registerTypeAdapter(LocalDateTime.class, new LocalDateTimeConverter());//.registerTypeAdapterFactory(adapter);

        Gson gson = gsonBuilder.create();

        Type type= new TypeToken<List<User>>(){}.getType();


        File file = new File(ManageUsers.usersFilePath);

        try {


            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

            gson.toJson(list, type, bufferedWriter);

            bufferedWriter.close();

        } catch (FileNotFoundException fnfe) {
            System.out.println("No se encuentra el archivo");
        } catch (IOException ioe) {
            System.out.println("Error en la lectura del archivo " + usersFilePath);
            ioe.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Trae desde archivo, el directorio de User, a la lista pasada por parametro
     * crea adaptadores para la sublclase de User (Admin)
     * @param list <User>
     * @return lista leida desde archivo
     */
    public List<User> readFile(List<User> list ) {

        RuntimeTypeAdapterFactory<User> adapter = RuntimeTypeAdapterFactory.of(User.class, "User").registerSubtype(User.class,"user").registerSubtype(Admin.class,"admin");
        GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapterFactory(adapter);

        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateConverter()).registerTypeAdapter(LocalDateTime.class, new LocalDateTimeConverter());//.registerTypeAdapterFactory(adapter);

        Gson gson = gsonBuilder.create();

        Type type= new TypeToken<List<User>>(){}.getType();

        File file = new File(ManageUsers.usersFilePath);
        try {


            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            list =  gson.fromJson(bufferedReader,type);


        } catch (IOException ioe) {
            System.out.println("Error en la lectura del archivo " + usersFilePath);
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
