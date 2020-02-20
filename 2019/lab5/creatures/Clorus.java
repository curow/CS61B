package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import java.awt.Color;
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

    /**
     * red color.
     */
    private final int r = 34;
    /**
     * green color.
     */
    private final int g = 0;
    /**
     * blue color.
     */
    private final int b = 231;

    /**
     * creates plip with energy equal to E.
     */
    public Clorus(double e) {
        super("clorus");
        energy = e;
    }

    /**
     * creates a clorus with energy equal to 1.
     */
    public Clorus() {
        this(1);
    }


    public Color color() {
        return color(r, g, b);
    }

    /**
     * cloruses attack other creatures and gain their energy
     */
    public void attack(Creature c) {
        energy += c.energy();
    }

    private static final double moveEnergyLoss = 0.03;
    private static final double stayEnergyLoss = 0.01;

    /**
     * Cloruses should lose 0.03 units of energy when moving.
     */
    public void move() {
        energy -= moveEnergyLoss;
    }


    /**
     * Cloruses should loss 0.01 energy when staying.
     */
    public void stay() {
        energy -= stayEnergyLoss;
    }

    /**
     * Plips and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Plip.
     */
    public Clorus replicate() {
        Clorus babyClorus = new Clorus(energy / 2);
        energy -= energy / 2;
        return babyClorus;
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> neighborPlip = new ArrayDeque<>();
        // (Google: Enhanced for-loop over keys of NEIGHBORS?)
        // for () {...}
        for (Map.Entry<Direction, Occupant> entry : neighbors.entrySet()) {
            Direction direction = entry.getKey();
            Occupant occupant = entry.getValue();
            if (occupant.name().equals("plip")) {
                neighborPlip.add(direction);
            } else if (occupant.name().equals("empty")) {
                emptyNeighbors.add(direction);
            }
        }

        // Rule 1
        if (emptyNeighbors.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        }
        // Rule 2
        else if (!neighborPlip.isEmpty()) {
            return new Action(Action.ActionType.ATTACK,
                    randomEntry(neighborPlip));
        }
        // Rule 3
        else if (energy >= 1) {
            return new Action(Action.ActionType.REPLICATE,
                    randomEntry(emptyNeighbors));
        }
        // Rule 4
        return new Action(Action.ActionType.MOVE,
                randomEntry(emptyNeighbors));
    }
}
