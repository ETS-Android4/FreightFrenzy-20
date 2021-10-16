package org.timecrafters.FreightFrenzy.HardwareTesting.States;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.cyberarm.engine.V2.CyberarmState;
import org.timecrafters.TimeCraftersConfigurationTool.library.TimeCraftersConfiguration;

public class DriveTrainTestState extends CyberarmState {

    //here, you'll find some of your variables. you can add more as you need them.
    DcMotor driveFrontRight;
    DcMotor driveBackRight;
    DcMotor driveFrontLeft;
    DcMotor driveBackLeft;

    TimeCraftersConfiguration configuration;

    double maxSpeed;

    //This is the constructor. It lets other code bits run use the code you put here
    public DriveTrainTestState() {
        configuration = new TimeCraftersConfiguration();
        maxSpeed = configuration.variable("testing", "teleop", "maxSpeed").value();
    }

    @Override
    public void init() {
        driveFrontRight = engine.hardwareMap.dcMotor.get("driveFrontRight");
        driveFrontLeft = engine.hardwareMap.dcMotor.get("driveFrontLeft");
        driveBackRight = engine.hardwareMap.dcMotor.get("driveBackRight");
        driveBackLeft = engine.hardwareMap.dcMotor.get("driveBackLeft");

        driveFrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        driveBackLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    //This is a method. methods are bits of code that can be run elsewhere. 
    //This one is set up to repeat every few milliseconds
    @Override
    public void exec() {

        driveFrontRight.setPower(-engine.gamepad1.right_stick_y * maxSpeed);
        driveBackRight.setPower(-engine.gamepad1.right_stick_y * maxSpeed);
        driveFrontLeft.setPower(-engine.gamepad1.left_stick_y * maxSpeed);
        driveBackLeft.setPower(-engine.gamepad1.left_stick_y * maxSpeed);

    }
}