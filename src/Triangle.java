package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import java.lang.Math;

@TeleOp(name="Main: Triangle Drive", group="Iterative Opmode")
public class TriangleDrive extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor w0 = null;
    private DcMotor w1 = null;
    private DcMotor w2 = null;

    @Override
    public void init() {
        w0 = hardwareMap.get(DcMotor.class, "w0");
        w1 = hardwareMap.get(DcMotor.class, "w1");
        w2 = hardwareMap.get(DcMotor.class, "w2");

        w0.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        w1.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        w2.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);

        w0.setDirection(DcMotor.Direction.FORWARD);
        w1.setDirection(DcMotor.Direction.FORWARD);
        w2.setDirection(DcMotor.Direction.FORWARD);
        
        telemetry.addData("Init", "Motors");
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {
        double x_joy = gamepad1.left_stick_x;
        double y_joy = gamepad1.left_stick_y;

        double phi_joy = Math.atan2(y_joy, x_joy);
        
        double x_joy_sq = Math.pow(x_joy, 2);
        double y_joy_sq = Math.pow(y_joy, 2);
        
        double r_joy = Math.sqrt(x_joy_sq + y_joy_sq);

        double max_speed = 1000;
        double speed = max_speed * r_joy;

        double alpha_1 = Math.PI / 2;
        double alpha_2 = 7 * Math.PI / 6;
        double alpha_3 = 11 * Math.PI / 6;

        double theta_1 = alpha_1 - phi_joy;
        double theta_2 = alpha_2 - phi_joy;
        double theta_3 = alpha_3 - phi_joy;

        double w0_power = speed * Math.sin(theta_1);
        double w1_power = speed * Math.sin(theta_2);
        double w2_power = speed * Math.sin(theta_3);

        // w0.setPower(w0_power);
        // w1.setPower(w1_power);
        // w2.setPower(w2_power);


        // double w0_power;
        // double w1_power;
        // double w2_power;

        // double drive = gamepad1.right_stick_x;
        // double turn = -gamepad1.left_stick_x;
        // w0_power = Range.clip(drive + turn, -1.0, 1.0);
        // w1_power = Range.clip(drive + turn, -1.0, 1.0);
        // w2_power = Range.clip(drive + turn, -1.0, 1.0);

        // w0.setPower(w0_power * 100);
        // w1.setPower(w1_power * 100);
        // w2.setPower(w2_power * 100);

        telemetry.addData("Run Time", runtime.toString());
    }

    @Override
    public void stop() {

    }
}
