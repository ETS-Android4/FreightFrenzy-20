package org.timecrafters.FreightFrenzy.Competition.Autonomous.States;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.cyberarm.engine.V2.CyberarmState;
import org.timecrafters.FreightFrenzy.Competition.Common.Robot;

public class TurretArmRiser extends CyberarmState {

    private Robot robot;
    private DcMotor motor;
    private int targetPosition, tolerance;
    private double power;

    public TurretArmRiser(Robot robot, DcMotor motor, int targetPosition, double power, int tolerance) {
        this.robot = robot;
        this.motor = motor;
        this.targetPosition = targetPosition;
        this.power = power;
        this.tolerance = tolerance;
    }

    @Override
    public void start() {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void exec() {
        if (motor.getCurrentPosition() < targetPosition - tolerance){
            motor.setPower(power);
        }

        else if (motor.getCurrentPosition() > targetPosition + tolerance){
            motor.setPower(-power);
        }
        else {
            setHasFinished(true);
            motor.setPower(0);
        }
    }
}
