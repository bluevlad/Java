package maf.web.tags.mh.support;


/**
 * <p>Interface for tag handlers implementing valid parent tags for
 * &lt;c:param&gt;.</p>
 *
 * @author Shawn Bayern
 */

public interface ParamParent {

    /**
     * Adds a parameter to this tag's URL.  The intent is that the
     * &lt;param&gt; subtag will call this to register URL parameters.
     * Assumes that 'name' and 'value' are appropriately encoded and do
     * not contain any meaningful metacharacters; in order words, escaping
     * is the responsibility of the caller.
     *
     * @see MafParamSupport
     */
    void addParameter(String name, String value);

}
