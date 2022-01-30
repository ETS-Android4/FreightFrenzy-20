package org.timecrafters.FreightFrenzy.MecanumRobot_Spencer;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.cyberarm.engine.V2.CyberarmEngine;

@TeleOp(name = "TeleOp Mecanum Robot")

public class Mecanum_Drive_Engine extends CyberarmEngine {
    @Override
    public void setup() { addState(new TeleOpStateMecanumRobot(new MecanumRobot(this)));}

    }
