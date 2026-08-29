package frc.robot.subsystems.Hood;

public interface HoodIO {

    void setPosition(double position);

    void stop();

    double getPosition();

    default void periodic() {
    }

    default void simulationPeriodic() {
    }
}