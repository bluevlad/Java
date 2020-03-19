package maf.web.multipart;

public class BasicProgressListener implements org.apache.commons.fileupload.ProgressListener  {
	private long megaBytes = -1;

	public void update(long pBytesRead, long pContentLength, int pItems) {
		long mBytes = pBytesRead / 1000000;
		if (megaBytes == mBytes) {
			return;
		}
		megaBytes = mBytes;
		System.out.println("We are currently reading item " + pItems);
		if (pContentLength == -1) {
			System.out.println("So far, " + pBytesRead + " bytes have been read.");
		} else {
			System.out.println("So far, "
			        + pBytesRead
			        + " of "
			        + pContentLength
			        + " bytes have been read.");
		}
	}
}
