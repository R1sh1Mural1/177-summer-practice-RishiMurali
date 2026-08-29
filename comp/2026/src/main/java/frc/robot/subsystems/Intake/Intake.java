package frc.robot.subsystems.Intake;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {

    private final IntakeIO io;
    private IntakeState currentState = IntakeState.IDLE;

    public Intake(IntakeIO io) {
        this.io = io;
    }

    @Override
    public void periodic() {
        switch (currentState) {
            case IDLE -> io.stop();
            default -> io.setRPS(currentState.getRPS());
        }
    }

    public void setState(IntakeState state) {
        this.currentState = state;
    }

    public IntakeState getState() {
        return currentState;
    }
}