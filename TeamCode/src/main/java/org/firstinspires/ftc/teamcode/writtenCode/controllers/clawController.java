package org.firstinspires.ftc.teamcode.writtenCode.controllers;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.writtenCode.robotMap;
@Config
public class clawController {
    public enum clawStatus{
        OPEN,
        CLOSED;
    }
    public clawController.clawStatus currentStatus= clawController.clawStatus.OPEN;
    public clawController.clawStatus previousStatus=null;
    public Servo clawLeft = null;
    public Servo clawRight = null;
    public static double open_position=0;
    public static double closed_position=0.4;
    public clawController(robotMap robot) {
        this.clawLeft=robot.clawLeft;
        this.clawRight=robot.clawRight;
    }
    public void update()
    {
        if(currentStatus!=previousStatus)
        {
            previousStatus=currentStatus;
            switch(currentStatus)
            {
                case OPEN:
                {
                    this.clawLeft.setPosition(open_position);
                    this.clawRight.setPosition(closed_position);

                    break;
                }
                case CLOSED:
                {
                    this.clawRight.setPosition(open_position);
                    this.clawLeft.setPosition(closed_position);
                    break;
                }
            }
        }

    }
}
