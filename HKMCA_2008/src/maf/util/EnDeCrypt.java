package maf.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EnDeCrypt {

    public EnDeCrypt()
    {
    }

    public static String encode(String s)
    {
        try
        {
            int i = s.length();
            byte byte0 = 0;
            byte abyte0[] = s.getBytes("ASCII");
            int j = key1.length();
            for(int k = 0; k < i; k++)
            {
                int l = abyte0[k] + Integer.valueOf(key.substring(k, k + 1)).intValue() ^ Integer.valueOf(key1.substring(j - (k + 1), j - k)).intValue();
                abyte0[k] = (byte)l;
            }

            s = new String(abyte0, 0);
            String s1 = encoder.encode(s.getBytes());
            if(s1.trim().endsWith("==="))
                byte0 = 3;
            else
            if(s1.trim().endsWith("=="))
                byte0 = 2;
            else
            if(s1.trim().endsWith("="))
                byte0 = 1;
            else
                byte0 = 0;
            int i1 = s1.length();
            String s2 = s1.substring(0, i1 - byte0).toString() + (new Integer(byte0)).toString();
            return s2;
        }
        catch(Exception exception)
        {
            return null;
        }
    }

    public static String decode(String s)
    {
        try
        {
            int i = s.length();
            String s1 = s.substring(i - 1, i);
            String s2 = s.substring(0, i - 1);
            int j = Integer.valueOf(s1).intValue();
            int k = key1.length();
            for(int l = 0; l < j; l++)
                s2 = s2 + "=";

            String s3 = new String(decoder.decodeBuffer(s2), 0);
            int i1 = s3.length();
            byte abyte0[] = s3.getBytes("ASCII");
            for(int j1 = 0; j1 < i1; j1++)
            {
                int k1 = (abyte0[j1] ^ Integer.valueOf(key1.substring(k - (j1 + 1), k - j1)).intValue()) - Integer.valueOf(key.substring(j1, j1 + 1)).intValue();
                abyte0[j1] = (byte)k1;
            }

            s3 = new String(abyte0, 0);
            return s3;
        }
        catch(Throwable thr)
        {
        	thr.printStackTrace();
        }
        return null;
    }

    static String istr;
    static BASE64Encoder encoder = new BASE64Encoder();
    static BASE64Decoder decoder = new BASE64Decoder();
    static String key = "32780123454689645487";
    static String key1 = "3456784358902156778";
}
