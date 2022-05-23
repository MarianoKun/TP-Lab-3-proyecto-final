package PlanePackage;

import java.util.ArrayList;
import java.util.List;

public class vuelo <T extends  Planes>{

    List<BronzePlane> bronzeList=new ArrayList<>(15);
    List<SilverPlane> SilverList=new ArrayList<>(15);
    List<GoldPlane> goldList=new ArrayList<>(15);


    public double calcularaValor (T plane, Cities city){
        //(Cantidad de kms * Costo del km) + (cantidad de pasajeros * 3500) + (Tarifa del tipo de avión)

        double endPrice;
        double priceOfPlane;

        if(plane instanceof GoldPlane){
            priceOfPlane=6000;
        }else if(plane instanceof SilverPlane){
            priceOfPlane=4000;
        }else {
            priceOfPlane=3000;
        }

        endPrice= city.getDistance() * plane.costPerKm + plane.occupation*3500 + priceOfPlane;

        return endPrice;
    }

    










}
