package org.timecrafters.FreightFrenzy.Competition.Autonomous.Engines;

import org.cyberarm.engine.V2.CyberarmEngine;
import org.timecrafters.FreightFrenzy.Competition.Autonomous.States.StubState;

public class AutonomousEngine extends CyberarmEngine {
    @Override
    public void setup() {
        addState(new StubState());
    }
}
