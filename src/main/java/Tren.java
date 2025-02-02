public class Tren {
    private int id;
    boolean escarga;

    public Tren(int id, boolean escarga) {
        this.id = id;
        this.escarga = escarga;
    }


    public int getId() {
        return id;
    }

    public boolean isEscarga() {
        return escarga;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEscarga(boolean escarga) {
        this.escarga = escarga;
    }
}
