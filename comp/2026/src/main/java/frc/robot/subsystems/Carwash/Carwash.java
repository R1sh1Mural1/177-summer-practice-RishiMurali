package frc.robot.subsystems.Carwash;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Carwash extends SubsystemBase {

    private final CarwashIO io;
    private CarwashState currentState = CarwashState.IDLE;

    public Carwash(CarwashIO io) {
        this.io = io;
    }

    @Override
    public void periodic() {
        switch (currentState) {
            case IDLE -> io.stop();
            case INTAKE -> io.setRPS(currentState.getRPS());
            case OUTTAKE -> io.setRPS(currentState.getRPS());
        }
    }

    public void stop() {
        io.stop();
    }

    public void setState(CarwashState state) {
        this.currentState = state;
    }

    public CarwashState getState() {
        return currentState;
    }
}