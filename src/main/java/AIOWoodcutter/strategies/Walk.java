package AIOWoodcutter.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.TilePath;
import AIOWoodcutter.data.Variables;

public class Walk implements Strategy {

    @Override
    public boolean activate() {
        return Game.isLoggedIn() &&
                !Inventory.isFull() &&
                Variables.getLocation().getBankZone().inTheZone();
    }

    @Override
    public void execute() {
        Variables.pathToWalk = new TilePath(Variables.getTree().getPathToTree());
        Variables.setBotStatus("walking to " + Variables.getTree().getName());
        while (Variables.pathToWalk != null && !Variables.pathToWalk.hasReached()) {
            Variables.pathToWalk.traverse();
            Time.sleep(1000, 2000);
        }
    }
}