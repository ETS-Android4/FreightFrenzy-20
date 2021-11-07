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


        if (engine.gamepad1.right_bumper){
            robot.collectorDispenser.setPosition(.5);
        } else {
            robot.collectorDispenser.setPosition(0);
        }

        robot.collectorArmBobbin.setPower(engine.gamepad1.right_trigger * maxCollectorArmSpeed);

        if (engine.gamepad1.right_trigger <= 0){
            robot.collectorArmBobbin.setPower(-engine.gamepad1.left_trigger * maxCollectorArmSpeed);
        }


        // GamePad 2
        robot.depositorArmBobbin.setPower(engine.gamepad2.right_trigger * maxDepositorArmSpeed);

        if (engine.gamepad2.right_trigger <= 0) {
            robot.depositorArmBobbin.setPower(-engine.gamepad2.left_trigger * maxDepositorArmSpeed);
        }


        if (engine.gamepad2.left_bumper){
            robot.depositorDispenser.setPosition(.5);
        } else {
            robot.depositorDispenser.setPosition(0);
        }




    }


}
