package org.timecrafters.FreightFrenzy.Competition.TeleOp.Engines;
//adb connect 192.168.43.1
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.cyberarm.engine.V2.CyberarmEngine;
import org.timecrafters.FreightFrenzy.Competition.Common.Robot;
import org.timecrafters.FreightFrenzy.Competition.TeleOp.States.TeleOpState;

@TeleOp(name = "TeleOp")
public class TeleOpEngine extends CyberarmEngine {
    @Override
    public void setup() {
        addState(new TeleOpState(new Robot(this)));
    }
}
