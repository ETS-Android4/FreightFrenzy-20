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

        addState(new TurretOrbit(robot, robot.turretServoWhite, "RedWarehouseAutonomous", "01_0"));
        addState(new TurretArmExtension(robot, robot.whiteArmBobbin, "RedWarehouseAutonomous", "02_0"));
        addState(new DepositorDoor(robot, robot.whiteDispenser, "RedWarehouseAutonomous", "03_0"));
        addState(new DepositorDoor(robot, robot.whiteDispenser, "RedWarehouseAutonomous", "04_0"));
        addState(new TurretArmExtension(robot, robot.whiteArmBobbin, "RedWarehouseAutonomous", "05_0"));
        addState(new TurretOrbit(robot, robot.turretServoWhite, "RedWarehouseAutonomous", "06_0"));
        addState(new DriveState(robot,"RedWarehouseAutonomous", "07_0"));
        addState(new DriveState(robot, "RedWarehouseAutonomous", "08_0"));

    }
}
