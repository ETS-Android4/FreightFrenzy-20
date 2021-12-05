package org.timecrafters.FreightFrenzy.Competition.Autonomous.States;

import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.hardware.CRServo;

import org.cyberarm.engine.V2.CyberarmState;
import org.timecrafters.FreightFrenzy.Competition.Common.Robot;
import org.timecrafters.TimeCraftersConfigurationTool.library.TimeCraftersConfiguration;
import org.timecrafters.TimeCraftersConfigurationTool.library.backend.config.Action;

public class TurretOrbit extends CyberarmState {
    private Robot robot;
    private CRServo servo;
    private double time;
    private double power;
    private RevTouchSensor magnetSwitch;
    public TurretOrbit(Robot robot, CRServo servo, RevTouchSensor magnetSwitch, String groupName, String actionName) {
        this.robot = robot;
        this.servo = servo;
        this.time = robot.configuration.variable(groupName, actionName, "time").value();
        this. power = robot.configuration.variable(groupName, actionName, "power").value();
        this. magnetSwitch = magnetSwitch;
    }

    @Override
    public void exec() {
  if (magnetSwitch.isPressed() || runTime() < time ){
      servo.setPower(0);
      setHasFinished(true);
  }
  else {
      servo.setPower(power);
  }
    }
}
