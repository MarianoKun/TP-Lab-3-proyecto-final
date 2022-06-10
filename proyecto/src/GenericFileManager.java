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

public class GenericFileManager<T> implements IFileManager<T>{
    private T  type;

    @Override
    public void saveFile(List<T> list) {

        if(type instanceof Flight){

            ManageFlights manageFlights = new ManageFlights();
            manageFlights.saveFile((List<Flight>) list);


        }

    }

    @Override
    public List<T> readFile(List<T> list) {
        return null;
    }
}
