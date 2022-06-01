package PlanePackage;

public enum Connections {
    BsAs_CORDOBA(695,"Buenos Aires - Cordoba"),
    BsAs_SANTIAGO(1400,"Buenos Aires - Santiago de Chile"),
    BsAs_MONTEVIDEO(950,"Buenos Aires - Montevideo"),
    CORDOBA_MONTEVIDEO(1190,"Cordoba - Montevideo"),
    CORDOBA_SANTIAGO(1050,"Cordoba - Santiago de Chile"),
    MONTEVIDEO_SANTIAGO(2100,"Montevideo - Santiago")
    ;

    final private int distance;


    Connections(int i, String s) {
        this.distance=i;
    }

    public int getDistance() {
        return distance;
    }




}

