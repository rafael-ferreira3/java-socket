package utils.criptografia;

import utils.Constants;

public class Criptografia {

    public static String encrypt(String txt){
        char[] chars=txt.toCharArray();
        for(int i=0;i<chars.length;i++){
            chars[i] += Constants.KEY_CRIPTOGRAFICA;
        }
        return new String(chars);
    }

    public static String descrypt(String txt){
        char[] chars=txt.toCharArray();
        for(int i=0;i<chars.length;i++){
            chars[i] -= Constants.KEY_CRIPTOGRAFICA;
        }
        return new String(chars);
    }
}
