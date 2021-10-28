package org.timecrafters.FreightFrenzy.HardwareTesting.Engines;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.cyberarm.engine.V2.CyberarmEngine;
import org.timecrafters.FreightFrenzy.Competition.Common.Robot;
import org.timecrafters.FreightFrenzy.HardwareTesting.States.DriveTrainTestState;

@TeleOp(name = "DriveTrain Test", group = "testing")
public class DriveTrainTestEngine extends CyberarmEngine {
    @Override
    public void setup() {
        Robot robot = new Robot(this);

        addState(new DriveTrainTestState(robot));
    }
}
