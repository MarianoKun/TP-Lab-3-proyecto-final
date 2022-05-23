package PlanePackage;

import java.util.ArrayList;
import java.util.List;

public class vuelo <T extends  Planes>{

    List<BronzePlane> bronzeList=new ArrayList<>(15);
    List<SilverPlane> SilverList=new ArrayList<>(15);
    List<GoldPlane> goldList=new ArrayList<>(15);


    public double calcularaValor (T plane, Connections city){
        //(Cantidad de kms * Costo del km) + (cantidad de pasajeros * 3500) + (Tarifa del tipo de avión)

        double endPrice;

        endPrice= city.getDistance() * plane.costPerKm + plane.occupation*3500 + plane.priceOfRent;

        return endPrice;
    }



    










}
