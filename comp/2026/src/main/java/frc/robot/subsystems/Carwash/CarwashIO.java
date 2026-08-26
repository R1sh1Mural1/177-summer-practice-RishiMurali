package frc.robot.subsystems.Carwash;

public interface CarwashIO {

    void setRPS(double rps);

    void stop();

    double getVelocityRPS();

    default void periodic() {
    }

    default void simulationPeriodic() {
    }
}