class WellOnArea {
    private Area area;
    private Well well;

    public WellOnArea(Area area, Well well) {
        this.area = area;
        this.well = well;
    }

    public void displayWellInfo() {
        area.displayInfo();
        well.displayInfo();
    }
}