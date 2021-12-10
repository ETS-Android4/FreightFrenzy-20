package org.timecrafters.FreightFrenzy.Competition.Autonomous.Engines;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.cyberarm.engine.V2.CyberarmEngine;
import org.timecrafters.FreightFrenzy.Competition.Autonomous.States.CollectorToggle;
import org.timecrafters.FreightFrenzy.Competition.Autonomous.States.DepositorDoor;
import org.timecrafters.FreightFrenzy.Competition.Autonomous.States.DriveState;
import org.timecrafters.FreightFrenzy.Competition.Autonomous.States.TensorFlowState;
import org.timecrafters.FreightFrenzy.Competition.Autonomous.States.TurretArmExtension;
import org.timecrafters.FreightFrenzy.Competition.Autonomous.States.TurretArmRiser;
import org.timecrafters.FreightFrenzy.Competition.Autonomous.States.TurretOrbit;
import org.timecrafters.FreightFrenzy.Competition.Common.Robot;
@Autonomous (name = "Red WareHouse", group = "red")
public class RedWarehouseEngine extends CyberarmEngine {
    Robot robot;

    @Override
    public void setup() {
        this.robot = new Robot(this);
        robot.resetEncoders();

        addState(new TurretOrbit(robot, robot.turretServoWhite, robot.whiteMag, "RedWarehouseAutonomous", "01_0"));
        addState(new TensorFlowState(robot, robot.whiteArmRiser, robot.whiteArmBobbin, "RedWarehouseAutonomous", "01_1"));
//        addState(new TurretArmExtension(robot, robot.whiteArmBobbin, "RedWarehouseAutonomous", "02_0"));
//        addState(new TurretArmRiser(robot, robot.whiteArmRiser, "RedWarehouseAutonomous", "03_0_middle"));
//        addState(new TurretArmExtension(robot, robot.whiteArmBobbin, "RedWarehouseAutonomous", "04_0"));
        addState(new CollectorToggle(robot, robot.collectorWhite, "RedWarehouseAutonomous", "05_0"));
        addState(new CollectorToggle(robot, robot.collectorWhite, "RedWarehouseAutonomous", "06_0"));
        addState(new TurretArmExtension(robot, robot.whiteArmBobbin, "RedWarehouseAutonomous", "07_0"));
        addState(new DriveState(robot,"RedWarehouseAutonomous", "08_0"));
        addState(new DriveState(robot, "RedWarehouseAutonomous", "09_0"));
        addState(new TurretOrbit(robot, robot.turretServoWhite, null, "RedWarehouseAutonomous", "10_0"));
        addState(new DriveState(robot, "RedWarehouseAutonomous", "10_1"));

    }

    public void loop() {
        super.loop();

        telemetry.addData("white arm extension", robot.whiteArmBobbin.getCurrentPosition());
        telemetry.addData("White Riser Arm", robot.whiteArmRiser.getCurrentPosition());
        telemetry.addData("white Turret Switch", robot.whiteMag.isPressed());
        telemetry.addData("white Turret orbit Power", robot.turretServoWhite.getPower());
        telemetry.addData("White Door Position", robot.whiteDispenser.getPosition());
        telemetry.addLine();

        telemetry.addData("Orange Riser Arm", robot.orangeArmRiser.getCurrentPosition());
        telemetry.addData("orange arm extension", robot.orangeArmBobbin.getCurrentPosition());
        telemetry.addData("orange Turret Switch", robot.orangeMag.isPressed());
        telemetry.addData("orange Turret Orbit Power", robot.turretServoOrange.getPower());
        telemetry.addData("Orange Door Position", robot.orangeDispenser.getPosition());
        telemetry.addLine();

        telemetry.addData("driveWarehouseLeft", robot.driveWarehouseLeft.getCurrentPosition());
        telemetry.addData("driveWarehouseRight", robot.driveWarehouseRight.getCurrentPosition());
        telemetry.addData("driveGoalLeft", robot.driveGoalLeft.getCurrentPosition());
        telemetry.addData("driveGoalRight", robot.driveGoalRight.getCurrentPosition());
    }
}
