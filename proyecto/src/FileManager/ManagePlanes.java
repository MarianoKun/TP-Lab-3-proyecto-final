package FileManager;

import PlanePackage.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ManagePlanes implements IFileManager <Planes>{

    public static final String planesFilePath = "planesFile.json";

    public ManagePlanes() {
    }

    /**
     * Guarda en el directorio de Planes la lista pasada por parametro
     * crea adaptadores para las sublclases de planes
     * adapta las clases LocalDate y LocalDateTime
     * @param list <Planes>
     */
    public void saveFile(List<Planes> list) {
        RuntimeTypeAdapterFactory<Planes> adapter = RuntimeTypeAdapterFactory.of(Planes.class, "Planes").registerSubtype(Planes.class,"planes").registerSubtype(BronzePlane.class,"Bronze").registerSubtype(SilverPlane.class,"Silver").registerSubtype(GoldPlane.class,"Gold");
        GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapterFactory(adapter);

        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateConverter()).registerTypeAdapter(LocalDateTime.class, new LocalDateTimeConverter());

        Gson gson = gsonBuilder.create();

        Type type= new TypeToken<List<Planes>>(){}.getType();

        File file = new File(planesFilePath);

        try {


            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

            gson.toJson(list, type, bufferedWriter);

            bufferedWriter.close();

        } catch (FileNotFoundException fnfe) {
            System.out.println("No se encuentra el archivo");
        } catch (IOException ioe) {
            System.out.println("Error en la lectura del archivo " + planesFilePath);
            ioe.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Trae desde archivo, el directorio de Planes, a la lista pasada por parametro
     * crea adaptadores para las sublclases de planes
     * adapta las clases LocalDate y LocalDateTime
     * @param list <Planes>
     * @return lista leida desde archivo
     */
    public List<Planes> readFile(List<Planes> list ) {

        RuntimeTypeAdapterFactory<Planes> adapter = RuntimeTypeAdapterFactory.of(Planes.class, "Planes").registerSubtype(Planes.class,"planes").registerSubtype(BronzePlane.class,"Bronze").registerSubtype(SilverPlane.class,"Silver").registerSubtype(GoldPlane.class,"Gold");
        GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapterFactory(adapter);

        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateConverter()).registerTypeAdapter(LocalDateTime.class, new LocalDateTimeConverter());


        Gson gson = gsonBuilder.create();

        Type type= new TypeToken<List<Planes>>(){}.getType();

        File file = new File(ManagePlanes.planesFilePath);


        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            list =  gson.fromJson(bufferedReader,type);


        }catch (IOException ioe) {
            System.out.println("Error en la lectura del archivo " + planesFilePath);
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}
