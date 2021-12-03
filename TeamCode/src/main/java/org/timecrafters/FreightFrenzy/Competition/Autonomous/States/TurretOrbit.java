package org.timecrafters.FreightFrenzy.Competition.Autonomous.States;

import com.qualcomm.robotcore.hardware.CRServo;

import org.cyberarm.engine.V2.CyberarmState;
import org.timecrafters.FreightFrenzy.Competition.Common.Robot;
import org.timecrafters.TimeCraftersConfigurationTool.library.TimeCraftersConfiguration;
import org.timecrafters.TimeCraftersConfigurationTool.library.backend.config.Action;

public class TurretOrbit extends CyberarmState {
    private Robot robot;
    private CRServo servo;
    private long time;
    private double power;

    public TurretOrbit(Robot robot, CRServo servo, String groupName, String actionName) {
        this.robot = robot;
        this.servo = servo;
        this.time = robot.configuration.variable(groupName, actionName, "time").value();
        this. power = robot.configuration.variable(groupName, actionName, "power").value();
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
