package org.timecrafters.FreightFrenzy.Competition.Autonomous.States;

import android.util.Log;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.cyberarm.engine.V2.CyberarmState;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.timecrafters.FreightFrenzy.Competition.Common.Robot;

import java.util.List;

public class TensorFlowState extends CyberarmState {

    Robot robot;
    private List<Recognition> recognitions;
    private double checkTime;
    private int manualPath = 0;
    private int path = 3;
    private double leftDuck;
    private double middleDuck;

    private String groupName;
    private DcMotor armRiser, armExtension;

    public TensorFlowState(Robot robot, DcMotor armRiser, DcMotor armExtension, String groupName, String actionName) {
        this.robot = robot;
        this.groupName = groupName;
        this.armRiser = armRiser;
        this.armExtension = armExtension;
        this.leftDuck = robot.configuration.variable(groupName, actionName, "leftDuck").value();
        this.middleDuck = robot.configuration.variable(groupName, actionName, "middleDuck").value();
        this.checkTime = robot.configuration.variable(groupName, actionName, "time").value();
    }

    @Override
    public void start() {
        robot.activateVuforia();
        robot.activateTensorflow();
    }

    @Override
    public void exec() {
        recognitions = robot.tensorflowDetections();

        if (runTime() < checkTime) {
            if (recognitions != null && recognitions.size() != 0) {
                if (recognitions.size() == 1) {
                    Recognition recognition = recognitions.get(0);

                    if (recognition.getLeft() < leftDuck) {
                        path = 0;
                    } else {
                        path = 1;
                    }
                }
            } else {
                path = 2;
            }
        } else {
            Log.i(TAG, "Choosen path: " + path);

            if (path == 0){
                addState(new TurretArmExtension(robot, armExtension, groupName, "02_0"));
                addState(new TurretArmRiser(robot, armRiser, groupName, "03_0_bottom"));
                addState(new TurretArmExtension(robot, armExtension, groupName, "04_0_bottom"));
            } else if (path == 1){
                addState(new TurretArmExtension(robot, armExtension, groupName, "02_0"));
                addState(new TurretArmRiser(robot, armRiser, groupName, "03_0_middle"));
                addState(new TurretArmExtension(robot, armExtension, groupName, "04_0_middle"));
            } else {
                addState(new TurretArmExtension(robot, armExtension, groupName, "02_0"));
                addState(new TurretArmRiser(robot, armRiser, groupName, "03_0_top"));
                addState(new TurretArmExtension(robot, armExtension, groupName, "04_0_top"));
            }

            setHasFinished(true);
        }
    }

    @Override
    public void telemetry() {
        engine.telemetry.addData("Runtime", runTime());
        engine.telemetry.addData("Check Time", checkTime);
        engine.telemetry.addData("Path", path);
        engine.telemetry.addLine();

        if (recognitions == null) {
            return;
        }

        for (Recognition recognition : recognitions) {
            engine.telemetry.addData("Label", recognition.getLabel());
            engine.telemetry.addData("Left", recognition.getLeft());
            engine.telemetry.addData("Top", recognition.getTop());
            engine.telemetry.addData("Confidence", recognition.getConfidence());
            engine.telemetry.addLine();
        }
    }
}
