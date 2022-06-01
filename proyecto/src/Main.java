import BranchJuanma.Executable;
import BranchJuanma.Flight;
import BranchJuanma.User;
import PlanePackage.Planes;
import PlanePackage.Tester;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Planes> planeList = new ArrayList<>();
        List<Flight> flightList = new ArrayList<>();
        List<User> userList = new ArrayList<>();

        User admin1 = new User("juan manuel","sanjurjo");

        userList.add(admin1);

        Executable exec = new Executable(userList,flightList,planeList);

        exec.appCycle();








    }

}
