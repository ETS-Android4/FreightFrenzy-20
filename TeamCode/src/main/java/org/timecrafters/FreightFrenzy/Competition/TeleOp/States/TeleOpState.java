package org.timecrafters.FreightFrenzy.Competition.TeleOp.States;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.cyberarm.engine.V2.CyberarmState;
import org.timecrafters.FreightFrenzy.Competition.Common.Robot;

public class TeleOpState extends CyberarmState {
    Robot robot;
    double maxDriveSpeed, maxCollectorArmSpeed, maxDepositorArmSpeed;
    int maxArmTravelDistance, minArmTravelDistance;
    boolean armLimitToggle = false, orangeCollectorToggle = false, whiteCollectorToggle = false;
    boolean allianceRedDriver1 = true, allianceRedDriver2 = true;

    public TeleOpState(Robot robot) {
        this.robot = robot;
     maxDriveSpeed = 1;
     maxCollectorArmSpeed = 1;
     maxDepositorArmSpeed = 1;
     minArmTravelDistance = 0;
     maxArmTravelDistance = 4000;

    }


    @Override
    public void start() {
        super.start();
        // FIXME: Don't reset when doing autonomous stuff
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
            robot.orangeDispenser.setPosition(0);
        }
        // if not pressed dispenser off
        else {
            robot.orangeDispenser.setPosition(0.5);
        }

        //  if one of triggers pressed arm extends or unextends, there is also a limit on how far arm can extend
        if (robot.orangeArmBobbin.getCurrentPosition() <= maxArmTravelDistance && engine.gamepad1.left_trigger > 0) {

            robot.orangeArmBobbin.setPower(engine.gamepad1.left_trigger * maxDepositorArmSpeed);
        } else {
            if (engine.gamepad1.left_trigger <= 0 && robot.orangeArmBobbin.getCurrentPosition() >= minArmTravelDistance) {
                robot.orangeArmBobbin.setPower(-engine.gamepad1.right_trigger * maxDepositorArmSpeed);
            } else {
                robot.orangeArmBobbin.setPower(0);
            }

        }

        turretOrbitControl(engine.gamepad1, robot.turretServoOrange, allianceRedDriver1);

        // if dpad verticles pressed arm rises or lowers...
        if (engine.gamepad1.dpad_up || engine.gamepad1.dpad_down) {

            if (engine.gamepad1.dpad_up) {
                robot.orangeArmRiser.setPower(0.8);
            }

            if (engine.gamepad1.dpad_down) {
                robot.orangeArmRiser.setPower(-0.5);
            }
        }
        // nothing pressed nothing move...
        else {
            robot.orangeArmRiser.setPower(0);
        }

        // button x pressed carousel wheel move.

