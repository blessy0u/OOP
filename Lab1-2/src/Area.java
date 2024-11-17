class Area extends OilFieldEntity {
    private double areaSize;

    public Area(String name, double areaSize) {
        super(name);
        this.areaSize = areaSize;
    }

    @Override
    public void displayInfo() {
        System.out.println("Месторождение: " + getName() + ", Размер: " + areaSize + " кв. км");
    }
}