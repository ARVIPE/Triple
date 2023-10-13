import java.util.List;

interface IState {
    /**
     * @return the children of the receiver.
     */
    List<IState> children();

    /**
     * @return true if the receiver equals the argument state; return false otherwise.
     */
    boolean isGoal(IState state);

    /**
     * @return the cost for moving from the input state to the receiver.
     */
    int getG(); // Costo desde el estado inicial hasta el estado actual

    /**
     * @param goalState El estado objetivo al que se quiere llegar.
     * @return Estimación del costo desde el estado actual hasta el estado objetivo.
     */
    double getH(IState goalState);

    /**
     * @return a unique identifier or hash code for the state.
     */
    int hashCode();

    /**
     * @param obj El objeto con el que se compara el estado.
     * @return true si el estado es igual al objeto proporcionado; de lo contrario, false.
     */
    boolean equals(Object obj);

    /**
     * @return El número asociado al estado, específico para el problema.
     */
    int getNumber();
}
