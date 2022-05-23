package PlanePackage;

public enum Conecctions {
    BsAs_CORDOBA(695),
    BsAs_SANTIAGO(1400),
    BsAs_MONTEVIDEO(950),
    CORDOBA_MONTEVIDEO(1190),
    CORDOBA_SANTIAGO(1050),
    MONTEVIDEO_SANTIAGO(2100)
    ;

    final private int distance;


    Conecctions(int i) {
        this.distance=i;
    }

    public int getDistance() {
        return distance;
    }




}

