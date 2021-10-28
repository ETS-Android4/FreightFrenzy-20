package org.timecrafters.FreightFrenzy.Competition.Common;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.cyberarm.engine.V2.CyberarmEngine;
import org.timecrafters.TimeCraftersConfigurationTool.library.TimeCraftersConfiguration;

public class Robot {
    private final CyberarmEngine engine;
    public TimeCraftersConfiguration configuration;

    // Shiny
    public RevBlinkinLedDriver leds;

    // Sensors
    public BNO055IMU imu;

    // Drivetrain
    public static final double WHEEL_CIRCUMFERENCE = Math.PI * 8;
    public static final int COUNTS_PER_REVOLUTION = 1000;

    public DcMotor driveFrontLeft, driveFrontRight, driveBackLeft, driveBackRight;

    // Collector
    public DcMotor collectorBobbin;
    public Servo collectorDoor;

    // Depositor
    public DcMotor depositorBobbin;
    public Servo depositorDoor;

    public Robot(CyberarmEngine engine) {
        this.engine = engine;

        initConfiguration();
        initBlinkin();
        initIMU();
        initDrivetrain();
        initCollector();
        initDepositor();
        initCarousel();
    }

    public double rotation() {
        return imu.getAngularOrientation().firstAngle;
    }

    public double pitch() {
        return imu.getAngularOrientation().secondAngle;
    }

    public double roll() {
        return imu.getAngularOrientation().thirdAngle;
    }

    public double ticksToInches(int ticks) {
        return ticks * (WHEEL_CIRCUMFERENCE / COUNTS_PER_REVOLUTION);
    }

    public double inchesToTicks(double inches) {
        return inches * (COUNTS_PER_REVOLUTION / WHEEL_CIRCUMFERENCE);
    }

    private void initConfiguration() {
        this.configuration = new TimeCraftersConfiguration();
    }

    private void initBlinkin() {
//        engine.hardwareMap.get(RevBlinkinLedDriver.class, "leds");
    }

    private void initIMU() {
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        parameters.mode = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled = false;

        this.imu = engine.hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
    }

    private void initDrivetrain() {
        driveFrontRight = engine.hardwareMap.dcMotor.get("driveFrontRight");
        driveFrontLeft = engine.hardwareMap.dcMotor.get("driveFrontLeft");
        driveBackRight = engine.hardwareMap.dcMotor.get("driveBackRight");
        driveBackLeft = engine.hardwareMap.dcMotor.get("driveBackLeft");

        driveFrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        driveBackLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    private void initCollector() {

    }

    private void initDepositor(){

    }

    private void initCarousel() {

    }
}
