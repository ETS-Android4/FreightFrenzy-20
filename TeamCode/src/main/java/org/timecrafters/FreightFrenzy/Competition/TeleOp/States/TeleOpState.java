package org.timecrafters.FreightFrenzy.Competition.TeleOp.States;

import org.cyberarm.engine.V2.CyberarmState;
import org.timecrafters.FreightFrenzy.Competition.Common.Robot;

public class TeleOpState extends CyberarmState {
    Robot robot;
    double maxDriveSpeed, maxCollectorArmSpeed, maxDepositorArmSpeed;


    public TeleOpState(Robot robot) {
        this.robot = robot;
     maxDriveSpeed = 1;
     maxCollectorArmSpeed = 0.7;
     maxDepositorArmSpeed = 0.7;
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void exec() {
        // GamePad 1

        // TankDrive
        robot.driveWarehouseLeft.setPower(-engine.gamepad1.left_stick_y * maxDriveSpeed);
        robot.driveGoalLeft.setPower(-engine.gamepad1.left_stick_y * maxDriveSpeed);

        robot.driveWarehouseRight.setPower(-engine.gamepad1.right_stick_y * maxDriveSpeed);
        robot.driveGoalRight.setPower(-engine.gamepad1.right_stick_y * maxDriveSpeed);

        // dispenser powered
        if (engine.gamepad1.right_bumper){
            robot.collectorDispenser.setPosition(.5);
        }

        // if not pressed dispenser off
        else {
            robot.collectorDispenser.setPosition(0);
        }
        //if one of triggers pressed arm extends or unextends
        robot.collectorArmBobbin.setPower(engine.gamepad1.right_trigger * maxCollectorArmSpeed);

        if (engine.gamepad1.right_trigger <= 0){
            robot.collectorArmBobbin.setPower(-engine.gamepad1.left_trigger * maxCollectorArmSpeed);
        }

        //  if either of these buttons move the servo
        //  turretServo1 = orange
        if (engine.gamepad1.dpad_right || engine.gamepad1.dpad_left) {

            if (engine.gamepad1.dpad_right) {
                robot.turretServo1.setPower(1);
            }

            if (engine.gamepad1.dpad_left) {
                robot.turretServo1.setPower(-1);
            }
        }
        //  if neither of these buttons power off
        else {
            robot.turretServo1.setPower(0);
        }


        // GamePad 2

        // if triggers are pressed then arm extends or unextends
        robot.depositorArmBobbin.setPower(engine.gamepad2.right_trigger * maxDepositorArmSpeed);

        if (engine.gamepad2.right_trigger <= 0) {
            robot.depositorArmBobbin.setPower(-engine.gamepad2.left_trigger * maxDepositorArmSpeed);
        }


        if (engine.gamepad2.left_bumper){
            robot.depositorDispenser.setPosition(.5);
        } else {
            robot.depositorDispenser.setPosition(0);
        }


        //  if either of these buttons move the servo
        //  turretServo2 = white
        if (engine.gamepad2.dpad_right || engine.gamepad2.dpad_left) {

            if (engine.gamepad2.dpad_right) {
                robot.turretServo2.setPower(1);
            }

            if (engine.gamepad2.dpad_left) {
                robot.turretServo2.setPower(-1);
            }
        }
        //  if neither of these buttons power off
        else {
            robot.turretServo2.setPower(0);
        }



    }


}
