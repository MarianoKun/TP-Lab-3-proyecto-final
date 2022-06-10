import PlanePackage.Flight;
import PlanePackage.Planes;
import UserPackage.User;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GenericFileHandler {

    public static final String usersFilePath = "userFile.json";
    public static final String flightsFilePath = "flightsFile.json";
    public static final String planesFilePath = "planesFile.json";



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
     * Salva listado en formato Json en el directorio correspondiente, usando GSON
     * @param list <T>  lista generica (Planes/Users/Flights)
     */
    public <T> void saveFileGson(List<T> list ,String filePath) {
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

    /**
     * Retorna lista desde archivo Json con GSON
     * @param list Lista generica (Planes/Users/Flights)
     * @param filePath directorio del filePath a levantar
     */
    public <T> List<T> readFileGson(List<T> list, String filePath) {

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
