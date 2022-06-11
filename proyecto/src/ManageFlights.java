import PlanePackage.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ManageFlights implements IFileManager <Flight>  {
    public static final String flightsFilePath = "flightsFile.json";

    public ManageFlights() {
    }

    public void saveFile(List<Flight> list) {

        RuntimeTypeAdapterFactory<Planes> adapter = RuntimeTypeAdapterFactory.of(Planes.class, "Planes").registerSubtype(Planes.class,"planes").registerSubtype(BronzePlane.class,"Bronze").registerSubtype(SilverPlane.class,"Silver").registerSubtype(GoldPlane.class,"Gold");
        GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapterFactory(adapter);

        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateConverter()).registerTypeAdapter(LocalDateTime.class, new LocalDateTimeConverter());

        Gson gson = gsonBuilder.create();

        Type type= new TypeToken<List<Flight>>(){}.getType();

        File file = new File(ManageFlights.flightsFilePath);

        try {

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

            gson.toJson(list,type,bufferedWriter);

            bufferedWriter.close();

        } catch (FileNotFoundException fnfe) {
            System.out.println("No se encuentra el archivo");
        } catch (IOException ioe) {
            System.out.println("Error en la lectura del archivo " + flightsFilePath);
            ioe.printStackTrace();
        }
    }


    public List<Flight> readFile(List<Flight> list ) {

        RuntimeTypeAdapterFactory<Planes> adapter = RuntimeTypeAdapterFactory.of(Planes.class, "Planes").registerSubtype(Planes.class,"planes").registerSubtype(BronzePlane.class,"Bronze").registerSubtype(SilverPlane.class,"Silver").registerSubtype(GoldPlane.class,"Gold");
        GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapterFactory(adapter);

        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateConverter()).registerTypeAdapter(LocalDateTime.class, new LocalDateTimeConverter());

        Gson gson = gsonBuilder.create();

        Type type= new TypeToken<List<Flight>>(){}.getType();

        File file = new File(ManageFlights.flightsFilePath);

        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            list = gson.fromJson(bufferedReader,type);

            bufferedReader.close();

        } catch (IOException ioe) {
            System.out.println("Error en la lectura del archivo " + flightsFilePath);
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
