package ProgrammierSerie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class ConnectedComponentsFinder {

    /**
     * Erstellen einer leeren Hash-Map, um DFS jedes Topics zu speichern
     */
    private static HashMap<Topic, Integer> DFSNUMMER;

    /**
     * Erstellen einer leeren Hash-Map, um DFS jedes Topics zu speichern
     */
    private static HashMap<Topic, Topic> VATER;

    /**
     * Erstellen einer leeren Hash-Map, um LOWPOINT zu speichern
     */
    private static HashMap<Topic, Integer> LOWPOINT;

    /**
     * Erstellen einer leeren Hash-Map, um kinderknoten ans Ende der Stack zu speichern
     */
    private static Stack<Topic> STACK;
    /**
     * SCC für die Themen.
     */
    private static int SCC = 1;

    /**
     * Eine Methode, die jedes besuchte Thema als besucht markiert.
     *
     * @param v das Thema, welches besucht wurde.
     */
    private static void visit(Topic v) {
        DFSNUMMER.replace(v, v.getId() + 1);
        LOWPOINT.replace(v, v.getId() + 1);
        STACK.push(v);
    }

    /**
     * Eine Methode zum Initialisieren von Klassvariablen.
     *
     * @param topics the list of topics in the topic graph. Each topic can be
     *               assumed to have a unique ID between zero and
     */
    private static void init(List<Topic> topics) {
        DFSNUMMER = new HashMap<>();
        LOWPOINT = new HashMap<>();
        VATER = new HashMap<>();
        STACK = new Stack<>();

        /* die Klassvariablen mit 0 bzw. null fuellen. */
        for (var i : topics) {
            DFSNUMMER.put(i, 0);
            VATER.put(i, null);
            LOWPOINT.put(i, 0);
        }
    }

    /**
     * Eine Methode zum Finden der DFS von v bzw. um die ZH-Komponenten zu finden.
     *
     * @param v das Thema, für das der Algorithmus scc finden soll.
     */
    private static void scc(Topic v) {
        // Das Thema v als besucht markieren.
        visit(v);

        //alle ausgehenden Themen rekusive besuchen.
        for (var u : v.getOutgoingTopics()) {

            // Wenn das Thema noch nicht besucht ist.
            if (DFSNUMMER.get(u) == 0) {

                // Vater des Themas festlegen.
                VATER.replace(u, v);

                //überprüfen, ob das Thema u Ausgehende Themen hat.
                scc(u);

                //der kleinste Tiefpunkt nehmen.
                LOWPOINT.replace(v, Integer.min(LOWPOINT.get(u), LOWPOINT.get(v)));
            } else {

                //Sonst wenn der DFSNUMMER vom Thema u kleiner ist als DFS vom Thema Vm dann..
                if (DFSNUMMER.get(u) < DFSNUMMER.get(v) && STACK.contains(u)) {
                    //der kleinste Tiefpunkt nehmen.
                    LOWPOINT.replace(v, Integer.min(DFSNUMMER.get(u), LOWPOINT.get(v)));
                }
            }
        }
        // Wenn SCC gefunden wurde, dann  alle Themen in der durch Stack definierten Reihenfolge loeschen bis auf v.
        if (LOWPOINT.get(v) == DFSNUMMER.get(v)) {
            Topic tpoic;
            SCC++;
            do {
                tpoic = STACK.pop();
                tpoic.setScc(SCC);
            } while (!v.equals(tpoic));
        }
    }

    /**
     * Finds the strongly connected components of the given topic graph using the
     * algorithm known from the lecture. The connected component each topic is part
     * of is represented by an integer ID and must be set on the topic by this
     * method by calling {@link Topic#setScc(int)}.
     *
     * @param topics the list of topics in the topic graph. Each topic can be
     *               assumed to have a unique ID between zero and
     *               {@code topics.size() - 1}-
     * @param start  the topic the algorithm should start at.
     * @throws IllegalArgumentException if either argument is {@code null} or
     *                                  {@code start} is not part of the list of
     *                                  topics.
     */
    public static void computeSCC(List<Topic> topics, Topic start) {
        if (topics == null || start == null || !topics.contains(start)) {
            throw new IllegalArgumentException("either argument is {@code null {@code start} is not part of the list of topics.");
        }
        init(topics);
        scc(start);
        for (Topic v : topics) {
            if(DFSNUMMER.get(v) == 0) {
                scc(v);
            }
        }
    }
    public static void main(String[] args) {
        List<Topic> topics = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            topics.add(new Topic(i));
        }

        //(0, 1);
        topics.get(0).getOutgoingTopics().add(topics.get(1));

        //(1, 2);
        topics.get(1).getOutgoingTopics().add(topics.get(2));

        //(2, 3);
        topics.get(2).getOutgoingTopics().add(topics.get(3));

        //(3, 0);
        topics.get(3).getOutgoingTopics().add(topics.get(0));

        //(2, 4);
        topics.get(2).getOutgoingTopics().add(topics.get(4));

        //(4, 5);
        topics.get(4).getOutgoingTopics().add(topics.get(5));

        //(5, 6);
        topics.get(5).getOutgoingTopics().add(topics.get(6));

        //(6, 4);
        topics.get(6).getOutgoingTopics().add(topics.get(4));

        //(6, 7);
        topics.get(6).getOutgoingTopics().add(topics.get(7));
        computeSCC(topics, topics.get(0));

        for (int i = 0; i < topics.size(); i++) {
            System.out.println(topics.get(i).toString());
        }
    }
}