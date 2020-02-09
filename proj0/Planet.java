public class Planet {
     public double xxPos;
     public double yyPos;
     public double xxVel;
     public double yyVel;
     public double mass;
     public String imgFileName;

     public Planet(double xP, double yP, double xV, double yV, double m, String img) {
          xxPos = xP;
          yyPos = yP;
          xxVel = xV;
          yyVel = yV;
          mass = m;
          imgFileName = img;
     }

     public Planet(Planet b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
   }

   public double calcDistance(Planet b) {
        return Math.sqrt(Math.pow(xxPos - b.xxPos, 2) + Math.pow(yyPos - b.yyPos, 2));
   }
}