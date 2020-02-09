public class Planet {
     static final double G = 6.67e-11;
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
          double dx = xxPos - b.xxPos;
          double dy = yyPos - b.yyPos;
          return Math.sqrt(dx * dx + dy * dy);
     }

     public double calcForceExertedBy(Planet b) {
          double distance = this.calcDistance(b);
          return G * mass * b.mass / (distance * distance);
     }

     public double calcForceExertedByX(Planet b) {
          double force = calcForceExertedBy(b);
          double distance = calcDistance(b);
          return (b.xxPos - xxPos) / distance * force;
     }

     public double calcForceExertedByY(Planet b) {
          double force = calcForceExertedBy(b);
          double distance = calcDistance(b);
          return (b.yyPos - yyPos) / distance * force;
     }

     public double calcNetForceExertedByX(Planet[] allPlanets) {
          double force = 0;
          for (Planet b : allPlanets) {
               if (this.equals(b)) {
                    continue;
               } else {
                    force += calcForceExertedByX(b);
               }
          }
          return force;
     }

     public double calcNetForceExertedByY(Planet[] allPlanets) {
          double force = 0;
          for (Planet b : allPlanets) {
               if (this.equals(b)) {
                    continue;
               } else {
                    force += calcForceExertedByY(b);
               }
          }
          return force;
     }

     public void update(double dt, double fx, double fy) {
          double ax = fx / mass;
          double ay = fy / mass;
          xxVel += ax * dt;
          yyVel += ay * dt;
          xxPos += xxVel * dt;
          yyPos += yyVel * dt;
     }
}