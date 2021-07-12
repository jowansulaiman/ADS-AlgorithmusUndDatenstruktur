package ProgrammierSerie;

import java.util.Comparator;
import java.util.Objects;

/**
 * Represents a connection between two airports. Connections can be taken in either direction. They
 * have an associated cost and can be compared with one another (and thus sorted) based on that
 * cost. Two connections are considered equal if they connect the same airports, even if they differ
 * in cost. This is because we don't allow more than one connection between two airports.
 *
 * <p>This class offers a comparator to sort lists of connections with. If you have a list of
 * connections called {@code list}, you can sort them by cost using the following code:</p>
 *
 * <pre>
 * list.sort(Connection.COST_COMPARATOR);
 * </pre>
 */
public class Connection {

    /**
     * A comparator you can use to sort lists of connectors. See class comment for details.
     */
    public static final Comparator<Connection> COST_COMPARATOR = (conn1, conn2) -> conn1.getCost() - conn2.getCost();

    /**
     * One of the two incident airports.
     */
    private final Airport airport1;
    /**
     * Other of the two incident airports.
     */
    private final Airport airport2;
    /**
     * The connection's cost.
     */
    private final int cost;

    /**
     * Creates a new connection between the two airports with the given cost.
     */
    public Connection(final Airport airport1, final Airport airport2, final int cost) {
        if (airport1 == null || airport2 == null) {
            throw new IllegalArgumentException("Airports must not be null.");
        }

        if (airport1 == airport2) {
            throw new IllegalArgumentException("Connections must connect different airports.");
        }

        if (cost < 1) {
            throw new IllegalArgumentException("Cost must be >= 1");
        }
        // Be sure to save our airports in lexicographically ascending order for
        // equal hash codes
        if (airport1.compareTo(airport2) > 0) {
            this.airport1 = airport1;
            this.airport2 = airport2;
        } else {
            this.airport1 = airport2;
            this.airport2 = airport1;
        }
        this.cost = cost;
    }

    /**
     * Returns the first of the two airports the connection connects.
     */
    public Airport getAirport1() {
        return airport1;
    }

    /**
     * Returns the second of the two airports the connection connects.
     */
    public Airport getAirport2() {
        return airport2;
    }

    /**
     * Returns the cost associated with this connection.
     */
    public int getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Connection) {
            Connection c = (Connection) obj;
            return (c.airport1.equals(this.airport1) && c.airport2.equals(this.airport2))
                    || (c.airport1.equals(this.airport2) && c.airport2.equals(this.airport1));
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        // We build the hash code based on our fields
        return Objects.hash(airport1, airport2);
    }

    @Override
    public String toString() {
        return airport1 + " <-> " + airport2 + " (cost " + cost + ")";
    }
}