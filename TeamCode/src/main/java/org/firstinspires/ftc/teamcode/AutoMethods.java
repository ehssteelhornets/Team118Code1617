package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
public abstract class AutoMethods extends HardwareMethods118_1617 {

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void start() {
    }

    public void DriveForward(double speed, int distance) {
        reset_drive_encoders();
        while(!have_encoders_reset()) {
            reset_drive_encoders();
        }

        int ticks = getNumTicks(distance);
        while(!have_encoders_reached(ticks)) {
            set_drive_power(speed);
        }
        set_drive_power(0);
    }


    final static double enc_CPR = 1260.31;  //Encoder ticks per revolution
    final static double wheelSize = 3.75;  //Diameter of the wheels or sprockets in inches

    /**
     * SET THE DRIVE POWER FOR AUTO
     *
     * @param pwr - power level of both motors
     */
    public void set_drive_power(double pwr) {
        r$front.setPower(pwr);
        r$rear.setPower(pwr);

        l$front.setPower(pwr);
        l$rear.setPower(pwr);
    }

    public void turn(int degrees) {
        double[] drivePower = new double[2];
        drivePower[0] = 0.0;
        drivePower[1] = 0.0;
        degrees = degrees % 360;
        if (degrees > 180) {

            drivePower[0] = 1.0;
            drivePower[1] = -1.0;

        } else {

            drivePower[0] = -1.0;
            drivePower[1] = 1.0;

        }

        int ticks = get_ticks_degrees(degrees);
        set_drive_power(drivePower[0] = 1.0, drivePower[1]);
        if (have_encoders_reached(ticks)) {

            set_drive_power(0);

        }
    }

    public int get_ticks_degrees(int degrees) {

        double percent = degrees / 360;
        int ticks = (int) (percent * enc_CPR);
        return ticks;
    }

    /**
     * @param leftPwr  - power of left motor
     * @param rightPwr - power of right motor
     */
    public void set_drive_power(double leftPwr, double rightPwr) {
        r$front.setPower(rightPwr);
        r$rear.setPower(rightPwr);

        l$front.setPower(leftPwr);
        l$rear.setPower(leftPwr);
    }

    /**
     * @param distance - number of inches to travel
     * @return - number of encoder ticks to travel DISTANCE inches
     */
    public static int getNumTicks(int distance) {

        double rotations = distance / (Math.PI * Math.pow((wheelSize/2), 2)); //distance divided by the circumference of the wheel
        return (int) (enc_CPR * rotations); //Encoder ticks per revolution times by the number of rotations
    }

    /**
     * RESET THE ENCODERS OF BOTH DRIVE MOTORS
     */
    public void reset_drive_encoders() {
        l$front.setPower(0.0);
        l$rear.setPower(0.0);

        r$front.setPower(0.0);
        l$rear.setPower(0.0);

        reset_left_encoders();
        reset_right_encoders();
    }

    public void reset_left_encoders() {
        l$front.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        l$rear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void reset_right_encoders() {
        r$front.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        r$rear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }


    /**
     * SET THE MOTORS TO RUN WITH ENCODERS
     */
    public void RunWithEncoders() {

        r$front.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        r$rear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        l$front.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        l$rear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void RunToPosition() {

        r$front.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        r$rear.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        l$front.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        l$rear.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    /**
     * HAVE THE ENCODERS REACHED THE REQUIRED NUMBER OF TICKS
     *
     * @param ticks
     * @return true if the encoders have reached TICKS number of ticks
     */
    public boolean have_encoders_reached(int ticks) {
        return has_left_encoder_reached(ticks) && has_right_encoder_reached(ticks);
    }

    public boolean have_encoders_reached(int leftTicks, int rightTicks) {
        return has_left_encoder_reached(leftTicks) && has_right_encoder_reached(rightTicks);
    }

    public boolean has_left_encoder_reached(int ticks) {
        return Math.abs(l$front.getCurrentPosition()) > ticks;
    }

    public boolean has_right_encoder_reached(int ticks) {
        return Math.abs(r$front.getCurrentPosition()) > ticks;
    }


    /**
     * HAVE THE ENCODERS RESET
     *
     * @return true only if the encoders are finished resetting
     */
    public boolean have_encoders_reset() {
        return has_left_front_encoder_reset() && has_right_front_encoder_reset() && has_left_rear_encoder_reset() && has_right_rear_encoder_reset();
    }

    public boolean has_left_front_encoder_reset() {
        return l$front.getCurrentPosition() == 0;
    }

    public boolean has_right_front_encoder_reset() {
        return r$front.getCurrentPosition() == 0;
    }

    public boolean has_left_rear_encoder_reset() {
        return l$rear.getCurrentPosition() == 0;
    }

    public boolean has_right_rear_encoder_reset() {
        return r$rear.getCurrentPosition() == 0;
    }

    /**
     * Color Sensor Stuff
     */

    private static final int LED_CHANNEL = 1; //The port the color sensor is plugged into. Change if necessary

    public void turn_LED_On() {

        cdim.setDigitalChannelState(LED_CHANNEL, true);
    }

    public void turn_LED_Off() {
        cdim.setDigitalChannelState(LED_CHANNEL, false);
    }

    //Convert HSV to RGB values
    public char getColor() {
        char color;
        turn_LED_On();

        if (rgbSensor.red() > rgbSensor.blue()) {
            color = 'R';
        } else if (rgbSensor.red() < rgbSensor.blue()) {
            color = 'B';
        } else {
            color = 'N';
        }

        turn_LED_Off();
        return color;
    }

    public void pusher_toggle(char side) {
        if (side == 'l') {
            lPusherDown ^= true;
            if (lPusherDown) {
                lPusher.setPosition(lServoDown);
            } else {
                lPusher.setPosition(lServoUp);
            }
            busySleep(500);
        }
        else {

            rPusherDown ^= true;
            if (rPusherDown) {
                rPusher.setPosition(rServoDown);
            } else {
                rPusher.setPosition(rServoUp);
            }
            busySleep(500);
        }
    }
}

