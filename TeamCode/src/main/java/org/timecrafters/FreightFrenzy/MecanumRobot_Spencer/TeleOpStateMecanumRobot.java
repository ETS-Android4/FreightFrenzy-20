package org.timecrafters.FreightFrenzy.MecanumRobot_Spencer;

import org.cyberarm.engine.V2.CyberarmState;
import org.timecrafters.FreightFrenzy.MecanumRobot_Spencer.MecanumRobot;

public class TeleOpStateMecanumRobot extends CyberarmState {

    MecanumRobot robot;

    public TeleOpStateMecanumRobot(MecanumRobot mecanumRobot) {

    }

    @Override
    public void exec() {

        // Slow Mode

        if (engine.gamepad1.left_trigger > 0.5){
        // TankDrive
        // Left joystick foward; Left side foward;

        robot.driveFrontLeft.setPower(engine.gamepad1.left_stick_y * 0.5);
        robot.driveBackLeft.setPower(engine.gamepad1.left_stick_y * 0.5);

        // Right joystick foward; Right side foward;
        robot.driveFrontRight.setPower(engine.gamepad1.right_stick_y * 0.5);
        robot.driveBackRight.setPower(engine.gamepad1.right_stick_y * 0.5);


        // Strafing left and right

        if (engine.gamepad1.left_bumper) {
            robot.driveFrontLeft.setPower(0.5);
            robot.driveBackLeft.setPower(-0.5);
            robot.driveFrontRight.setPower(-0.5);
            robot.driveBackRight.setPower(0.5);
        } else if (engine.gamepad1.right_bumper) {
            robot.driveFrontLeft.setPower(-0.5);
            robot.driveBackLeft.setPower(0.5);
            robot.driveFrontRight.setPower(0.5);
            robot.driveBackRight.setPower(-0.5);
        } else {
            robot.driveFrontLeft.setPower(engine.gamepad1.left_stick_y * 0.5);
            robot.driveBackLeft.setPower(engine.gamepad1.left_stick_y * 0.5);
            robot.driveFrontRight.setPower(engine.gamepad1.right_stick_y * 0.5);
            robot.driveBackRight.setPower(engine.gamepad1.right_stick_y * 0.5);

            }
        }
    // Normal Speed

        else if (engine.gamepad1.right_stick_button){

            // left stick foward; right side foward
            robot.driveFrontLeft.setPower(engine.gamepad1.left_stick_y * 1);
            robot.driveBackLeft.setPower(engine.gamepad1.left_stick_y * 1);

            // right stick foward; right side foward;
            robot.driveFrontRight.setPower(engine.gamepad1.right_stick_y * 1);
            robot.driveBackRight.setPower(engine.gamepad1.right_stick_y * 1);


            // Strafing left and right

            if (engine.gamepad1.left_bumper) {
                robot.driveFrontLeft.setPower(1);
                robot.driveBackLeft.setPower(-1);
                robot.driveFrontRight.setPower(-1);
                robot.driveBackRight.setPower(1);
            } else if (engine.gamepad1.right_bumper) {
                robot.driveFrontLeft.setPower(-1);
                robot.driveBackLeft.setPower(1);
                robot.driveFrontRight.setPower(1);
                robot.driveBackRight.setPower(-1);
            } else {
                robot.driveFrontLeft.setPower(engine.gamepad1.left_stick_y * 1);
                robot.driveBackLeft.setPower(engine.gamepad1.left_stick_y * 1);
                robot.driveFrontRight.setPower(engine.gamepad1.right_stick_y * 1);
                robot.driveBackRight.setPower(engine.gamepad1.right_stick_y * 1);

            }
        }

        else {
            // left stick foward; right side foward
            robot.driveFrontLeft.setPower(engine.gamepad1.left_stick_y * 1);
            robot.driveBackLeft.setPower(engine.gamepad1.left_stick_y * 1);

            // right stick foward; right side foward;
            robot.driveFrontRight.setPower(engine.gamepad1.right_stick_y * 1);
            robot.driveBackRight.setPower(engine.gamepad1.right_stick_y * 1);


            // Strafing left and right

            if (engine.gamepad1.left_bumper) {
                robot.driveFrontLeft.setPower(1);
                robot.driveBackLeft.setPower(-1);
                robot.driveFrontRight.setPower(-1);
                robot.driveBackRight.setPower(1);
            } else if (engine.gamepad1.right_bumper) {
                robot.driveFrontLeft.setPower(-1);
                robot.driveBackLeft.setPower(1);
                robot.driveFrontRight.setPower(1);
                robot.driveBackRight.setPower(-1);
            } else {
                robot.driveFrontLeft.setPower(engine.gamepad1.left_stick_y * 1);
                robot.driveBackLeft.setPower(engine.gamepad1.left_stick_y * 1);
                robot.driveFrontRight.setPower(engine.gamepad1.right_stick_y * 1);
                robot.driveBackRight.setPower(engine.gamepad1.right_stick_y * 1);

            }
        }
    }
}
