package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Auto118_WIPColorSensor", group="Auto118")

public class Auto118_WIPColorSensor extends LinearOpMode {

    public enum Color {RED, BLUE}

    ;

    public Color teamColor = Color.RED;

    HardwareBot robot = new HardwareBot();

    int opState = 1;

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        robot.RunWithEncoders();
        waitForStart();
        while (opModeIsActive()) {
            if (opState == 1)   //Launch 2 balls - Works. Don't touch.
            {
                robot.shooter.setPower(1);
                telemetry.addData("shoot 1", 1);
                robot.printTelemetry(telemetry);
                sleep(1450);
                robot.shooter.setPower(0);
                robot.queue.setPosition(.5);
                sleep(1000);
                robot.queue.setPosition(0);
                robot.shooter.setPower(1);
                telemetry.addData("shoot 2", 2);
                robot.printTelemetry(telemetry);
                sleep(1450);
                robot.shooter.setPower(0);

                sleep(6100);
                opState++;
            }

            if (opState == 2) {
                while (!robot.have_encoders_reset()) {
                    robot.reset_drive_encoders();

                }
                robot.RunWithEncoders();
                final int pos1 = 30;
                telemetry.addData("Robot get Ticks", robot.getNumTicks(pos1));
                telemetry.update();

                while (!robot.have_encoders_reached(robot.getNumTicks(pos1))) {
                    robot.set_drive_power(1);

                    telemetry.addData("Robot get Ticks", robot.getNumTicks(pos1));
                    telemetry.addData("Right ticks", robot.r$front.getCurrentPosition());
                    telemetry.addData("Left ticks", robot.l$front.getCurrentPosition());
                    telemetry.addData("Tick Goal Reached", robot.have_encoders_reached(robot.getNumTicks(pos1)));
                    telemetry.update();
                }
                robot.set_drive_power(0);
                robot.reset_drive_encoders();
                opState++;
            }

            if (opState == 3) {
                robot.turn(90);
                robot.reset_drive_encoders();
                doColorSensor();
                opState++;
            }

            robot.printTelemetry(telemetry);
            telemetry.update();
            idle(); // Always call idle() at the bottom
        }
    }

    static final double lServoDown = .80;
    static final double rServoDown = .30;
    static final double lServoUp = .30;
    static final double rServoUp = .80;


    boolean leftRed()
    {
        HardwareBot.muxColor.startPolling();
        int[] r1 = new int[2];
        boolean r1b = false;
        int[] b1 = new int[2];
        boolean b1b = false;
        int[] r2 = new int[2];
        boolean r2b = false;
        for (int i=0; i<HardwareBot.ports.length; i++) {
            int[] crgb = HardwareBot.muxColor.getCRGB(HardwareBot.ports[i]);
            r1[i] = crgb[1];
        }



        for (int i=0; i<HardwareBot.ports.length; i++) {
            int[] crgb = HardwareBot.muxColor.getCRGB(HardwareBot.ports[i]);
            b1[i] = crgb[3];
        }


        for (int i=0; i<HardwareBot.ports.length; i++) {
            int[] crgb = HardwareBot.muxColor.getCRGB(HardwareBot.ports[i]);
            r2[i] = crgb[1];
        }



        if(r1[0] > r1[1]) {
            r1b = true;
        }
        else {
            r1b = false;
        }

        if(b1[0] > b1[1]) {
            b1b = true;
        }
        else {
            b1b = false;
        }

        if(r2[0] > r2[1]) {
            r2b = true;
        }
        else {
            r2b = false;
        }

        if(r1b && r2b && b1b || r1b && r2b || r1b && b1b || b1b && r2b)
        {
            return true;
        }
        else{

            return leftRed();
        }

    }


    void doColorSensor() throws InterruptedException {
        int leftRed = 0;
        int rightRed = 0;
        leftRed = robot.leftSensor.red();
        rightRed = robot.rightSensor.red();

        switch (teamColor)  {
            case RED:
                if(leftRed())  {
                    robot.lPusher.setPosition(lServoDown);
                    sleep(1000);
                    robot.lPusher.setPosition(lServoUp);
                }
                else {
                    robot.rPusher.setPosition(rServoDown);
                    sleep(1000);
                    robot.rPusher.setPosition(rServoUp);
                }
                break;
            case BLUE:
                if(leftRed())  {
                    robot.rPusher.setPosition(rServoDown);
                    sleep(1000);
                    robot.rPusher.setPosition(rServoUp);
                }
                else {
                    robot.lPusher.setPosition(lServoDown);
                    sleep(1000);
                    robot.lPusher.setPosition(lServoUp);
                }
                break;
        }
    }
}
