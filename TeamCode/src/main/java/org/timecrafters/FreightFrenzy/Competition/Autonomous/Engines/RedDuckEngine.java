package org.timecrafters.FreightFrenzy.Competition.Autonomous.Engines;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.cyberarm.engine.V2.CyberarmEngine;
import org.timecrafters.FreightFrenzy.Competition.Autonomous.States.CollectorToggle;
import org.timecrafters.FreightFrenzy.Competition.Autonomous.States.DriveState;
import org.timecrafters.FreightFrenzy.Competition.Autonomous.States.TensorFlowState;
import org.timecrafters.FreightFrenzy.Competition.Autonomous.States.TurretArmExtension;
import org.timecrafters.FreightFrenzy.Competition.Autonomous.States.TurretOrbit;
import org.timecrafters.FreightFrenzy.Competition.Common.Robot;

@Autonomous(name = "RedDuckAutonomous", group = "red")

public class RedDuckEngine extends CyberarmEngine {

    @Override
    public void setup() {
        Robot robot = new Robot(this);
        robot.resetEncoders();

        addState(new TurretOrbit(robot, robot.turretServoOrange, robot.orangeMag, "RedDuckAutonomous", "01_0"));
        addState(new TensorFlowState(robot, robot.orangeArmRiser, robot.orangeArmBobbin, "RedDuckAutonomous", "01_1"));
//        addState(new TurretArmExtension(robot, robot.orangeArmBobbin, "RedWarehouseAutonomous", "02_0"));
//        addState(new TurretArmRiser(robot, robot.orangeArmRiser, "RedWarehouseAutonomous", "03_0_middle"));
//        addState(new TurretArmExtension(robot, robot.orangeArmBobbin, "RedWarehouseAutonomous", "04_0"));
        addState(new CollectorToggle(robot, robot.collectorOrange, "RedDuckAutonomous", "05_0"));
        addState(new CollectorToggle(robot, robot.collectorOrange, "RedDuckAutonomous", "06_0"));
        addState(new TurretArmExtension(robot, robot.orangeArmBobbin, "RedDuckAutonomous", "07_0"));
        addState(new DriveState(robot,"RedDuckAutonomous", "08_0"));
        addState(new DriveState(robot, "RedDuckAutonomous", "09_0"));
        addState(new TurretOrbit(robot, robot.turretServoOrange, null, "RedDuckAutonomous", "10_0"));
        addState(new DriveState(robot, "RedDuckAutonomous", "10_1"));

    }
}
