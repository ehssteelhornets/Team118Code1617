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

    int meanRight;
    int meanLeft;
    enum Color {RED, BLUE, TEST};

    Color teamColor = Color.BLUE;
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
            meanLeft = 0;
            meanRight = 0;
            for(int x=0; x<10; x++) {
                meanLeft += muxColor.getCRGB(0)[1];
                meanRight += muxColor.getCRGB(1)[1];
                sleep(50);
            }
            meanLeft /= 10;
            meanRight /= 10;
            telemetry.addData("Left",meanLeft);
            telemetry.addData("Right",meanRight);
            telemetry.update();
            if (meanLeft > meanRight) {
                switch (teamColor) {
                    case RED:
                        robot.lPusher.setPosition(robot.lServoDown);
                        sleep(500);
                        robot.lPusher.setPosition(robot.lServoUp);
                        break;
                    case BLUE:
                        robot.rPusher.setPosition(robot.rServoDown);
                        sleep(500);
                        robot.rPusher.setPosition(robot.rServoUp);
                        break;
                }
            } else {
                switch (teamColor) {
                    case RED:
                        robot.lPusher.setPosition(robot.lServoDown);
                        sleep(500);
                        robot.lPusher.setPosition(robot.lServoUp);
                        break;
                    case BLUE:
                        robot.rPusher.setPosition(robot.rServoDown);
                        sleep(200);
                        robot.rPusher.setPosition(robot.rServoUp);
                        break;
                }
            }

            for (int i=0; i<ports.length; i++) {
                int[] crgb = muxColor.getCRGB(ports[i]);

                telemetry.addLine("Sensor " + ports[i]);
                telemetry.addData("CRGB", "%5d %5d %5d %5d",
                        crgb[0], crgb[1], crgb[2], crgb[3]);
            }

            telemetry.update();
            sleep(2000);
            }

        }
    }

