public class PlantMaker{
    private String plantType;

    public PlantMaker(String plantType){
        this.plantType = plantType;
    }

    public Plant makePlant(){
        Plant plant;
        switch(this.plantType){
            case "sunflower": plant = new Sunflower(); break;
            case "peashooter": plant = new Peashooter(); break;
            case "walnut": plant = new Walnut(); break;
            case "jalapeno": plant = new Jalapeno(); break;
            case "potatoMine": plant = new PotatoMine(); break;
            default: plant = null; break;
        }
        return plant;
    }
}
