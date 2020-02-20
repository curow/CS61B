package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import java.awt.Color;
import java.text.AttributedCharacterIterator;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Map;

import static huglife.HugLifeUtils.randomEntry;

/**
 * An implementation of predator
 *
 * @author Zhiwne Xie
 */
public class Clorus extends Creature {

    private int age;
    private int replicateUpperAge;
    private int activeAge;
    private double ageFactor;

    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g = 0;
    /**
     * blue color.
     */
    private int b;

    /**
     * creates plip with energy equal to E.
     */
    public Clorus(double e) {
        super("clorus");
        age = 0;
        replicateUpperAge = 600;
        activeAge = replicateUpperAge;
        ageFactor = 0.02;
        energy = e;
    }

    /**
     * creates a clorus with energy equal to 1.
     */
    public Clorus() {
        this(1);
    }


    public Color color() {
        r = (int) Math.min(255, age / 10);
        b = (int) Math.min(255, energy / age);
        return color(r, g, b);
    }

    /**
     * cloruses attack other creatures and gain their energy
     */
    public void attack(Creature c) {
        energy += c.energy() / (1 + ageFactor * age);
    }

    private static final double moveEnergyLoss = 0.03;
    private static final double stayEnergyLoss = 0.01;

    /**
     * Cloruses should lose 0.03 units of energy when moving.
     */
    public void move() {
        energy -= moveEnergyLoss * (1 + ageFactor * age);
    }


    /**
     * Cloruses should loss 0.01 energy when staying.
     */
    public void stay() {
        energy -= stayEnergyLoss * (1 + ageFactor * age);
    }

    /**
     * Plips and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Plip.
     */
    public Clorus replicate() {
        double ageLoss =  Math.min((double) age / replicateUpperAge, 0.8);
        Clorus babyClorus = new Clorus(energy / (2 + 2 * ageLoss));
        energy -= babyClorus.energy;
        energy *= 1 - ageLoss;
        return babyClorus;
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        age += 1;
        Deque<Direction> placesToWander = new ArrayDeque<>();
        Deque<Direction> neighborPlip = new ArrayDeque<>();
        for (Map.Entry<Direction, Occupant> entry : neighbors.entrySet()) {
            Direction direction = entry.getKey();
            Occupant occupant = entry.getValue();
            if (occupant.name().equals("plip")) {
                neighborPlip.add(direction);
            } else if (occupant.name().equals("empty")) {
                placesToWander.add(direction);
            }
        }

        // modified the rule to make Clorus less aggressive
        double hungryEnergyThreshold = 5;
        double replicateEnergyThreshold = 15;
        // eat for survive.
        if (!neighborPlip.isEmpty()
                && energy < hungryEnergyThreshold) {
            return new Action(Action.ActionType.ATTACK,
                    randomEntry(neighborPlip));
        }
        else if (placesToWander.isEmpty()) {
            // eat to be able to wander around if not full.
            if (!neighborPlip.isEmpty() && age < activeAge) {
                return new Action(Action.ActionType.ATTACK,
                        randomEntry(neighborPlip));
            } else { // no place to go.
                return new Action(Action.ActionType.STAY);
            }
        }
        else if (energy >= replicateEnergyThreshold
                    && age < replicateUpperAge) {
            return new Action(Action.ActionType.REPLICATE,
                    randomEntry(placesToWander));
        }
        return new Action(Action.ActionType.MOVE,
                randomEntry(placesToWander));
    }
}
