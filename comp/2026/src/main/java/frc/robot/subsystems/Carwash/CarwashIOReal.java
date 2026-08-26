package frc.robot.subsystems.Carwash;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import frc.robot.Constants.CarwashConstants;

public class CarwashIOReal implements CarwashIO {

    private final TalonFX carwash;
    private final TalonFXConfigurator carwashConfig;
    private final VelocityVoltage velocityRequest = new VelocityVoltage(0);

    public CarwashIOReal(int id, String bus) {
        this.carwash = new TalonFX(id, bus);
        carwashConfig = carwash.getConfigurator();

        TalonFXConfiguration config = new TalonFXConfiguration();

        config.MotorOutput.Inverted = CarwashConstants.SharedIntake.isInverted
                ? InvertedValue.Clockwise_Positive
                : InvertedValue.CounterClockwise_Positive;
        config.MotorOutput.NeutralMode = CarwashConstants.SharedIntake.isCoast
                ? NeutralModeValue.Coast
                : NeutralModeValue.Brake;

        config.Slot0.kS = CarwashConstants.SharedIntake.kIntakeMotorkS;
        config.Slot0.kV = CarwashConstants.SharedIntake.kIntakeMotorkV;
        config.Slot0.kP = CarwashConstants.SharedIntake.kIntakeMotorkP;
        config.Slot0.kI = CarwashConstants.SharedIntake.kIntakeMotorkI;
        config.Slot0.kD = CarwashConstants.SharedIntake.kIntakeMotorkD;

        config.CurrentLimits.SupplyCurrentLimitEnable = true;
        config.CurrentLimits.SupplyCurrentLimit = CarwashConstants.SharedIntake.supplyCurrentLimit;
        config.CurrentLimits.StatorCurrentLimitEnable = true;
        config.CurrentLimits.StatorCurrentLimit = CarwashConstants.SharedIntake.statorCurrentLimit;

        carwashConfig.apply(config);
    }

    @Override
    public void setRPS(double rps) {
        carwash.setControl(velocityRequest.withVelocity(rps));
    }

    @Override
    public void stop() {
        carwash.stopMotor();
    }

    @Override
    public double getVelocityRPS() {
        return carwash.getVelocity().getValueAsDouble();
    }

    @Override
    public void periodic() {
    }
}