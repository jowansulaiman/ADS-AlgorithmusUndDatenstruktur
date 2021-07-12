package ProgrammierSerie;

import java.util.List;
import java.util.Set;

import java.util.ArrayList;
import java.util.HashSet;

public class AirportReducer {
    /**
     * Ein Feld mit ganzen Zahlen, um die minimalen Kosten zu erhalten.
     */
    private static int[] MIN;
    /**
     * Ein Feld von Verbindungen, das verwendet wird, um die kürzeste Verbindung zu erhalten.
     */
    private static Connection[] SHORTEST;
    /**
     * Eine Liste, die zum Speichern der verbundenen Komponenten des Graphen verwendet wird.
     */
    private static List<ArrayList<Airport>> CONNECTION;
    /**
     * Ein Satz von Verbindungen, um die kürzesten Verbindungen iterativ zu speichern.
     */
    private static Set<Connection> SHORTCONNECTION;
    /**
     * Ein Array von Ganzzahlen, um die DFS-Nummer jedes Knotens zu speichern.
     */
    private static int[] DFSNUMBER;
    /**
     * Eine Ganzzahl, die zur Kennzeichnung der Knoten während des DFS verwendet wird.
     */
    private static int LABEL;

    /**
     * Klassen Variable initialisieren.
     *
     * @param airports werden uebergeben.
     */
    static void init(List<Airport> airports) {
        CONNECTION = new ArrayList<>();
        SHORTCONNECTION = new HashSet<>();

        // CONNECTION mit Mengen initialisieren, von denen jede einen Flughafen enthält.
        for (Airport airport : airports) {
            ArrayList<Airport> set = new ArrayList<>();
            set.add(airport);
            CONNECTION.add(set);
        }

    }

    /**
     * Finds a set of connections that belong to a minimal spanning tree in the
     * given graph using the Borvuka-Sollin-algorithm.
     *
     * @param airports a list of airports. Airports have incident connections, and
     *                 it is these connections that shall form the MST returned by
     *                 this method. You can assume that there exists a direct or
     *                 indirect connection between any pair of airports, and that
     *                 the set contains at least one airport.
     * @return the connections that form a minimal spanning tree.
     */
    public static Set<Connection> minimalSpanningTree(final List<Airport> airports) {
        init(airports);
        while (CONNECTION.size() != 1) {
            MIN = new int[CONNECTION.size()];
            SHORTEST = new Connection[CONNECTION.size()];

            // Klassen Variablen MIN initialisieren
            for (int i = 0; i < MIN.length; i++) {
                MIN[i] = Integer.MAX_VALUE;
            }
            // Iterieren Sie durch alle Verbindungen.
            Set<Connection> e = connections(airports);
            for (Connection connection : e) {
                // erhalten Sie die Indizes der beiden Sätze, die die Enden der Verbindung enthalten.
                int i = findIndex(connection.getAirport1());
                int j = findIndex(connection.getAirport2());

                if (i != j) {
                    if (connection.getCost() < MIN[i]) {
                        MIN[i] = connection.getCost();
                        SHORTEST[i] = connection;
                    }
                    if (connection.getCost() < MIN[j]) {
                        MIN[j] = connection.getCost();
                        SHORTEST[j] = connection;
                    }

                }
            }
            for (ArrayList<Airport> s : CONNECTION) {
                SHORTCONNECTION.add(SHORTEST[CONNECTION.indexOf(s)]);
            }
            DFSNUMBER = new int[airports.size()];
            LABEL = 0;
            CONNECTION.clear();
            //  Die Menge der verbundenen Komponenten eines Graphen.
            for (Airport airport : airports) {
                if (DFSNUMBER[airports.indexOf(airport)] == 0) {
                    ArrayList<Airport> component = new ArrayList<>();
                    component.add(airport);
                    DFS(airport, airports, component);
                    // Die angeschlossenen Komponenten.
                    CONNECTION.add(component);
                }

            }
        }
        return SHORTCONNECTION;
    }


    /**
     * Gibt den Index der Menge zurück, in der sich der Flughafen befindet.
     *
     * @param airports Flughafen, der angegeben werden soll.
     * @return Der Index.
     */
    private static int findIndex(Airport airport) {
        int index = -1;
        for (ArrayList<Airport> set : CONNECTION) {
            if (set.contains(airport)) {
                index = CONNECTION.indexOf(set);
            }
        }
        return index;
    }

    /**
     * Gibt alle Verbindungen im Graphen als Menge zurück.
     *
     * @param airports Die Liste der Flughaefen.
     * @return Die Menge der Verbindungen.
     */
    private static Set<Connection> connections(List<Airport> airports) {
        Set<Connection> result = new HashSet<>();
        for (Airport a : airports) {
            result.addAll(a.getConnections());
        }
        return result;
    }

    /**
     * Eine rekursive Prozedur, die verwendet wird, um eine DFS rekursiv durchzuführen. Diese Methode wird verwendet als
     * ein Unterprogramm, um die verbundenen Komponenten zu erhalten.
     *
     * @param airport   Der Flughafen, der in der DFS besucht wird.
     * @param airports  Die Liste der Flughaefen.
     * @param component Eine Liste zum Speichern der Flughäfen einer angeschlossenen Komponente.
     */
    private static void DFS(Airport airport, List<Airport> airports, ArrayList<Airport> component) {
        LABEL++;
        DFSNUMBER[airports.indexOf(airport)] = LABEL;

        //Die Adjliste.
        Set<Airport> adj = new HashSet<>();
        Set<Connection> connectionsEt = new HashSet<>();
        /*
         Eine Unterroutine, die verwendet wird,
         um die Verbindungen eines bestimmten Flughafens in einer bestimmten Menge von Verbindungen zu erhalten.
         */
        for (Connection connection : SHORTCONNECTION) {
            if (connection.getAirport1() == airport || connection.getAirport2() == airport) {
                //Die Verbindungen des angegebenen Flughafens.
                connectionsEt.add(connection);
            }
        }
        // Die adjListe eines gegebenen Flughafens basierend auf einem bestimmten Satz von Verbindungen.
        for (Connection connection : connectionsEt) {
            adj.add(connection.getAirport1());
            adj.add(connection.getAirport2());
        }
        // Der angegebene Flughafen loeschen.
        adj.remove(airport);

        for (Airport adjacentAirport : adj) {
            if (DFSNUMBER[airports.indexOf(adjacentAirport)] == 0) {
                component.add(adjacentAirport);
                DFS(adjacentAirport, airports, component);
            }
        }

    }
}

