package org.timecrafters.FreightFrenzy.HardwareTesting.States;

import org.cyberarm.engine.V2.CyberarmState;

public class ISO8061State extends CyberarmState {
    @Override
    public void exec() {
    }

    @Override
    public void telemetry() {
        engine.telemetry.addLine("The ISO8061 Date Format is Best");
        engine.telemetry.addLine("YYYY-MM-DD");
    }
}
