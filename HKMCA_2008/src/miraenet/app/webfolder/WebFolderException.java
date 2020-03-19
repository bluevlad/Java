package miraenet.app.webfolder;

public class WebFolderException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2413025600413910709L;

	public WebFolderException(Throwable e) {
        super(e);
    }

    public WebFolderException(String msg) {
        super(msg);
    }

    public WebFolderException(String msg, Throwable e) {
        super(msg, e);
    }
}