package frc.robot.subsystems.Intake;

import frc.robot.Constants.IntakeConstants;

/** Intake states (rollers only). Each carries the roller velocity (RPS). */
public enum IntakeState {
    IDLE(0.0),
    ROLLING_IN(IntakeConstants.targetIntakeSpeed),
    ROLLING_OUT(-IntakeConstants.targetIntakeSpeed);

    private final double rps;

    IntakeState(double rps) {
        this.rps = rps;
    }

    public double getRPS() {
        return rps;
    }
}