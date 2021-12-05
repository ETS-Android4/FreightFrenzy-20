package org.timecrafters.FreightFrenzy.Competition.Common;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XYZ;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesReference.EXTRINSIC;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.cyberarm.engine.V2.CyberarmEngine;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.timecrafters.TimeCraftersConfigurationTool.library.TimeCraftersConfiguration;

import java.util.ArrayList;
import java.util.List;

public class Robot {
    private static final float mmPerInch        = 25.4f;
    private static final float mmTargetHeight   = 6 * mmPerInch;  // the height of the center of the target image above the floor
    private static final float halfField        = 72 * mmPerInch;
    private static final float halfTile         = 12 * mmPerInch;
    private static final float oneAndHalfTile   = 36 * mmPerInch;

    private static final String TENSORFLOW_MODEL_ASSET = "FreightFrenzy_BCDM.tflite";
    private static final String[] TENSORFLOW_MODEL_LABELS = {
            "Ball",
            "Cube",
            "Duck",
            "Marker"
    };
    private static final String VUFORIA_KEY = "Abmu1jv/////AAABmYzrcgDEi014nv+wD6PkEPVnOlV2pI3S9sGUMMR/X7hF72x20rP1JcVtsU0nI6VK0yUlYbCSA2k+yMo4hQmPDBvrqeqAgXKa57ilPhW5e1cB3BEevP+9VoJ9QYFhKA3JJTiuFS50WQeuFy3dp0gOPoqHL3XClRFZWbhzihyNnLXgXlKiq+i5GbfONECucQU2DgiuuxYlCaeNdUHl1X5C2pO80zZ6y7PYAp3p0ciXJxqfBoVAklhd69avaAE5Z84ctKscvcbxCS16lq81X7XgIFjshLoD/vpWa300llDG83+Y777q7b5v7gsUCZ6FiuK152Rd272HLuBRhoTXAt0ug9Baq5cz3sn0sAIEzSHX1nah";
    private static final float VUFORIA_CAMERA_FORWARD_DISPLACEMENT  = 0.0f * mmPerInch; // Camera lens forward distance from robot center
    private static final float VUFORIA_CAMERA_VERTICAL_DISPLACEMENT = 0.0f * mmPerInch; // Camera lens height above ground
    private static final float VUFORIA_CAMERA_LEFT_DISPLACEMENT     = 0.0f * mmPerInch; // Camera lens left distance from robot center

    private final CyberarmEngine engine;
    public TimeCraftersConfiguration configuration;

    // Shiny
    public RevBlinkinLedDriver leds;
    public TFObjectDetector tensorflow;
    private List<Recognition> tensorflowRecognitions = new ArrayList<>();
    public VuforiaLocalizer vuforia;
    private VuforiaTrackables vuforiaTargets;
    private List<VuforiaTrackable> vuforiaTrackables;
    private OpenGLMatrix vuforiaCameraLocationOnRobot = OpenGLMatrix
            .translation(VUFORIA_CAMERA_FORWARD_DISPLACEMENT, VUFORIA_CAMERA_LEFT_DISPLACEMENT, VUFORIA_CAMERA_VERTICAL_DISPLACEMENT)
            .multiplied(Orientation.getRotationMatrix(AxesReference.EXTRINSIC, AxesOrder.XZY, AngleUnit.DEGREES, 90, 90, 0));
    private RobotLocation vuforiaLastLocation;
    private Orientation orientation;

    // Sensors
    public BNO055IMU imu;
    public RevTouchSensor whiteMag;
    public RevTouchSensor orangeMag;

    // Drivetrain
    public static final double WHEEL_CIRCUMFERENCE = Math.PI * 8;
    public static final int COUNTS_PER_REVOLUTION = 1000;

    public DcMotor driveWarehouseLeft, driveWarehouseRight, driveGoalLeft, driveGoalRight,
            orangeArmRiser, orangeArmBobbin, whiteArmRiser, whiteArmBobbin;

    // Collector
    public Servo orangeDispenser;
    public CRServo turretServoOrange;

    // Depositor
    public Servo whiteDispenser;
    // Depositor, and carousel
    public CRServo turretServoWhite, carouselWheel, collectorOrange, collectorWhite;



    public Robot(CyberarmEngine engine) {
        this.engine = engine;
        initMagnetSwitches();
        initConfiguration();
        initBlinkin();
        initIMU();
        initDrivetrain();
        initCollector();
        initDepositor();
        initCarousel();
//        initVuforia();
//        initTensorflow();
    }

    private void initMagnetSwitches() {
        whiteMag = engine.hardwareMap.get(RevTouchSensor.class, "whiteMag");
        orangeMag = engine.hardwareMap.get(RevTouchSensor.class, "orangeMag");
    }

    public double heading() {
        return orientation.firstAngle;
    }

    public double roll() {
        return orientation.secondAngle;
    }

    public double pitch() {
        return orientation.thirdAngle;
    }

    public void updateRobotOrientation(){
        orientation = imu.getAngularOrientation();
    }

    public void activateTensorflow() {
        tensorflow.activate();
    }

    public List<Recognition> tensorflowDetections() {
        List<Recognition> updateRecognitions = tensorflow.getUpdatedRecognitions();

        if (updateRecognitions != null) {
            tensorflowRecognitions = updateRecognitions;
        }

        return tensorflowRecognitions;
    }

