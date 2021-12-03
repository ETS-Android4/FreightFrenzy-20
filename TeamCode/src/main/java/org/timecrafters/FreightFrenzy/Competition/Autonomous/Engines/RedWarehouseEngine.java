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

        addState(new TurretOrbit(robot, robot.turretServoWhite, "autonomous", "01_0"));
        addState(new TurretArmExtension(robot, robot.whiteArmBobbin, "autonomous", "02_0"));
        addState(new DepositorDoor(robot, robot.whiteDispenser, "autonomous", "03_0"));
        addState(new DepositorDoor(robot, robot.whiteDispenser, "autonomous", "04_0"));
        addState(new TurretArmExtension(robot, robot.whiteArmBobbin, "autonomous", "05_0"));
        addState(new TurretOrbit(robot, robot.turretServoWhite, "autonomous", "01_0"));
        addState(new DriveState(robot, 1500, 500, 1, .75));
        addState(new DriveState(robot, 1500, 1500, 1, 1));

    }
}
