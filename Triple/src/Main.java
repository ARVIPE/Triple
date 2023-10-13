import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String initialInput = sc.nextLine();
        sc.close();

        IState in = new TripleState(Integer.parseInt(initialInput), 0);
        IState out = new TripleState(3 * in.getNumber(), 0);

        BestFirst s1 = new BestFirst();
        Iterator<BestFirst.State> it = s1.solve(in, out);

        long startTime = System.currentTimeMillis();

        int totalCost = 0; // Inicializa el costo total en 0

        if (it == null) {
            System.out.println("No solution was found.");
        } else {
            while (it.hasNext()) {
                BestFirst.State i = it.next();
                System.out.println(i);
               

                // Acumula el costo real
                totalCost = i.getG();
            }
        }

        System.out.println("Total cost: " + totalCost); // Muestra el costo total real

        long totalTime = System.currentTimeMillis() - startTime;
        System.out.println("Time: " + totalTime / 1000f + " secs");
    }
}