    public void deactivateTensorflow() {
        tensorflow.deactivate();
    }

    public void activateVuforia() {
        vuforiaTargets.activate();
    }

    public RobotLocation vuforiaLocation() {
        for (VuforiaTrackable trackable : vuforiaTrackables) {
            if (((VuforiaTrackableDefaultListener)trackable.getListener()).isVisible()) {
                OpenGLMatrix robotLocationTransform = ((VuforiaTrackableDefaultListener)trackable.getListener()).getUpdatedRobotLocation();
                if (robotLocationTransform != null) {
                    vuforiaLastLocation = new RobotLocation(robotLocationTransform);
                }

                // Recycle old position since there is no new data
                return vuforiaLastLocation;
            }
        }

        // Return null if there is no visible trackable
        return null;
    }

    private void vuforiaIdentifyTarget(int targetIndex, String targetName, float dx, float dy, float dz, float rx, float ry, float rz) {
        VuforiaTrackable aTarget = vuforiaTargets.get(targetIndex);
        aTarget.setName(targetName);
        aTarget.setLocation(OpenGLMatrix.translation(dx, dy, dz)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, rx, ry, rz)));
    }

    public void deactivateVuforia() {
        vuforiaTargets.deactivate();
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

        imu.startAccelerationIntegration(new Position(), new Velocity(), 10);
    }

    private void initDrivetrain() {
        driveWarehouseRight = engine.hardwareMap.dcMotor.get("driveWarehouseRight");
        driveWarehouseLeft  = engine.hardwareMap.dcMotor.get("driveWarehouseLeft");
        driveGoalRight      = engine.hardwareMap.dcMotor.get("driveGoalRight");
        driveGoalLeft       = engine.hardwareMap.dcMotor.get("driveGoalLeft");

        driveWarehouseLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        driveWarehouseRight.setDirection(DcMotorSimple.Direction.REVERSE);

        driveGoalLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        driveGoalRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    private void initCollector() {
        orangeArmBobbin = engine.hardwareMap.dcMotor.get("orangeArmBobbin");
        orangeDispenser = engine.hardwareMap.servo.get("orangeDispenser");
        orangeArmBobbin.setDirection(DcMotorSimple.Direction.REVERSE);
        turretServoOrange = engine.hardwareMap.crservo.get("turretServoOrange");
        orangeArmRiser = engine.hardwareMap.dcMotor.get("orangeArmRiser");
        orangeArmRiser.setDirection(DcMotorSimple.Direction.FORWARD);
        orangeArmRiser.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        collectorOrange = engine.hardwareMap.crservo.get("collectorOrange");

        orangeDispenser.setPosition(0.5);
    }

    private void initDepositor(){
        whiteDispenser = engine.hardwareMap.servo.get("whiteDispenser");
        whiteArmBobbin = engine.hardwareMap.dcMotor.get("whiteArmBobbin");
        whiteArmBobbin.setDirection(DcMotorSimple.Direction.REVERSE);
        turretServoWhite = engine.hardwareMap.crservo.get("turretServoWhite");
        whiteArmRiser = engine.hardwareMap.dcMotor.get("whiteArmRiser");
        whiteArmRiser.setDirection(DcMotorSimple.Direction.FORWARD);
        whiteArmRiser.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        collectorWhite = engine.hardwareMap.crservo.get("collectorWhite");
    }

    private void initCarousel() {
        carouselWheel = engine.hardwareMap.crservo.get("carouselWheel");
    }

    private void initVuforia() {
        int cameraMonitorViewId = engine.hardwareMap.appContext.getResources().getIdentifier(
                "cameraMonitorViewId", "id", engine.hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = engine.hardwareMap.get(WebcamName.class, "Webcam 1");
        parameters.useExtendedTracking = false;

        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        vuforiaTargets = vuforia.loadTrackablesFromAsset("FreightFrenzy");

        vuforiaTrackables = new ArrayList<>(vuforiaTargets);

        // Name and locate each trackable object
        vuforiaIdentifyTarget(0, "Blue Storage",       -halfField,  oneAndHalfTile, mmTargetHeight, 90, 0, 90);
        vuforiaIdentifyTarget(1, "Blue Alliance Wall",  halfTile,   halfField,      mmTargetHeight, 90, 0, 0);
        vuforiaIdentifyTarget(2, "Red Storage",        -halfField, -oneAndHalfTile, mmTargetHeight, 90, 0, 90);
        vuforiaIdentifyTarget(3, "Red Alliance Wall",   halfTile,  -halfField,      mmTargetHeight, 90, 0, 180);

        for (VuforiaTrackable trackable : vuforiaTrackables) {
            ((VuforiaTrackableDefaultListener) trackable.getListener())
                    .setCameraLocationOnRobot(parameters.cameraName, vuforiaCameraLocationOnRobot);
        }
    }

    private void initTensorflow() {
        int tfodMonitorViewId = engine.hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", engine.hardwareMap.appContext.getPackageName());

        TFObjectDetector.Parameters parameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        parameters.minResultConfidence = 0.8f;
        parameters.isModelTensorFlow2 = true;
        parameters.inputSize = 320;

        tensorflow = ClassFactory.getInstance().createTFObjectDetector(parameters, vuforia);
        tensorflow.loadModelFromAsset(TENSORFLOW_MODEL_ASSET, TENSORFLOW_MODEL_LABELS);
    }
}
