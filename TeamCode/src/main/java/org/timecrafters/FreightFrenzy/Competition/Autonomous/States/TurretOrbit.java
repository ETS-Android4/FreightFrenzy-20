package org.timecrafters.FreightFrenzy.Competition.Autonomous.States;

import com.qualcomm.robotcore.hardware.CRServo;

import org.cyberarm.engine.V2.CyberarmState;
import org.timecrafters.FreightFrenzy.Competition.Common.Robot;

public class TurretOrbit extends CyberarmState {
    private Robot robot;
    private CRServo servo;
    private long time;
    private double power;

    public TurretOrbit(Robot robot, CRServo servo, long time, double power) {
        this.robot = robot;
        this.servo = servo;
        this.time = time;
        this. power = power;
    }

    @Override
    public void exec() {
        if (runTime() < time) {
            servo.setPower(power);
        }
        else {
            servo.setPower(0);
            setHasFinished(true);
        }
    }
}
