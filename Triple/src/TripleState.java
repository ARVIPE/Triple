import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TripleState implements IState {
    private int number;
    private int cost;

    public TripleState(int number, int cost) {
        this.number = number;
        this.cost = cost;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public List<IState> children() {
        List<IState> childStates = new ArrayList<>();
        // Generar estados hijos aplicando las operaciones: Incremento, Decremento, Duplicación
        childStates.add(new TripleState(number + 1, 1));
        childStates.add(new TripleState(number - 1, 2));
        childStates.add(new TripleState(number * 2, 3));
        return childStates;
    }

    @Override
    public boolean isGoal(IState state) {
        if (state instanceof TripleState) {
            return this.number == ((TripleState) state).number;
        }
        return false;
    }

    @Override
    public int getG() {
        return cost;
    }

    @Override
    public double getH(IState goalState) {
        if (goalState instanceof TripleState) {
            int targetNumber = ((TripleState) goalState).number;
            // Estimación del costo basada en la diferencia entre el número actual y el objetivo
            return Math.abs(this.number - targetNumber);
        }
        return Double.POSITIVE_INFINITY; // Valor predeterminado si el estado objetivo no es válido
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, cost);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TripleState other = (TripleState) obj;
        return number == other.number && cost == other.cost;
    }
}
