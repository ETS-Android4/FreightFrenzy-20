package org.timecrafters.FreightFrenzy.Competition.Autonomous.States;

import org.cyberarm.engine.V2.CyberarmState;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.timecrafters.FreightFrenzy.Competition.Common.Robot;

public class VuforiaState extends CyberarmState {

    Robot robot;

    public VuforiaState(Robot robot) {
        this.robot = robot;
    }

    @Override
    public void start() {
        robot.activateVuforia();
        robot.activateTensorflow();
    }

    @Override
    public void exec() {
        for (Recognition recognition : robot.tensorflowDetections()) {
        }
    }

    @Override
    public void telemetry() {
        for (Recognition recognition : robot.tensorflowDetections()) {
            engine.telemetry.addData("Label", recognition.getLabel());
            engine.telemetry.addData("Left", recognition.getLeft());
            engine.telemetry.addData("Top", recognition.getTop());
            engine.telemetry.addData("Confidence", recognition.getConfidence());
        }
    }
}
