package org.timecrafters.FreightFrenzy.HardwareTesting.Engines;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.cyberarm.engine.V2.CyberarmEngine;
import org.timecrafters.FreightFrenzy.HardwareTesting.States.ISO8061State;

@TeleOp(name = "Servo Test ", group = "testing")
public class ServoTestEngine extends CyberarmEngine {
    @Override
    public void setup() {
        addState(new ISO8061State());
    }
}
