package frc.robot.subsystems.Intake;

public interface IntakeIO {

    void setRPS(double rps);

    void stop();

    double getVelocityRPS();

    default void periodic() {
    }

    default void simulationPeriodic() {
    }
}