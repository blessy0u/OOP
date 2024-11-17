abstract class OilFieldEntity {
    private String name;

    public String toString() {
        return "OilFieldEntity{" +
                "name='" + name + '\'' +
                '}';
    }

    public OilFieldEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void displayInfo();
}