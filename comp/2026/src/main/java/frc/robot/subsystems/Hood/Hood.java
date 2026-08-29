package frc.robot.subsystems.Hood;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Hood extends SubsystemBase {

    private final HoodIO io;
    private HoodState currentState = HoodState.IDLE;

    public Hood(HoodIO io) {
        this.io = io;
    }

    @Override
    public void periodic() {
        switch (currentState) {
            case IDLE -> io.stop();
            default -> io.setPosition(currentState.getPosition());
        }
    }

    public void setState(HoodState state) {
        this.currentState = state;
    }

    public HoodState getState() {
        return currentState;
    }
}