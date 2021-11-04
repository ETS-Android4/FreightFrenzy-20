package org.timecrafters.FreightFrenzy.HardwareTesting.States;

import org.cyberarm.engine.V2.CyberarmState;
import org.timecrafters.FreightFrenzy.Competition.Common.Robot;
import org.timecrafters.FreightFrenzy.Competition.Common.RobotLocation;

public class VuforiaTestState extends CyberarmState {
    private Robot robot;

    public VuforiaTestState(Robot robot) {
        this.robot = robot;
    }

    @Override
    public void init() {
        robot.activateVuforia();
    }

    @Override
    public void exec() {

    }

    @Override
    public void telemetry() {
        RobotLocation robotLocation = robot.vuforiaLocation();

        engine.telemetry.addLine("Robot Vuforia Location");

        if (robotLocation != null) {
            engine.telemetry.addData("Orientation", "heading: %.2f, roll: %.2f, pitch: %.2f", robotLocation.heading, robotLocation.roll, robotLocation.pitch);
            engine.telemetry.addData("Location", "X: %.2f, Y: %.2f, Z: %.2f", robotLocation.x, robotLocation.y, robotLocation.z);
        } else {
            engine.telemetry.addLine("No Data");
        }
    }

    @Override
    public void stop() {
        robot.deactivateVuforia();
    }
}
