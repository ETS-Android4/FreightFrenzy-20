package org.timecrafters.FreightFrenzy.Competition.Autonomous.Engines;

import org.cyberarm.engine.V2.CyberarmEngine;
import org.timecrafters.FreightFrenzy.Competition.Autonomous.States.DriveState;
import org.timecrafters.FreightFrenzy.Competition.Common.Robot;

public class RedWarehouseEngine extends CyberarmEngine {
    @Override
    public void setup() {
        Robot robot = new Robot(this);

        addState(new DriveState(robot, 2000, 2000,.25,.25));
    }
}
