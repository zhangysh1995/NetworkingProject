package Utility;

import name.neuhalfen.projects.crypto.bouncycastle.openpgp.BouncyGPG;
import name.neuhalfen.projects.crypto.bouncycastle.openpgp.keys.callbacks.KeyringConfigCallbacks;
import name.neuhalfen.projects.crypto.bouncycastle.openpgp.keys.keyrings.KeyringConfig;
import name.neuhalfen.projects.crypto.bouncycastle.openpgp.keys.keyrings.KeyringConfigs;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPKeyRingGenerator;
import org.bouncycastle.openpgp.PGPPublicKeyRing;
import org.bouncycastle.openpgp.PGPSecretKeyRing;
import org.bouncycastle.util.io.Streams;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;

import static Utility.RSA_gen.generateKeyRingGenerator;

/**
 * This module deals with encryption & decryption
 */

//import bounty
public class GPG {
    private PGPSecretKeyRing privateKey;
    private PGPPublicKeyRing publicKey;

    public GPG() {

    }

    public Boolean newKey(String email, String password) {
        /**
         * see https://bouncycastle-pgp-cookbook.blogspot.com/2013/01/generating-rsa-keys.html
         */
        try {
            // get new keys
            PGPKeyRingGenerator krgen = generateKeyRingGenerator
                    (email, password.toCharArray());

            // Generate public key ring, dump to file.
            PGPPublicKeyRing pkr = krgen.generatePublicKeyRing();
            BufferedOutputStream pubout = new BufferedOutputStream
                    (new FileOutputStream("dummy.pkr"));
            pkr.encode(pubout);
            pubout.close();

            // Generate private key, dump to file.
            PGPSecretKeyRing skr = krgen.generateSecretKeyRing();
            BufferedOutputStream secout = new BufferedOutputStream
                    (new FileOutputStream("dummy.skr"));
            skr.encode(secout);
            secout.close();
            return true;
        } catch (Exception e){
            return false;
        }

    }

    public static String Decrypt(String text) {
        //return "Decrypted text";
        File PublicKeyRing = new File("/resource/pubring.gpg");
        File SecretKeyRing = new File("/resource/secring.gpg");
        final KeyringConfig keyringConfig = KeyringConfigs
                .withKeyRingsFromFiles(
                        PublicKeyRing,
                        SecretKeyRing,
                        KeyringConfigCallbacks.withPassword("s3cr3t"));

        try (
                final InputStream cipherTextStream = new ByteArrayInputStream(text.getBytes());

                final ByteArrayOutputStream plainText_inMem = new ByteArrayOutputStream();
                final BufferedOutputStream bufferedOut = new BufferedOutputStream(plainText_inMem);

                final InputStream plaintextStream = BouncyGPG
                        .decryptAndVerifyStream()
                        .withConfig(keyringConfig)
                        .andIgnoreSignatures()
                        .fromEncryptedInputStream(cipherTextStream)

        ) {
            Streams.pipeAll(plaintextStream, bufferedOut);
            return new String(plainText_inMem.toByteArray(),"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String Encrypt(String text, String src, String dest, String password) {
        //return "hello world";
        File PublicKeyRing = new File("/resource/pubring.gpg");
        File SecretKeyRing = new File("/resource/secring.gpg");

        final KeyringConfig keyringConfig = KeyringConfigs
                .withKeyRingsFromFiles(
                        PublicKeyRing,
                        SecretKeyRing,
                        KeyringConfigCallbacks.withPassword(password));

        try (
                final ByteArrayOutputStream encrypted = new ByteArrayOutputStream();
                final BufferedOutputStream bufferedOut = new BufferedOutputStream(encrypted);

                final OutputStream outputStream = BouncyGPG
                        .encryptToStream()
                        .withConfig(keyringConfig)
                        .withStrongAlgorithms()
                        .toRecipient(dest)
                        .andSignWith(src)
                        .binaryOutput()
                        .andWriteTo(bufferedOut);

                final InputStream is = new ByteArrayInputStream(text.getBytes("UTF-8"))

        ) {
            Streams.pipeAll(is, outputStream);
            return new String(encrypted.toByteArray(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (PGPException e) {
            e.printStackTrace();
        }
        return null;
    }

    // getter & setters
    public PGPSecretKeyRing getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PGPSecretKeyRing privateKey) {
        this.privateKey = privateKey;
    }

    public PGPPublicKeyRing getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PGPPublicKeyRing publicKey) {
        this.publicKey = publicKey;
    }
}
