package data;

public class Chapter {
    private String name;
    private String world;

    public Chapter(){

    }

    public Chapter(String name, String world){
        this.name = name;
        this.world = world;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorld(){
        return this.world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Chapter chapter = (Chapter) o;
        if (this.hashCode() != chapter.hashCode()) return false;
        return (this.getName().equals(chapter.getName()) && this.getWorld().equals(chapter.getWorld()));
    }

    @Override
    public int hashCode(){
        int ans = 0;
        for (int i = 0; i < this.getName().length(); i++){
            ans = (ans + (int)this.getName().charAt(i)) % 2000000000;
        }
        ans = (ans + this.getWorld().length()) % 2000000000;
        return ans;
    }

    @Override
    public String toString(){
        String res = "Название главы: " + this.getName() + "\n";
        res += "Название мира: " + this.getWorld() + "\n";
        return res;
    }
}
