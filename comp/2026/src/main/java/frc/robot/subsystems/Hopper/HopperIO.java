package frc.robot.subsystems.Hopper;

public interface HopperIO {

    void setRPS(double rps);

    void stop();

    double getVelocityRPS();

    default void periodic() {
    }

    default void simulationPeriodic() {
    }
}