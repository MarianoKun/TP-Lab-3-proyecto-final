import PlanePackage.Flight;
import UserPackage.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class GenericFileManager<T> implements IGenericFileManager<T>{

    @Override
    public void saveFile(List<T> list, String filePath) {

        GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateConverter()).registerTypeAdapter(LocalDateTime.class, new LocalDateTimeConverter());

        Gson gson = gsonBuilder.create();

        Type type= new TypeToken<List<T>>(){}.getType();

        File file = new File(filePath);

        System.out.println(type);
        try {

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

            gson.toJson(list, type, bufferedWriter);

            bufferedWriter.close();

        } catch (EOFException eofe) {
            //eofe.printStackTrace(); ////  todo ENCONTRAR LA MANERA DE IMPEDIR ESTA EXCEPCION sin tener que obligatoriamente captarla
            System.out.println("TERMINO EL ARCHIVO");
        } catch (IOException ioe) {
            System.out.println("Error en la lectura del archivo " + filePath);
            ioe.printStackTrace();
        } finally {
            // cerrar bufferedwriter
        }
    }

    @Override
    public List<T> readFile(List<T> list, String filePath) {

        GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateConverter()).registerTypeAdapter(LocalDateTime.class, new LocalDateTimeConverter());

        Gson gson = gsonBuilder.create();

        Type type= new TypeToken<List<T>>(){}.getType();

        File file = new File(filePath);

        try {


            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            list =  gson.fromJson(bufferedReader, type);

        } catch (IOException ioe) {
            System.out.println("Error en la lectura del archivo " + filePath);
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // cerrar bufferedwriter

        }
        return list;
    }


}
