package org.timecrafters.FreightFrenzy.Competition.Common;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class RobotLocation {
    public double x, y, z, roll, pitch, heading;
    public RobotLocation(OpenGLMatrix matrix) {
        VectorF translation = matrix.getTranslation();
        Orientation rotation = Orientation.getOrientation(
                matrix, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);

        x = translation.get(0);
        y = translation.get(1);
        z = translation.get(2);

        roll = rotation.firstAngle;
        pitch = rotation.secondAngle;
        heading = rotation.thirdAngle;
    }
}
