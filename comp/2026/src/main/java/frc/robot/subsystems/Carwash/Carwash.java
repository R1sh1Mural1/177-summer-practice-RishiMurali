package frc.robot.subsystems.Carwash;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Carwash extends SubsystemBase {

    private final CarwashIO io;

    private CarwashState state = CarwashState.IDLE;

    public Carwash(CarwashIO io) {
        this.io = io;
    }

    public void setState(CarwashState state) {
        this.state = state;
    }

    public CarwashState getState() {
        return state;
    }

    @Override
    public void periodic() {
        io.periodic();

        switch (state) {
            case IDLE:
                io.stop();
                break;
            default:
                io.setRPS(state.getVelocityRPS());
                break;
        }
    }

    @Override
    public void simulationPeriodic() {
        io.simulationPeriodic();
    }
}