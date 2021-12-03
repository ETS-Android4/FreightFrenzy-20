package org.timecrafters.FreightFrenzy.Competition.Autonomous.States;

import com.qualcomm.robotcore.hardware.Servo;

import org.cyberarm.engine.V2.CyberarmState;
import org.timecrafters.FreightFrenzy.Competition.Common.Robot;

public class DepositorDoor extends CyberarmState {

    Servo servo;
    double targetPosition;
    long time;

    public DepositorDoor(Robot robot, Servo servo, String groupName, String actionName) {
        this.servo = servo;
        this.targetPosition = robot.configuration.variable(groupName, actionName, "targetPosition").value();
        this.time = robot.configuration.variable(groupName, actionName, "time").value();
    }

    @Override
    public void exec() {
        if (runTime() < time) {
            servo.setPosition(targetPosition);
        }
        else {
            setHasFinished(true);
        }

    }
}
