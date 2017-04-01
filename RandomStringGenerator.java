/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package obfuscator;

import java.util.ArrayList;

/**
 *
 * @author cuonglb
 */
public class RandomStringGenerator {

    public static enum Mode {

        SMETHOD, SPROPERTY, SCLASS
    }

    public static String generateRandomName(int length, int n, Mode mode, ArrayList lstExistedName) throws Exception
    {
        String sName = "";
        while(true)
        {
            for(int i=0; i< n; i++)
            {
                sName = generateRandomString(length, mode);
                //check sName is Existed in list Name
                boolean flag = false;
                for(int j=0; j< lstExistedName.size(); j++)
                {
                    if(sName.equalsIgnoreCase(lstExistedName.get(j).toString()))
                        flag = true;
                }
                if(flag == false) return sName;//Neu chua ton tai => tra ve sName
                length++;
            }
        }
    }

    public static String generateRandomString(int length, Mode mode) throws Exception {

        StringBuffer buffer = new StringBuffer();
        String characters = "";

        switch (mode) {

            case SMETHOD:
                characters = "_";

                //characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
                break;

            case SPROPERTY:
                characters = "lI1";

                //characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
                break;

            case SCLASS:
                //characters = "S5$";
                  characters = "S5S";
                //characters = "1234567890";
                break;
        }

        int charactersLength = characters.length();
        buffer.append(characters.charAt(0));
        for (int i = 0; i < length -1; i++) {
            double index = Math.random() * charactersLength;
            buffer.append(characters.charAt((int) index));
        }
        return buffer.toString();
    }
}
