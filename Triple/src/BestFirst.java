import java.util.*;

class BestFirst {
    static class State {
        private IState layout;
        private State father;
        private int g;

        public State(IState l, State n) {
            layout = l;
            father = n;
            if (father != null)
                g = father.g + l.getG();
            else
                g = 0;
        }

        public String toString() {
            return layout.toString();
        }

        public int getG() {
            return g;
        }

        public int hashCode() {
            return layout.hashCode();
        }

        public boolean equals(Object o) {
            if (o == null)
                return false;
            if (this.getClass() != o.getClass())
                return false;
            State s = (State) o;
            return this.layout.equals(s.layout);
        }
    }

    protected PriorityQueue<State> abertos;
    private Map<IState, State> fechados;
    private State actual;
    private IState objective;

    final private List<State> sucessores(State n) {
        List<State> sucs = new ArrayList<>();
        List<IState> children = n.layout.children();
        for (IState e : children) {
            if (n.father == null || !e.equals(n.father)) {
                State nn = new State(e, n);
                sucs.add(nn);
            }
        }
        return sucs;
    }

    
    final public Iterator<State> solve(IState s, IState goal) {
        objective = goal;
        abertos = new PriorityQueue<>(10, (s1, s2) -> (int) Math.signum(s1.getG() - s2.getG()));
        fechados = new HashMap<>();
        abertos.add(new State(s, null));
        List<State> sucs;
        State f;
        int i = 0;

        for (; ; ) {
            if (abertos.isEmpty()) {
                return null;
            }
            actual = abertos.poll();

            if (goal.isGoal(actual.layout)) {
                class IT implements Iterator<State> {
                    State last;
                    Stack<State> stack;
                    int totalCost; // Agregamos una variable para rastrear el costo total

                    IT() {
                        last = actual;
                        stack = new Stack<>();
                        totalCost = 0; // Inicializamos el costo total
                        while (last != null) {
                            stack.push(last);
                            totalCost += last.layout.getG(); // Sumamos el costo de cada estado al costo total
                            last = last.father;
                        }
                    }

                    public boolean hasNext() {
                        return !stack.empty();
                    }

                    public State next() {
                        return stack.pop();
                    }

                    public void remove() {
                        throw new UnsupportedOperationException();
                    }

                    public int getTotalCost() {
                        return totalCost; // Devolvemos el costo total
                    }
                }
                return new IT();
            } else {
                sucs = sucessores(actual);
                fechados.put(actual.layout, actual);

                for (State e : sucs) {
                    if (fechados.containsKey(e.layout)) {
                        f = fechados.get(e.layout);
                        if (e.g < f.g) {
                            f.father = e.father;
                            f.g = e.g;
                        }
                    } else {
                        abertos.add(e);
                    }
                }
            }
        }
    }
    
    
}
