package ProgrammierSerie;

import java.util.HashSet;
import java.util.Set;

/**
 * A topic which can be discussed in a conversation.
 *
 * <p>
 * Topics have an ID between zero and the number of topics, minus one. The ID
 * can thus be used to index into arrays. Each topic also knows which other
 * topics one might directly switch to.
 * </p>
 *
 * <p>
 * Finally, each topic can be told which strongly connected component it is part
 * of by
 * </p>
 */
public class Topic {

    /**
     * Special value that indicates that this topic is not yet part of a strongly
     * connected component.
     */
    public static final int NO_SCC = -1;

    /** The topic's ID. */
    private final int id;
    /** The set of topics one could transition to from this topic. */
    private final Set<Topic> outgoingTopics = new HashSet<>();
    /** ID of the strongly connected component the topic is part of. */
    private int scc = NO_SCC;

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // Creation

    public Topic(final int id) {
        this.id = id;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // Accessors

    /**
     * Returns the topics ID, which can be used to index into arrays.
     *
     * @return the ID, which is between 0 (inclusive) and the number of topics
     *         (exclusive).
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the set of topics one might transition to from this topic. This set
     * is modifiable, but should not be modified by the SCC algorithm.
     *
     * @return the set of outgoing topics.
     */
    public Set<Topic> getOutgoingTopics() {
        return outgoingTopics;
    }

    /**
     * Returns the ID of the strongly connected component that this topic is part of
     * in the topic graph.
     *
     * @return ID that identifies the strongly connected component, or
     *         {@link #NO_SCC} if the component has not been computed yet.
     */
    public int getSCC() {
        return scc;
    }

    /**
     * Sets the ID of the strongly connected component that this topic is part of in
     * the topic graph.
     *
     * @param scc component's ID. Must be {@code >= 0}.
     * @throws IllegalArgumentException if the ID is {@code < 0}.
     */
    public void setScc(final int scc) {
        if (scc < 0) {
            throw new IllegalArgumentException("scc must be >= 0");
        }
        this.scc = scc;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // Overrides

    @Override
    public String toString() {
        String result = "Topic " + id + " ";

        if (scc == NO_SCC) {
            result += "(no SCC ID yet)";
        } else {
            result += "(SCC " + scc + ")";
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Topic) {
            return ((Topic) obj).getId() == getId();
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(getId());
    }

}