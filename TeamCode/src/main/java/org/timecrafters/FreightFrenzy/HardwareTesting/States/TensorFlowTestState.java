package org.timecrafters.FreightFrenzy.HardwareTesting.States;

import org.cyberarm.engine.V2.CyberarmState;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.timecrafters.FreightFrenzy.Competition.Common.Robot;

public class TensorFlowTestState extends CyberarmState {
    private Robot robot;

    public TensorFlowTestState(Robot robot) {
        this.robot = robot;
    }

    @Override
    public void init() {
        robot.activateTensorflow();
    }

    @Override
    public void exec() {

    }

    @Override
    public void stop() {
        robot.deactivateTensorflow();
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
