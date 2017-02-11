/*
 * MIT License
 *
 * Copyright (c) 2016 Chris D
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/** Created by Chris D on 10/5/2016
        *
        * In this example, you need to
/** create a device configuration that lists two
 * "I2C Device"s, one named "mux" and the other named "ada". There are two
 * Adafruit color sensors plugged into the I2C multiplexer on ports 0 and 3.
 */
@Autonomous(name="Button Pusher Test", group="Iterative Opmode")
public class ButtonPusherTest extends LinearOpMode {
    HardwareBot robot = new HardwareBot();

    MultiplexColorSensor muxColor;
    int[] ports = {0, 1};

    int leftRed = 0;
    int rightRed = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        int milliSeconds = 48;
        muxColor = new MultiplexColorSensor(hardwareMap, "mux", "ada",
                ports, milliSeconds,
                MultiplexColorSensor.GAIN_16X);
        robot.init(hardwareMap);
        waitForStart();
        muxColor.startPolling();
        while(opModeIsActive()) {
            leftRed = muxColor.getCRGB(0)[1];
            rightRed = muxColor.getCRGB(1)[1];
            telemetry.addData("Left",leftRed);
            telemetry.addData("Right",rightRed);
            telemetry.update();
            try {
                if (leftRed > rightRed) {
                    robot.lPusher.setPosition(robot.lServoDown);
                    sleep(200);
                    robot.lPusher.setPosition(robot.lServoUp);
                } else {
                    robot.rPusher.setPosition(robot.rServoDown);
                    sleep(200);
                    robot.rPusher.setPosition(robot.rServoUp);
                }
            }catch(Exception e){}
            sleep(2000);
            }

        }
    }

