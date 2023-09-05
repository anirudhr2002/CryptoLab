
import java.io.*;
import java.net.*;
import java.util.*;
class PolyCipher {

    static String generateKey(String str, String key) {
        int x = str.length();

        for (int i = 0;; i++) {
            if (x == i) {
                i = 0;
            }
            if (key.length() == str.length()) {
                break;
            }
            key += (key.charAt(i));
        }
        return key;
    }

    static String cipherText(String str, String key) {
        String cipher_text = "";

        for (int i = 0; i < str.length(); i++) {
            // converting in range 0-25
            int x = (str.charAt(i) + key.charAt(i)) % 26;

            // convert into alphabets(ASCII)
            x += 'A';

            cipher_text += (char) (x);
        }
        return cipher_text;
    }
    static String LowerToUpper(String s) {
        StringBuffer str = new StringBuffer(s);
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLowerCase(s.charAt(i))) {
                str.setCharAt(i, Character.toUpperCase(s.charAt(i)));
            }
        }
        s = str.toString();
        return s;
    }
// Driver code
    public static void main(String[] args) {
    try {
            System.out.print("Plain Text : ");
            Scanner sc = new Scanner(System.in);
            String plain = sc.nextLine();
            plain = LowerToUpper(plain);
            
            System.out.print("Key : ");
            String key = sc.nextLine();
            key = generateKey(plain, key);
            key = LowerToUpper(key);
            String cipher = cipherText(plain, key);
            System.out.println("Cipher Text:" + cipher);
            Socket s = new Socket("localhost", 6667);
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            dout.writeUTF(cipher);
            dout.flush();
            dout.writeUTF(key);
            dout.close();
            s.close();
            sc.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

// This code has been contributed by 29AjayKumar
