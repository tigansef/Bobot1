package org.firstinspires.ftc.teamcode.writtenCode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.teamcode.writtenCode.controllers.clawController;
import org.firstinspires.ftc.teamcode.writtenCode.controllers.clawPositionController;
import org.firstinspires.ftc.teamcode.writtenCode.controllers.clawRotateController;
import org.firstinspires.ftc.teamcode.writtenCode.controllers.forbarController;
import org.firstinspires.ftc.teamcode.writtenCode.controllers.liftController;
import org.firstinspires.ftc.teamcode.writtenCode.controllers.linkageController;
import org.firstinspires.ftc.teamcode.writtenCode.controllers.pivotController;

@TeleOp(name="teleOpCode", group="Linear OpMode")

public class teleOpCode extends LinearOpMode {

    public void setMotorRunningMode(DcMotor leftFront, DcMotor leftBack, DcMotor rightFront,
                                    DcMotor rightBack, DcMotor.RunMode runningMode) {
        leftFront.setMode(runningMode);
        rightFront.setMode(runningMode);
        leftBack.setMode(runningMode);
        rightBack.setMode(runningMode);
    }

    public void setMotorZeroPowerBehaviour(DcMotor leftFront, DcMotor leftBack, DcMotor rightFront,
                                           DcMotor rightBack, DcMotor.ZeroPowerBehavior zeroPowerBehavior) {
        leftFront.setZeroPowerBehavior(zeroPowerBehavior);
        rightFront.setZeroPowerBehavior(zeroPowerBehavior);
        leftBack.setZeroPowerBehavior(zeroPowerBehavior);
        rightBack.setZeroPowerBehavior(zeroPowerBehavior);
    }

    public void robotCentricDrive(DcMotor leftFront, DcMotor leftBack,
                                  DcMotor rightFront, DcMotor rightBack,
                                  double leftTrigger, double rightTrigger) {

        double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
        double x = (-gamepad1.left_trigger + gamepad1.right_trigger) * 1.05; // Counteract imperfect strafing
        double rx = gamepad1.right_stick_x;

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double leftFrontPower = (y + x + rx) / denominator;
        double leftBackPower = (y - x + rx) / denominator;
        double rightFrontPower = (y - x - rx) / denominator;
        double rightBackPower = (y + x - rx) / denominator;


        leftFront.setPower(leftFrontPower);
        leftBack.setPower(leftBackPower);
        rightFront.setPower(rightFrontPower);
        rightBack.setPower(rightBackPower);
    }
    int liftTargetPosition=0;
    @Override
    public void runOpMode() throws InterruptedException
    {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        robotMap robot= new robotMap(hardwareMap);

        clawRotateController ClawRotateController = new clawRotateController(robot);
        clawPositionController ClawPositionController = new clawPositionController(robot);
        forbarController Forbarcontroller = new forbarController(robot);
        linkageController LinkageController = new linkageController(robot);
        liftController LiftController = new liftController(robot);
        pivotController PivotController = new pivotController(robot);
        clawController ClawController = new clawController(robot);





        ClawRotateController.update();
        ClawPositionController.update();
        Forbarcontroller.update();
        LiftController.update(liftTargetPosition);
        ClawController.update();
        PivotController.update();
        LinkageController.update();





        DcMotor rightFront = hardwareMap.get(DcMotor.class,"rightFront");
        DcMotor leftFront = hardwareMap.get(DcMotor.class,"leftFront");
        DcMotor rightBack = hardwareMap.get(DcMotor.class,"rightBack");
        DcMotor leftBack = hardwareMap.get(DcMotor.class,"leftBack");
        MotorConfigurationType mct1, mct2, mct3, mct4;
        mct1 = rightBack.getMotorType().clone();
        mct1.setAchieveableMaxRPMFraction(1.0);
        rightBack.setMotorType(mct1);

        mct2 = rightFront.getMotorType().clone();
        mct2.setAchieveableMaxRPMFraction(1.0);
        rightFront.setMotorType(mct2);

        mct3 = leftFront.getMotorType().clone();
        mct3.setAchieveableMaxRPMFraction(1.0);
        leftFront.setMotorType(mct3);

        mct4 = leftBack.getMotorType().clone();
        mct4.setAchieveableMaxRPMFraction(1.0);
        leftBack.setMotorType(mct4);
        setMotorRunningMode(leftFront,leftBack,rightFront,rightBack,
                DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.REVERSE);
        setMotorZeroPowerBehaviour(leftFront,leftBack,rightFront,rightBack,
                DcMotor.ZeroPowerBehavior.BRAKE);
        Gamepad currentGamepad1 = new Gamepad();
        Gamepad currentGamepad2 = new Gamepad();

        Gamepad previousGamepad1 = new Gamepad();
        Gamepad previousGamepad2 = new Gamepad();

        waitForStart();

        while(opModeIsActive())
        {
            if(isStopRequested()) return;
            robotCentricDrive(leftFront,leftBack,rightFront,rightBack,gamepad1.left_trigger,gamepad1.right_trigger);
            previousGamepad1.copy(currentGamepad1);
            previousGamepad2.copy(currentGamepad2);

            currentGamepad1.copy(gamepad1);
            currentGamepad2.copy(gamepad2);

            if(currentGamepad1.x && !previousGamepad1.x && ClawRotateController.currentStatus!= clawRotateController.clawRotateStatus.ROTATE)
            {
                ClawRotateController.currentStatus = clawRotateController.clawRotateStatus.ROTATE;
                //turetaTargetPosition=0;
            }
            if(currentGamepad2.y && !previousGamepad2.y && ClawPositionController.currentStatus!=clawPositionController.clawPositionStatus.COLLECT){
                ClawPositionController.currentStatus= clawPositionController.clawPositionStatus.COLLECT;
            }
            ClawRotateController.update();
            ClawPositionController.update();
            Forbarcontroller.update();
            LiftController.update(liftTargetPosition);
            ClawController.update();
            PivotController.update();
            LinkageController.update();


        }
    }
}
