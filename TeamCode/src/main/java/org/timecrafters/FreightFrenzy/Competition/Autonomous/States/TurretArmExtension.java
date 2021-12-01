package org.timecrafters.FreightFrenzy.Competition.Autonomous.States;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.cyberarm.engine.V2.CyberarmState;
import org.timecrafters.FreightFrenzy.Competition.Common.Robot;

public class TurretArmExtension extends CyberarmState {

    private Robot robot;
    private DcMotor motor;
    private int position;
    private double power;

    public TurretArmExtension(Robot robot, DcMotor motor, int position, double power) {
        this.robot = robot;
        this.motor = motor;
        this.position = position;
        this.power = power;
    }

    @Override
    public void exec() {

    }
}
