package org.timecrafters.FreightFrenzy.Competition.TeleOp.States;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.cyberarm.engine.V2.CyberarmState;
import org.timecrafters.FreightFrenzy.Competition.Common.Robot;

public class TeleOpState extends CyberarmState {
    Robot robot;
    double maxDriveSpeed, maxCollectorArmSpeed, maxDepositorArmSpeed;
    int maxArmTravelDistance;


    public TeleOpState(Robot robot) {
        this.robot = robot;
     maxDriveSpeed = 1;
     maxCollectorArmSpeed = 0.1;
     maxDepositorArmSpeed = 0.1;
     maxArmTravelDistance = 4000;
    }

    @Override
    public void start() {
        super.start();
        // FIXME: Don't reset when doing autonomous stuff
        // FIXME: Fix unable to retract if reset mid-match
        robot.whiteArmBobbin.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.whiteArmBobbin.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.orangeArmBobbin.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.orangeArmBobbin.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
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
        if (engine.gamepad1.b){
            robot.orangeDispenser.setPosition(.5);
        }

        // if not pressed dispenser off
        else {
            robot.orangeDispenser.setPosition(0);
        }
        //  if one of triggers pressed arm extends or unextends, there is also a limit on how far arm can extend
        if (robot.orangeArmBobbin.getCurrentPosition() <= maxArmTravelDistance && engine.gamepad1.left_trigger > 0) {

            robot.orangeArmBobbin.setPower(engine.gamepad1.left_trigger * maxDepositorArmSpeed);
        } else {
            if (engine.gamepad1.left_trigger <= 0 && robot.orangeArmBobbin.getCurrentPosition() >= 0) {
                robot.orangeArmBobbin.setPower(-engine.gamepad1.right_trigger * maxDepositorArmSpeed);
            } else {
                robot.orangeArmBobbin.setPower(0);
            }

        }

        //  if either of these buttons... move the servo
        //  turretServo1 = orange
        if (engine.gamepad1.dpad_right || engine.gamepad1.dpad_left) {

            if (engine.gamepad1.dpad_right) {
                robot.turretServoOrange.setPower(-1);
            }

            if (engine.gamepad1.dpad_left) {
                robot.turretServoOrange.setPower(1);
            }
        }

        //  if neither of these buttons... power off
        else {
            robot.turretServoOrange.setPower(0);
        }

        // if dpad verticles pressed arm rises or lowers...
        if (engine.gamepad1.dpad_up || engine.gamepad1.dpad_down) {

            if (engine.gamepad1.dpad_up) {
                robot.orangeArmRiser.setPower(1);
            }

            if (engine.gamepad1.dpad_down) {
                robot.orangeArmRiser.setPower(-1);
            }
        }
        // nothing pressed nothing move...
        else {
            robot.orangeArmRiser.setPower(0);
        }

        // if bumpers pressed intake wheel move...

        if (engine.gamepad1.right_bumper || engine.gamepad1.left_bumper) {
            if (engine.gamepad1.right_bumper) {
                robot.collectorOrange.setPower(1);
            }

            if (engine.gamepad1.left_bumper) {
                robot.collectorOrange.setPower(-1);
            }
        }
        // no bumpers pressed no wheel move...
        else {
            robot.collectorOrange.setPower(0);
        }

        // button x pressed carousel wheel move.

        if (engine.gamepad1.x){
            robot.carouselWheel.setPower(1);
        }

        else {
            robot.carouselWheel.setPower(0);
        }

        // GamePad 2

        // if triggers are pressed then arm extends or unextends, there is also a limit on how far arm can extend
        if (robot.whiteArmBobbin.getCurrentPosition() <= maxArmTravelDistance && engine.gamepad2.left_trigger > 0) {

            robot.whiteArmBobbin.setPower(engine.gamepad2.left_trigger * maxDepositorArmSpeed);
        } else {
            if (engine.gamepad2.left_trigger <= 0 && robot.whiteArmBobbin.getCurrentPosition() >= 0) {
                robot.whiteArmBobbin.setPower(-engine.gamepad2.right_trigger * maxDepositorArmSpeed);
            } else {
                robot.whiteArmBobbin.setPower(0);
            }

        }



        // if b is pressed then depositor door opens, if not pressed not opening.
        if (engine.gamepad2.b){
            robot.whiteDispenser.setPosition(.5);
        }

        else {
            robot.whiteDispenser.setPosition(0);
        }

        //  if either of these buttons move the servo
        //  turretServo2 = white
        if (engine.gamepad2.dpad_right || engine.gamepad2.dpad_left) {

            if (engine.gamepad2.dpad_right) {
                robot.turretServoWhite.setPower(-1);
            }

            if (engine.gamepad2.dpad_left) {
                robot.turretServoWhite.setPower(1);
            }
        }
        //  if neither of these buttons power off
        else {
            robot.turretServoWhite.setPower(0);
        }

        // if dpad verticles pressed arm rises or lowers
        if (engine.gamepad2.dpad_down || engine.gamepad2.dpad_up) {

            if (engine.gamepad2.dpad_up) {
                robot.whiteArmRiser.setPower(-1);
            }

            if (engine.gamepad2.dpad_down) {
                robot.whiteArmRiser.setPower(1);
            }
        }
        // nothing pressed nothing move...
        else {
            robot.whiteArmRiser.setPower(0);
        }

        if (engine.gamepad2.left_bumper || engine.gamepad2.right_bumper) {

            if (engine.gamepad2.left_bumper) {
                robot.collectorWhite.setPower(-1);
            }

            if (engine.gamepad2.right_bumper) {
                robot.collectorWhite.setPower(1);
            }
        }

        else {
            robot.collectorWhite.setPower(0);
        }

    }


    @Override
    public void telemetry() {
        engine.telemetry.addData("white arm extension", robot.whiteArmBobbin.getCurrentPosition());
    }
}
