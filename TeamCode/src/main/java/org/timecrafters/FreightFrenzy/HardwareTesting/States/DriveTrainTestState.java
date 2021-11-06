package org.timecrafters.FreightFrenzy.HardwareTesting.States;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.cyberarm.engine.V2.CyberarmState;
import org.timecrafters.FreightFrenzy.Competition.Common.Robot;
import org.timecrafters.TimeCraftersConfigurationTool.library.TimeCraftersConfiguration;

public class DriveTrainTestState extends CyberarmState {

    //here, you'll find some of your variables. you can add more as you need them.
    final Robot robot;

    double maxSpeed;

    //This is the constructor. It lets other code bits run use the code you put here
    public DriveTrainTestState(Robot robot) {
        this.robot = robot;

        maxSpeed = robot.configuration.variable("testing", "teleop", "maxSpeed").value();
    }

    @Override
    public void init() {
    }

    //This is a method. methods are bits of code that can be run elsewhere. 
    //This one is set up to repeat every few milliseconds
    @Override
    public void exec() {
//
//        robot.driveFrontRight.setPower(-engine.gamepad1.right_stick_y * maxSpeed);
//        robot.driveBackRight.setPower(-engine.gamepad1.right_stick_y * maxSpeed);
//        robot.driveFrontLeft.setPower(-engine.gamepad1.left_stick_y * maxSpeed);
//        robot.driveBackLeft.setPower(-engine.gamepad1.left_stick_y * maxSpeed);
    }
}