package fi.metropolia.herbreferenceguide.Herb;

public class Herbs {
    private String name;

    public Herbs(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Herbs{" +
                "name='" + name + '\'' +
                '}';
    }
}
