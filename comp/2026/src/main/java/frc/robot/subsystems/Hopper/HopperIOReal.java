package frc.robot.subsystems.Hopper;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import frc.robot.Constants.HopperConstants;

public class HopperIOReal implements HopperIO {

    private final TalonFX hopper;
    private final TalonFXConfigurator hopperConfig;
    private final VelocityVoltage velocityRequest = new VelocityVoltage(0);

    public HopperIOReal(int id, String bus) {
        this.hopper = new TalonFX(id, bus);
        hopperConfig = hopper.getConfigurator();

        TalonFXConfiguration config = new TalonFXConfiguration();
        config.MotorOutput.Inverted = HopperConstants.Top.isInverted
                ? InvertedValue.Clockwise_Positive
                : InvertedValue.CounterClockwise_Positive;
        config.MotorOutput.NeutralMode = HopperConstants.Top.isCoast
                ? NeutralModeValue.Coast
                : NeutralModeValue.Brake;

        config.Slot0.kS = HopperConstants.Top.kHopperS;
        config.Slot0.kV = HopperConstants.Top.kHopperV;
        config.Slot0.kP = HopperConstants.Top.kHopperP;
        config.Slot0.kI = HopperConstants.Top.kHopperI;
        config.Slot0.kD = HopperConstants.Top.kHopperD;

        config.CurrentLimits.SupplyCurrentLimitEnable = true;
        config.CurrentLimits.SupplyCurrentLimit = HopperConstants.Top.hopperCurrentLimit;
        config.CurrentLimits.StatorCurrentLimitEnable = true;
        config.CurrentLimits.StatorCurrentLimit = HopperConstants.Top.hopperCurrentLimit;

        hopperConfig.apply(config);
    }

    @Override
    public void setRPS(double rps) {
        hopper.setControl(velocityRequest.withVelocity(rps));
    }

    @Override
    public void stop() {
        hopper.stopMotor();
    }

    @Override
    public double getVelocityRPS() {
        return hopper.getVelocity().getValueAsDouble();
    }

    @Override
    public void periodic() {
    }
}