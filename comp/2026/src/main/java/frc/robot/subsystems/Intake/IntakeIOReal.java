package frc.robot.subsystems.Intake;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import frc.robot.Constants.IntakeConstants;

public class IntakeIOReal implements IntakeIO {

    private final TalonFX rightRoller;
    private final TalonFX leftRoller;
    private final VelocityVoltage velocityRequest = new VelocityVoltage(0);

    public IntakeIOReal(int rightId, int leftId, String bus) {
        rightRoller = new TalonFX(rightId, bus);
        leftRoller = new TalonFX(leftId, bus);

        rightRoller.getConfigurator().apply(config(IntakeConstants.RightRollerConstants.isInverted));
        leftRoller.getConfigurator().apply(config(IntakeConstants.LeftRollerConstants.isInverted));
    }

    private TalonFXConfiguration config(boolean inverted) {
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.MotorOutput.Inverted = inverted
                ? InvertedValue.Clockwise_Positive
                : InvertedValue.CounterClockwise_Positive;
        config.MotorOutput.NeutralMode = NeutralModeValue.Coast;

        config.Slot0.kS = IntakeConstants.RightRollerConstants.kS;
        config.Slot0.kV = IntakeConstants.RightRollerConstants.kV;
        config.Slot0.kP = IntakeConstants.RightRollerConstants.kP;
        config.Slot0.kI = IntakeConstants.RightRollerConstants.kI;
        config.Slot0.kD = IntakeConstants.RightRollerConstants.kD;

        config.CurrentLimits.StatorCurrentLimitEnable = true;
        config.CurrentLimits.StatorCurrentLimit = IntakeConstants.RightRollerConstants.currentLimit;
        return config;
    }

    @Override
    public void setRPS(double rps) {
        rightRoller.setControl(velocityRequest.withVelocity(rps));
        leftRoller.setControl(velocityRequest.withVelocity(rps));
    }

    @Override
    public void stop() {
        rightRoller.stopMotor();
        leftRoller.stopMotor();
    }

    @Override
    public double getVelocityRPS() {
        return rightRoller.getVelocity().getValueAsDouble();
    }
}