package data;

public class Coordinates {
    private long x;
    private Double y;

    public Coordinates(){

    }

    public Coordinates(long x, Double y){
        this.x = x;
        this.y = y;
    }

    public long getX() {
        return x;
    }

    public void setX(long x){
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Coordinates coordinates = (Coordinates) o;
        if (this.hashCode() != coordinates.hashCode()) return false;
        return (this.getX() == coordinates.getX() && this.getY() == coordinates.getY());
    }

    @Override
    public int hashCode(){
        int ans = this.getY().hashCode() + (int)this.getX();
        return ans;
    }

    @Override
    public String toString(){
        String res = "Координата X: " + this.getX() + "\n";
        res += "Координата Y: " + this.getY() + "\n";
        return res;
    }
}
