package br.avcaliani.dxburgerapi.common;

/**
 * Visitable Object Interface.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
public interface Visitable {

    /**
     * Accept Visitor.
     *
     * @param visitor {@link Visitor}.
     */
    public void accept(Visitor visitor);
}
