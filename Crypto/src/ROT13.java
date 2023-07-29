import java.io.*;
import java.io.FileReader;

import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;
import static java.lang.Character.toLowerCase;

public class ROT13  {

    Character cs;

    Character cf;

    ROT13(Character cs, Character cf) {
        this.cs = cs;
        this.cf = cf;
    }

    ROT13() {
    }


    public String crypt(String text) throws UnsupportedOperationException {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String crypt = "";
        Character key = Character.toUpperCase(cf);

        StringBuilder secureText = new StringBuilder();

        // Build the crypt
        crypt = rotate(alphabet, key);

        for (int i = 0; i < text.length(); i++){
            if (!Character.isLetter(text.charAt(i))){
                secureText.append(text.charAt(i));
            }
            else if (!Character.isUpperCase(text.charAt(i))){
                Character temp = Character.toUpperCase(text.charAt(i));
                int alphabetIndex = alphabet.indexOf(temp);
                int cryptIndex = alphabetIndex;

                secureText.append(Character.toLowerCase(crypt.charAt(cryptIndex)));
            }
            else{
                Character temp = text.charAt(i);
                int alphabetIndex = alphabet.indexOf(temp);
                int cryptIndex = alphabetIndex;

                secureText.append(crypt.charAt(cryptIndex));
            }
        }

        return secureText.toString();
    }

    public String encrypt(String text) {

        return crypt(text);
    }

    public String decrypt(String text) {
        return crypt(text);
    }

    public static String rotate(String s, Character c) {
        StringBuilder string = new StringBuilder();
        int index = s.indexOf(c);

        for (int i = 0; i < s.length(); i++){
            if (index >= s.length()){
                index = 0;
            }
            string.append(s.charAt(index));
            index++;
        }

        return string.toString();
    }


    public void encryptSonnetFile(){
        String[] sonnet = new String[14];
        try (BufferedReader theFileReader = new BufferedReader(new FileReader("sonnet18.txt"))) {
            String line;
            for (int i = 0; i < sonnet.length; i++){
                line = theFileReader.readLine();
                sonnet[i] = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < sonnet.length; i++){
            sonnet[i] = encrypt(sonnet[i]);
        }

        try {
            FileWriter encryptedSonnet = new FileWriter("sonnet18.enc");
            for (String line : sonnet){
                encryptedSonnet.write(line + "\n");
            }
            encryptedSonnet.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String decryptEncryptedSonnetFile(){
        String[] sonnet = new String[14];
        try (BufferedReader theFileReader = new BufferedReader(new FileReader("sonnet18.enc"))) {
            String line;
            for (int i = 0; i < sonnet.length; i++){
                line = theFileReader.readLine();
                sonnet[i] = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < sonnet.length; i++){
            sonnet[i] = decrypt(sonnet[i]);
        }

        StringBuilder decryptedSonnet = new StringBuilder();
        for (String line : sonnet){
            decryptedSonnet.append(line + "\n");
        }

        return decryptedSonnet.toString();
    }



}
