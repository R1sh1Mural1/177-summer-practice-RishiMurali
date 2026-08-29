package frc.robot.subsystems.Hood;

import frc.robot.Constants.ShooterConstants;

/** Hood states. Each carries a target hood position. */
public enum HoodState {
    IDLE(ShooterConstants.idleHoodPosition),
    DEPLOYED(ShooterConstants.targetHoodPosition);

    private final double position;

    HoodState(double position) {
        this.position = position;
    }

    public double getPosition() {
        return position;
    }
}