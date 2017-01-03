package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//@Autonomous(name="Auto118_ColorSensor", group="Auto118")

public class Auto118_ColorSensor extends LinearOpMode {
    HardwareBot robot = new HardwareBot();

    int opState = 1;
    @Override
    public void runOpMode() throws InterruptedException{
        robot.init(hardwareMap);
        robot.RunWithEncoders();
        waitForStart();
        while(opModeIsActive()) {
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

                sleep(10000);
                while (!robot.have_encoders_reset()) {
                    robot.reset_drive_encoders();
                }
                robot.RunWithEncoders();
                idle();
                telemetry.addData("Robot get Ticks", robot.getNumTicks(70));
                telemetry.update();
                while (!robot.have_encoders_reached(robot.getNumTicks(70))) {
                    robot.set_drive_power(1);
                }
                robot.set_drive_power(0);

                while (!robot.have_encoders_reset()) {
                    robot.reset_drive_encoders();
                }
                robot.RunWithEncoders();
                idle();
                while (!robot.have_encoders_reached(robot.getNumTicks(12))) {
                    robot.set_drive_power(-1);
                }
                robot.set_drive_power(0);

                while (!robot.have_encoders_reset()) {
                    robot.reset_drive_encoders();
                }
                robot.RunWithEncoders();
                robot.turn(45);

                while (!robot.have_encoders_reset()) {
                    robot.reset_drive_encoders();
                }
                robot.RunWithEncoders();
                idle();
                while (!robot.have_encoders_reached(robot.getNumTicks(34))) {
                    robot.set_drive_power(1);
                }
                robot.set_drive_power(0);
                //check sensors here
                /*

                 */
                //end sensor check code
                while (!robot.have_encoders_reset()) {
                    robot.reset_drive_encoders();
                }
                robot.RunWithEncoders();
                idle();
                while (!robot.have_encoders_reached(robot.getNumTicks(9))) {
                    robot.set_drive_power(1);
                }
                robot.set_drive_power(0);
                while (!robot.have_encoders_reset()) {
                    robot.reset_drive_encoders();
                }
                robot.RunWithEncoders();
                idle();
                while (!robot.have_encoders_reached(robot.getNumTicks(43))) {
                    robot.set_drive_power(-1);
                }
                robot.set_drive_power(0);
            }
            robot.printTelemetry(telemetry);
            telemetry.update();
        }
    }
}
