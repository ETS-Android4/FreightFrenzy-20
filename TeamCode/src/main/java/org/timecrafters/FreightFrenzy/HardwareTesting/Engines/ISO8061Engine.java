package org.timecrafters.FreightFrenzy.HardwareTesting.Engines;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.cyberarm.engine.V2.CyberarmEngine;
import org.timecrafters.FreightFrenzy.HardwareTesting.States.ISO8061State;

@TeleOp(name = "ISO8061-Is-Best", group = "testing")
public class ISO8061Engine extends CyberarmEngine {
    @Override
    public void setup() {
        addState(new ISO8061State());
    }
}
