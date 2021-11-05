package org.timecrafters.FreightFrenzy.Competition.TeleOp.States;

import org.cyberarm.engine.V2.CyberarmState;
import org.timecrafters.FreightFrenzy.Competition.Common.Robot;

public class TeleOpState extends CyberarmState {
    Robot robot;
    double maxDriveSpeed;

    public TeleOpState(Robot robot) {
        this.robot = robot;
     maxDriveSpeed = 0.7;
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void exec() {
        robot.driveWarehouseLeft.setPower(-engine.gamepad1.left_stick_y * maxDriveSpeed);
        robot.driveWarehouseRight.setPower(-engine.gamepad1.right_stick_y * maxDriveSpeed);
        robot.driveGoalLeft.setPower(-engine.gamepad1.left_stick_y * maxDriveSpeed);
        robot.driveGoalRight.setPower(-engine.gamepad1.right_stick_y * maxDriveSpeed);

        if (engine.gamepad1.left_bumper){
            robot.depositorDispenser.setPosition(.5);
        } else {
            robot.depositorDispenser.setPosition(0);
        }

        if (engine.gamepad1.right_bumper){
            robot.collectorDispenser.setPosition(.5);
        } else {
            robot.collectorDispenser.setPosition(0);
        }
    }


}
