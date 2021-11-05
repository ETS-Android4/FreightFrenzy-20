package org.timecrafters.FreightFrenzy.Competition.TeleOp.Engines;

import org.cyberarm.engine.V2.CyberarmEngine;
import org.timecrafters.FreightFrenzy.Competition.Common.Robot;
import org.timecrafters.FreightFrenzy.Competition.TeleOp.States.TeleOpState;

public class TeleOpEngine extends CyberarmEngine {
    @Override
    public void setup() {
        addState(new TeleOpState(new Robot(this)));
    }
}
