package maf.web.tags;

public abstract class MafResourceAwareTag extends RequestContextAwareTag {

	protected int doStartTagInternal() throws Exception {
		return EVAL_BODY_BUFFERED;
	}
}
