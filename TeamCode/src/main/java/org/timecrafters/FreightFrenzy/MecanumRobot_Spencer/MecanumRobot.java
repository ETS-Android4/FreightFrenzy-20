package org.timecrafters.FreightFrenzy.MecanumRobot_Spencer;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


import org.cyberarm.engine.V2.CyberarmEngine;
public class MecanumRobot {

    private final CyberarmEngine engine;

    // Drivetrain

    public DcMotor driveFrontLeft, driveFrontRight, driveBackLeft, driveBackRight;



    public MecanumRobot(CyberarmEngine engine) {
        this.engine = engine;
        initDrivetrain();
    }
    private void initDrivetrain() {

        driveFrontRight = engine.hardwareMap.dcMotor.get("driveFrontRight");
        driveFrontLeft  = engine.hardwareMap.dcMotor.get("driveFrontLeft");
        driveBackRight  = engine.hardwareMap.dcMotor.get("driveBackRight");
        driveBackLeft   = engine.hardwareMap.dcMotor.get("driveBackLeft");

        driveFrontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        driveFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);

        driveBackLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        driveBackRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }
}


