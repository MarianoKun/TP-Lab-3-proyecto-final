package BranchJuanma;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import java.io.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FileHandler <T> {
    private static final String usersFilePath = "userFile.json";
    private static final String flightsFilePath = "flightsFile.json";
    private static final String planesFilePath = "planesFile.json";


    public void saveFile(List<T> list) {

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

    public List<T> readFile(String filePath) {
        List<T> list = new ArrayList<>();

        try {
            File file = new File(filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));

            list = (List<T>) objectInputStream.readObject();

            objectInputStream.close();

        } catch (IOException ioe) {
            System.out.println("Error en la lectura del archivo " + filePath);
            ioe.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            // cerrar
        }
        return list;
    }

    public void saveFileGson(List<T> list, String filePath) {

        try {
            File file = new File(filePath);
            // crar instancia de Gson
            Gson gson = new Gson();

            // crear BufferedWriter
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(filePath)));

            // convertir lista a gson
            gson.toJson(list, list.getClass(), bufferedWriter);

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

    public List<T> readFileGson(List<T> list, String filePath) {


        try {
            File file = new File(filePath);
            // crar instancia de Gson
            Gson gson = new Gson();

            // crear BufferedWriter
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(usersFilePath)));

            // pasar conversion json a lista para retornar
            list = gson.fromJson(bufferedReader, list.getClass() );

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

    public String filePathSelector(List<T> list){
        String filePath = null;

        if(list.get(0) instanceof User){
            return usersFilePath;
        }else if(list.get(0) instanceof Flight){
            return flightsFilePath;
        }else{
            return planesFilePath;
        }

    }





}
