package Utility;

import java.io.IOException;

/**
 * This module deals with encryption & decryption
 */

//import bounty
public class GPG {
    private String privateKey;
    private String publicKey;
    private String email;

    public GPG() {

    }

    public Boolean newKey(String email) {
        // get new keys
        if(email != null) this.email = email;
        return true;
    }

    public static String Decrypt(String text) {
        return "Decrypted text";
    }

    public static String Encrypt(String text) {
        return "hello world";
    }

    // getter & setters
    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
