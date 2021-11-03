package org.timecrafters.FreightFrenzy.HardwareTesting.States;

import com.qualcomm.robotcore.hardware.Servo;

import org.cyberarm.engine.V2.CyberarmState;

public class ServoTestState extends CyberarmState {

    Servo transferServo;

    public void init() {
        transferServo = engine.hardwareMap.servo.get("Transfer_Servo");
    }

    @Override
    public void exec() {
        transferServo.setPosition(0.0);
    }

    @Override
    public void telemetry() {
        engine.telemetry.addLine("The ISO8061 Date Format is Best");
        engine.telemetry.addLine("YYYY-MM-DD");
    }
}
