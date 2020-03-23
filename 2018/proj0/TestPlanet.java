public class TestPlanet {
    public static void main(String[] args) {
        checkPlanet();
    }

    private static void checkPlanet() {
        Planet p1 = new Planet(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
        Planet p2 = new Planet(2.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
        Planet[] allPlanets = {p1, p2};
        double fx1 = p1.calcNetForceExertedByX(allPlanets);
        double fx2 = p2.calcNetForceExertedByX(allPlanets);
        System.out.println("all force to planet 1 equals to " + fx1);
        System.out.println("all force to planet 2 equals to " + fx2);
    }
}