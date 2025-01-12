package org.firstinspires.ftc.teamcode.writtenCode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

public class robotMap {
    public Servo clawRotate;
    public Servo clawLeft;
    public Servo clawRight;
    public Servo linkage1;
    public Servo linkage2;
    public Servo clawPosition;
    public Servo forbar;
    public Servo pivot1;
    public Servo pivot2;


    public DcMotorEx lift1;
    public DcMotorEx lift2;
    public MotorConfigurationType mctlift1, mctlift2;
    public robotMap(HardwareMap Init)
    {
        lift1=Init.get(DcMotorEx.class,"lift1");
        lift1.setDirection(DcMotor.Direction.FORWARD);
        lift1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        lift2=Init.get(DcMotorEx.class,"lift1");
        lift2.setDirection(DcMotor.Direction.FORWARD);
        lift2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        linkage1=Init.get(Servo.class, "linkage1");
        linkage2=Init.get(Servo.class, "linkage2");
        clawRotate=Init.get(Servo.class, "clawRotate");
        clawLeft=Init.get(Servo.class, "clawLeft");
        clawRight=Init.get(Servo.class, "clawRight");
        clawPosition=Init.get(Servo.class, "clawPosition");
        forbar=Init.get(Servo.class, "forbar");
        pivot1=Init.get(Servo.class, "pivot1");
        pivot2=Init.get(Servo.class, "pivot2");

    }
}
