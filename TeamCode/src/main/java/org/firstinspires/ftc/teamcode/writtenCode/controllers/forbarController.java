package org.firstinspires.ftc.teamcode.writtenCode.controllers;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.writtenCode.robotMap;

@Config
public class forbarController {
    public enum forbarStatus
    {
        INIT,
        HIGH;
    }
    public forbarController.forbarStatus currentStatus= forbarController.forbarStatus.INIT;
    public forbarController.forbarStatus previousStatus=null;
    public Servo forbar = null;
    public static double init_position=0;
    public static double high_position=0.7;
    public forbarController(robotMap robot) {
        this.forbar=robot.forbar;
    }
    public void update()
    {
        if(currentStatus!=previousStatus)
        {
            previousStatus=currentStatus;
            switch(currentStatus)
            {
                case INIT:
                {
                    this.forbar.setPosition(init_position);
                    break;
                }
                case HIGH:
                {
                    this.forbar.setPosition(high_position);
                    break;
                }
            }
        }

    }
}
