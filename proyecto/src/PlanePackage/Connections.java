package PlanePackage;

public enum Connections {
    BsAs_CORDOBA(695,"Buenos Aires - Cordoba"),
    BsAs_SANTIAGO(1400,"Buenos Aires - Santiago de Chile"),
    BsAs_MONTEVIDEO(950,"Buenos Aires - Montevideo"),
    CORDOBA_MONTEVIDEO(1190,"Cordoba - Montevideo"),
    CORDOBA_SANTIAGO(1050,"Cordoba - Santiago de Chile"),
    MONTEVIDEO_SANTIAGO(2100,"Montevideo - Santiago"),
    CORDOBA_BsAs(695,"Cordoba- Buenos Aires"),
    SANTIAGO_BsAs(1400,"Santiago de Chile - Buenos Aires"),
    MONTEVIDEO_BsAs(950,"Montevideo - Buenos Aires"),
    MONTEVIDEO_CORDOBA(1190,"Montevideo - Cordoba"),
    SANTIAGO_CORDOBA(1050,"Santiago de Chile - Cordoba"),
    SANTIAGO_MONTEVIDEO(2100,"Santiago - Montevideo");


    final private int distance;


    Connections(int i, String s) {
        this.distance=i;
    }

    public int getDistance() {
        return distance;
    }




}

