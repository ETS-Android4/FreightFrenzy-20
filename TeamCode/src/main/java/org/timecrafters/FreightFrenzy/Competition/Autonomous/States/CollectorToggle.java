package org.timecrafters.FreightFrenzy.Competition.Autonomous.States;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

import org.cyberarm.engine.V2.CyberarmState;
import org.timecrafters.FreightFrenzy.Competition.Common.Robot;

public class CollectorToggle extends CyberarmState {
    final static public int MODE_REVERSE = -1;
    final static public int MODE_COLLECT = 1;
    final static public int MODE_STOPPED = 0;
    double time;
    CRServo servo;
    double power;

    public CollectorToggle(Robot robot, CRServo servo, String groupName, String actionName) {
        this.servo = servo;
        this.power = robot.configuration.variable(groupName, actionName, "power").value();
        this.time = robot.configuration.variable(groupName, actionName, "time").value();

    }

    @Override
    public void exec() {
        servo.setPower(power);

        if (runTime() >= time){

            servo.setPower(0);

            setHasFinished(true);
        }
    }

    @Override
    public void telemetry() {
        engine.telemetry.addData("runtime", runTime());
        engine.telemetry.addData("time", time);
    }
}
