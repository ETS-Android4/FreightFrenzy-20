package org.timecrafters.FreightFrenzy.Competition.Autonomous.States;

import com.qualcomm.robotcore.hardware.Servo;

import org.cyberarm.engine.V2.CyberarmState;

public class DepositorDoor extends CyberarmState {

    Servo servo;
    double targetPosition;
    long time;

    public DepositorDoor(Servo servo, double targetPosition, long time) {
        this.servo = servo;
        this.targetPosition = targetPosition;
        this.time = time;
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
