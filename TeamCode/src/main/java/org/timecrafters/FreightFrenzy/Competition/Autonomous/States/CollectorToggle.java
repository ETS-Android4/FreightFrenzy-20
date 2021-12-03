package org.timecrafters.FreightFrenzy.Competition.Autonomous.States;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

import org.cyberarm.engine.V2.CyberarmState;

public class CollectorToggle extends CyberarmState {
    final static public int MODE_REVERSE = -1;
    final static public int MODE_COLLECT = 1;
    final static public int MODE_STOPPED = 0;

    CRServo servo;
    int mode;

    public CollectorToggle(CRServo servo, int mode) {
        this.servo = servo;
        this.mode = mode;
    }

    @Override
    public void exec() {
        if (mode == MODE_REVERSE){
            servo.setPower(-1);
        }

        else if (mode == MODE_COLLECT){
            servo.setPower(1);
        }

        else {
            servo.setPower(0);
        }
    }
}
