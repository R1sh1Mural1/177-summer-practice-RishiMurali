package frc.robot.subsystems.Hood;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import frc.robot.Constants.ShooterConstants;

public class HoodIOReal implements HoodIO {

    private final TalonFX hood;
    private final TalonFXConfigurator hoodConfig;
    private final MotionMagicVoltage positionRequest = new MotionMagicVoltage(0);

    public HoodIOReal(int id, String bus) {
        this.hood = new TalonFX(id, bus);
        hoodConfig = hood.getConfigurator();

        TalonFXConfiguration config = new TalonFXConfiguration();

        if (ShooterConstants.adjustableHood.isInverted) {
            config.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        } else {
            config.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        }

        if (ShooterConstants.adjustableHood.isCoast) {
            config.MotorOutput.NeutralMode = NeutralModeValue.Coast;
        } else {
            config.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        }

        config.Slot0.kS = ShooterConstants.adjustableHood.kAdjHoodMotorkS;
        config.Slot0.kV = ShooterConstants.adjustableHood.kAdjHoodMotorkV;
        config.Slot0.kP = ShooterConstants.adjustableHood.kAdjHoodMotorkP;
        config.Slot0.kI = ShooterConstants.adjustableHood.kAdjHoodMotorkI;
        config.Slot0.kD = ShooterConstants.adjustableHood.kAdjHoodMotorkD;

        config.CurrentLimits.StatorCurrentLimitEnable = true;
        config.CurrentLimits.StatorCurrentLimit = ShooterConstants.adjustableHood.statorCurrentLimit;
        config.CurrentLimits.SupplyCurrentLimitEnable = true;
        config.CurrentLimits.SupplyCurrentLimit = ShooterConstants.adjustableHood.supplyCurrentLimit;

        hoodConfig.apply(config);
    }

    @Override
    public void setPosition(double position) {
        hood.setControl(positionRequest.withPosition(position));
    }

    @Override
    public void stop() {
        hood.stopMotor();
    }

    @Override
    public double getPosition() {
        return hood.getPosition().getValueAsDouble();
    }
}