package fi.metropolia.herbreferenceguide.Veggie;

public class Veggies {
    private String name;

    public Veggies(String name) {
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
        return "Veggies{" +
                "name='" + name + '\'' +
                '}';
    }
}
