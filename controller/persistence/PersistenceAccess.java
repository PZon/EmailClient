package emailClient.controller.persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PZON_SM on 29.10.2020.
 **/
public class PersistenceAccess {

    private String validAccLocation = System.getProperty("user.home")  + File.separator + "validAccounts.ser";
    private Encoder encoder = new Encoder();

    public List<ValidAccount> loadFromPersistence(){
        List<ValidAccount> resultList = new ArrayList<ValidAccount>();
        try{
            FileInputStream fileInputStream = new FileInputStream(validAccLocation);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            List<ValidAccount> accountList = (List<ValidAccount>) objectInputStream.readObject();
            decodePass(accountList);
            resultList.addAll(accountList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  resultList;
    }

    private void decodePass(List<ValidAccount> accountList) {
        for(ValidAccount validAccount:accountList){
            String password = validAccount.getPassword();
            validAccount.setPassword(encoder.decode(password));
        }
    }

    public void savePersistence(List<ValidAccount> validAccounts){
        try {
            File file = new File(validAccLocation);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            encodePass(validAccounts);
            objectOutputStream.writeObject(validAccounts);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void encodePass(List<ValidAccount> validAccounts) {
        for(ValidAccount validAccount: validAccounts){
            String password= validAccount.getPassword();
            validAccount.setPassword(encoder.encode(password));
        }
    }
}
