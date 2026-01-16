package bloch.chapter6.item34;

// Takes earth-weight and prints table of weights on all planets (Page 160)
public class WeightTable {
   public static void main(String[] args) {
      //String argEarthWeight = "6.99722E24";
      String argEarthWeight = "185";
      double earthWeight = Double.parseDouble(argEarthWeight);
      double mass = earthWeight / Planet.EARTH.surfaceGravity();
      for (Planet p : Planet.values())
         System.out.printf("Weight on %s is %f%n",
                 p, p.surfaceWeight(mass));
   }
}
