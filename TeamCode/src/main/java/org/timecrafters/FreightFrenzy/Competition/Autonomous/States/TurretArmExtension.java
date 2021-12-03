package org.timecrafters.FreightFrenzy.Competition.Autonomous.States;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.cyberarm.engine.V2.CyberarmState;
import org.timecrafters.FreightFrenzy.Competition.Common.Robot;

public class TurretArmExtension extends CyberarmState {

    private Robot robot;
    private DcMotor motor;
    private int targetPosition, tolerance;
    private double power;

    public TurretArmExtension(Robot robot, DcMotor motor, String groupName, String actionName) {
        this.robot = robot;
        this.motor = motor;
        this.targetPosition = robot.configuration.variable(groupName, actionName, "targetPosition").value();
        this.power = robot.configuration.variable(groupName, actionName, "power").value();
        this.tolerance = robot.configuration.variable(groupName, actionName, "tolerance").value();
    }

    @Override
    public void exec() {
        if (motor.getCurrentPosition() < targetPosition + tolerance){
            motor.setPower(power);
        }

        else if (motor.getCurrentPosition() > targetPosition - tolerance){
            motor.setPower(-power);
        }
        else {
            setHasFinished(true);
            motor.setPower(0);
        }
    }
}
