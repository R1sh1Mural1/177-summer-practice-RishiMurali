package frc.robot.subsystems.Carwash;

/**
 * Carwash states. Each carries the roller velocity (RPS) to run at.
 */
public enum CarwashState {
    IDLE(0.0),
    INTAKE(80.0),
    OUTTAKE(-80.0);

    private final double rps;

    CarwashState(double rps) {
        this.rps = rps;
    }

    public double getRPS() {
        return rps;
    }
}