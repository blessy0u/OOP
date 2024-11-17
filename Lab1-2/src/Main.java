public class Main {
    public static void main(String[] args) {
        Area area = new Area("Северное поле", 150.5);
        Well well = new Well("Скважина 1", 200.0);
        WellOnArea wellOnArea = new WellOnArea(area, well);

        wellOnArea.displayWellInfo();

        Area southArea = new Area("Южное поле", 120.0);
        Well well2 = new Well("Скважина 2", 180.0);
        WellOnArea wellOnArea2 = new WellOnArea(southArea, well2);

        wellOnArea2.displayWellInfo();

        Area eastArea = new Area("Восточное поле", 180.0);
        Well well3 = new Well("Скважина 3", 250.0);
        WellOnArea wellOnArea3 = new WellOnArea(eastArea, well3);

        wellOnArea3.displayWellInfo();
    }
}