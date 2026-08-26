package frc.robot.subsystems.Hopper;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Hopper extends SubsystemBase {

    private final HopperIO io;

    private HopperState state = HopperState.IDLE;

    public Hopper(HopperIO io) {
        this.io = io;
    }

    public void setState(HopperState state) {
        this.state = state;
    }

    public HopperState getState() {
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