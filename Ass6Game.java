// ID 208465096

import biuoop.GUI;
import levels.LevelInformation;
import levels.DirectHit;
import levels.WideEasy;
import levels.Green3;
import levels.FinalFour;
import animations.AnimationRunner;
import settings.GameFlow;
import settings.GameLevel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ariel Ashkenazy
 * this class runs the game.
 */
public class Ass6Game {
    /**
     * starts the game.
     * @param args the input arguments.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", GameLevel.WIDTH, GameLevel.HEIGHT);
        AnimationRunner runner = new AnimationRunner(gui);
        GameFlow gameFlow = new GameFlow(runner, gui.getKeyboardSensor());
        List<LevelInformation> levelsList = new ArrayList<>();
        if (args.length == 0) {
            levelsList.add(new DirectHit());
            levelsList.add(new WideEasy());
            levelsList.add(new Green3());
            levelsList.add(new FinalFour());
        } else {
            for (String curr : args) {
                if (curr.equals("1")) {
                    levelsList.add(new DirectHit());
                } else if (curr.equals("2")) {
                    levelsList.add(new WideEasy());
                } else if (curr.equals("3")) {
                    levelsList.add(new Green3());
                } else if (curr.equals("4")) {
                    levelsList.add(new FinalFour());
                }
            }
        }
        gameFlow.runLevels(levelsList);
        gui.close();
    }
}