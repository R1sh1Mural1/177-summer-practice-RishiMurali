package frc.robot.subsystems.Hopper;

import frc.robot.Constants.HopperConstants;

/**
 * Hopper states. Each state carries the roller velocity (rotations per second)
 * to run at while it's active.
 */
public enum HopperState {
    IDLE(HopperConstants.idleHopperSpeed),
    FORWARD(HopperConstants.topMotorTargetVelocity),
    REVERSE(-HopperConstants.topMotorTargetVelocity),
    SPIN_UP(-30.0);

    private final double velocityRPS;

    HopperState(double velocityRPS) {
        this.velocityRPS = velocityRPS;
    }

    public double getVelocityRPS() {
        return velocityRPS;
    }
}