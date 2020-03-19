package maf.lib.excel.importer;

public class Util {
	/**
	 * column ���ڸ� A,B ������ �����ش�. 
	 * @param i
	 * @return
	 */
	public static String getColumnName(int i) {
		if( i < 0 || i > 255) {
			return "";
		}
		int m1 = i/26;
        int m2 = i%26;
        String s = "";
        if(m1 > 0) {
            s = (char) (m1+65-1) + "";
        }
        s = s + (char) (m2+65);
        return s;
	}
}
