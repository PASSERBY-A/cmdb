package com.hp.xo.resourcepool.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.BitSet;
    
/**
 *
 * This class is very similar to the java.net.URLEncoder class.
 *
 * Unfortunately, with java.net.URLEncoder there is no way to specify to the 
 * java.net.URLEncoder which characters should NOT be encoded.
 *
 * This code was moved from DefaultServlet.java
 *
 * @author Zhefang Chen
 */




public class URLEncoder {
    protected static final char[] hexadecimal = { '0', '1', '2', '3',
        '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    static CharsetEncoder asciiEncoder = 
        Charset.forName("US-ASCII").newEncoder(); // or "ISO-8859-1" for ISO Latin 1
    
    //Array containing the safe characters set.
    protected BitSet safeCharacters = new BitSet(256);
    
    public URLEncoder() {
        for (char i = 'a'; i <= 'z'; i++) {
            addSafeCharacter(i);
        }
        for (char i = 'A'; i <= 'Z'; i++) {
            addSafeCharacter(i);
        }
        for (char i = '0'; i <= '9'; i++) {
            addSafeCharacter(i);
        }
    }
    
    public void addSafeCharacter(char c) {
        safeCharacters.set(c);
    }
    
    public String encode(String path) {
        int maxBytesPerChar = 10;
        StringBuffer rewrittenPath = new StringBuffer(path.length());
        ByteArrayOutputStream buf = new ByteArrayOutputStream(
                maxBytesPerChar);
        OutputStreamWriter writer = null;
        try {
            writer = new OutputStreamWriter(buf, "UTF8");
        } catch (Exception e) {
            e.printStackTrace();
            writer = new OutputStreamWriter(buf);
        }
    
        for (int i = 0; i < path.length(); i++) {
            int c = (int) path.charAt(i);
            //NOTICE - !isPureAscii(path.charAt(i)) check was added by CloudStack
            if (safeCharacters.get(c) || !isPureAscii(path.charAt(i))) {
                rewrittenPath.append((char) c);
            } else {
                // convert to external encoding before hex conversion
                try {
                    writer.write((char) c);
                    writer.flush();
                } catch (IOException e) {
                    buf.reset();
                    continue;
                }
                byte[] ba = buf.toByteArray();
                for (int j = 0; j < ba.length; j++) {
                    // Converting each byte in the buffer
                    byte toEncode = ba[j];
                    rewrittenPath.append('%');
                        int low = (int) (toEncode & 0x0f);
                        int high = (int) ((toEncode & 0xf0) >> 4);
                        rewrittenPath.append(hexadecimal[high]);
                        rewrittenPath.append(hexadecimal[low]);
                    }
                    buf.reset();
                }
            }
            return rewrittenPath.toString();
        }
        
    
        //NOTICE - this part was added by CloudStack
        public static boolean isPureAscii(Character v) {
            return asciiEncoder.canEncode(v);
        }
}
