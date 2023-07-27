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
        return encrypt(text);
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

}
