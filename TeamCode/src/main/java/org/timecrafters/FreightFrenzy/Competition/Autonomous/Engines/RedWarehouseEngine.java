package org.timecrafters.FreightFrenzy.Competition.Autonomous.Engines;

import org.cyberarm.engine.V2.CyberarmEngine;
import org.timecrafters.FreightFrenzy.Competition.Autonomous.States.DepositorDoor;
import org.timecrafters.FreightFrenzy.Competition.Autonomous.States.DriveState;
import org.timecrafters.FreightFrenzy.Competition.Autonomous.States.TurretArmExtension;
import org.timecrafters.FreightFrenzy.Competition.Autonomous.States.TurretOrbit;
import org.timecrafters.FreightFrenzy.Competition.Common.Robot;

public class RedWarehouseEngine extends CyberarmEngine {
    @Override
    public void setup() {
        Robot robot = new Robot(this);

        addState(new TurretOrbit(robot, robot.turretServoWhite, 3, 1));
        addState(new TurretArmExtension(robot, robot.whiteArmBobbin, 3000, .75, 150));
        addState(new DepositorDoor(robot.whiteDispenser, .5, 1));
        addState(new DepositorDoor(robot.whiteDispenser, 0, 0));
        addState(new TurretArmExtension(robot, robot.whiteArmBobbin, 0, 1, 150));
        addState(new TurretOrbit(robot, robot.turretServoWhite, 3, -1));
        addState(new DriveState(robot, 1500, 500, 1, .75));
        addState(new DriveState(robot, 1500, 1500, 1, 1));

    }
}
