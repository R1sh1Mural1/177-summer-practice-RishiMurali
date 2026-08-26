package frc.robot.subsystems.Carwash;

/**
 * Carwash states (whiteboard). One roller; each state carries the velocity
 * (rotations per second) to run at. + = intake, - = outtake.
 */
public enum CarwashState {
    IDLE(0.0),
    INTAKE_HALF(40.0),
    INTAKE_FULL(80.0),    // Constants.CarwashConstants.targetIntakeSpeedRPS
    OUTTAKE_HALF(-40.0),
    OUTTAKE_FULL(-80.0);

    private final double velocityRPS;

    CarwashState(double velocityRPS) {
        this.velocityRPS = velocityRPS;
    }

    public double getVelocityRPS() {
        return velocityRPS;
    }
}