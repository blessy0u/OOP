class Well extends OilFieldEntity {
    private double depth;

    public Well(String name, double depth) {
        super(name);
        this.depth = depth;
    }


    @Override
    public void displayInfo() {
        System.out.println("Скважина: " + getName() + ", Глубина: " + depth + " м");
    }
}
