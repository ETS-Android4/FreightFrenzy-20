package org.timecrafters.FreightFrenzy.Competition.Autonomous.States;

import org.cyberarm.engine.V2.CyberarmState;
import org.timecrafters.FreightFrenzy.Competition.Common.Robot;

public class FaceState extends CyberarmState {
    private double faceDirection, tolerance, power;
    private Robot robot;
    public FaceState(Robot robot, double faceDirection, double tolerance, double power) {
        this.robot = robot;
        this.faceDirection = faceDirection;
        this.tolerance = tolerance;
        this.power = power;
    }

    @Override
    public void exec() {
        robot.updateRobotOrientation();

        if (robot.heading() - tolerance > faceDirection) {
            robot.driveGoalLeft.setPower(power);
            robot.driveWarehouseLeft.setPower(power);
            robot.driveGoalRight.setPower(0);
            robot.driveWarehouseRight.setPower(0);
        }

        else if (robot.heading() + tolerance < faceDirection){
            robot.driveGoalLeft.setPower(0);
            robot.driveWarehouseLeft.setPower(0);
            robot.driveGoalRight.setPower(power);
            robot.driveWarehouseRight.setPower(power);
        }

        else {
            robot.driveGoalLeft.setPower(0);
            robot.driveWarehouseLeft.setPower(0);
            robot.driveGoalRight.setPower(0);
            robot.driveWarehouseRight.setPower(0);
            setHasFinished(true);
        }
    }
}