        if (engine.gamepad2.x){
            robot.carouselWheel.setPower(0.5);
        }
        else if (engine.gamepad2.a){
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
            if (engine.gamepad2.left_trigger <= 0 && robot.whiteArmBobbin.getCurrentPosition() >= minArmTravelDistance) {
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


        turretOrbitControl(engine.gamepad2, robot.turretServoWhite, allianceRedDriver2);
        // if dpad verticles pressed arm rises or lowers
        if (engine.gamepad2.dpad_down || engine.gamepad2.dpad_up) {

            if (engine.gamepad2.dpad_up) {
                robot.whiteArmRiser.setPower(8);
            }

            if (engine.gamepad2.dpad_down) {
                robot.whiteArmRiser.setPower(-0.5);
            }
        }
        // nothing pressed nothing move...
        else {
            robot.whiteArmRiser.setPower(0);
        }
    }


    @Override
    public void telemetry() {
        engine.telemetry.addData("arm limit toggle", armLimitToggle);
        engine.telemetry.addData("driver A turret inverted", allianceRedDriver1);
        engine.telemetry.addData("driver B turret inverted", allianceRedDriver2);
        engine.telemetry.addLine();

        engine.telemetry.addData("white arm extension", robot.whiteArmBobbin.getCurrentPosition());
        engine.telemetry.addData("White Riser Arm", robot.whiteArmRiser.getCurrentPosition());
        engine.telemetry.addData("white collector toggle ", whiteCollectorToggle);
        engine.telemetry.addData("white Turret Switch", robot.whiteMag.isPressed());
        engine.telemetry.addData("white Turret orbit Power", robot.turretServoWhite.getPower());
        engine.telemetry.addData("White Door Position", robot.whiteDispenser.getPosition());
        engine.telemetry.addLine();

        engine.telemetry.addData("Orange Riser Arm", robot.orangeArmRiser.getCurrentPosition());
        engine.telemetry.addData("orange arm extension", robot.orangeArmBobbin.getCurrentPosition());
        engine.telemetry.addData("orange collector toggle ", orangeCollectorToggle);
        engine.telemetry.addData("orange Turret Switch", robot.orangeMag.isPressed());
        engine.telemetry.addData("orange Turret Orbit Power", robot.turretServoOrange.getPower());
        engine.telemetry.addData("Orange Door Position", robot.orangeDispenser.getPosition());
        engine.telemetry.addLine();

        engine.telemetry.addData("driveWarehouseLeft", robot.driveWarehouseLeft.getCurrentPosition());
        engine.telemetry.addData("driveWarehouseRight", robot.driveWarehouseRight.getCurrentPosition());
        engine.telemetry.addData("driveGoalLeft", robot.driveGoalLeft.getCurrentPosition());
        engine.telemetry.addData("driveGoalRight", robot.driveGoalRight.getCurrentPosition());
    }

    @Override
    public void buttonUp(Gamepad gamepad, String button) {
        // this is a toggle of a limit for arm extension in the case of the robot crashes
        // in the middle of the game we can bring the arm back in and extend and reset its position.
        System.out.println("GamePad: " + gamepad.getGamepadId() + " Button: " + button);

        if (button.equals("y")) {
            System.out.println("Y has been pressed! (" + armLimitToggle + ")");

            if (!armLimitToggle) {
                armLimitToggle = true;
                minArmTravelDistance = -8500;
                maxArmTravelDistance = 8500;
            } else {
                armLimitToggle = false;
                minArmTravelDistance = 0;
                maxArmTravelDistance = 4000;
                robot.orangeArmBobbin.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.whiteArmBobbin.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.orangeArmBobbin.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                robot.whiteArmBobbin.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            }
        }

        if (gamepad == engine.gamepad1) {
            switch (button) {
                case "left_bumper":
                    if (orangeCollectorToggle) {
                        orangeCollectorToggle = false;
                        robot.collectorOrange.setPower(0);
                    } else {
                        orangeCollectorToggle = true;
                        robot.collectorOrange.setPower(1);
                    }
                    break;
                case "right_bumper":
                    if (orangeCollectorToggle) {
                        orangeCollectorToggle = false;
                        robot.collectorOrange.setPower(0);
                    } else {
                        orangeCollectorToggle = true;
                        robot.collectorOrange.setPower(-1);
                    }
                    break;

                case "guide":
                    allianceRedDriver1 = !allianceRedDriver1;
                    break;


            }
        }

        if (gamepad == engine.gamepad2) {
            switch (button) {

                case "left_bumper":
                    if (whiteCollectorToggle) {
                        whiteCollectorToggle = false;
                        robot.collectorWhite.setPower(0);
                    } else {
                        whiteCollectorToggle = true;
                        robot.collectorWhite.setPower(-1);
                    }
                    break;
                case "right_bumper":
                    if (whiteCollectorToggle) {
                        whiteCollectorToggle = false;
                        robot.collectorWhite.setPower(0);
                    } else {
                        whiteCollectorToggle = true;
                        robot.collectorWhite.setPower(1);
                    }
                    break;
                case "guide":
                    allianceRedDriver2 = !allianceRedDriver2;
                    break;
            }
        }
    }

    private void turretOrbitControl (Gamepad gamepad, CRServo turretServo, boolean bias){

        //  if either of these buttons... move the servo
        //  turretServo1 = orange
        if (gamepad.dpad_right || gamepad.dpad_left) {
            if (bias) {
                if (gamepad.dpad_right) {
                    turretServo.setPower(-1);
                }

                if (gamepad.dpad_left) {
                    turretServo.setPower(1);
                }
            }  else {
                if (gamepad.dpad_right) {
                    turretServo.setPower(1);
                }

                if (gamepad.dpad_left) {
                    turretServo.setPower(-1);
                }
            }
        } else {
            turretServo.setPower(0);
        }
    }
}
