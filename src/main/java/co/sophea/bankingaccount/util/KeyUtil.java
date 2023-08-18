package co.sophea.bankingaccount.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.*;
import java.util.Objects;

// this use for private key and public key.
@Component
public class KeyUtil {

    @Value("${access-token.private}")
    private String accessTokenPrivateKeyPath;

    @Value("${access-token.public}")
    private String getAccessTokenPublicKeyPath;

    @Value("${refresh-token.private}")
    private String refreshTokenPrivateKeyPath;

    @Value("${refresh-token.public}")
    private String refreshTokenPublicKeyPath;

    private KeyPair accessTokenKeyPair;

    private KeyPair refreshTokenKeyPair;


    public KeyPair getAccessTokenKeyPair(){

        if(Objects.isNull(accessTokenKeyPair)){

            accessTokenKeyPair = getKeyPair(accessTokenPrivateKeyPath , getAccessTokenPublicKeyPath);
        }
        return accessTokenKeyPair;
    }

    public KeyPair getRefreshTokenKeyPair(){

        if(Objects.isNull(refreshTokenKeyPair)){

            refreshTokenKeyPair = getKeyPair(refreshTokenPublicKeyPath , refreshTokenPrivateKeyPath);
        }

        return refreshTokenKeyPair;
    }


    // create method create KeyPair

    public KeyPair getKeyPair(String publicKeyPath, String privateKeyPath){

        KeyPair keyPair;

        File publicKeyFile = new File(publicKeyPath);
        File privateKeyFile = new File(privateKeyPath);

        if(publicKeyFile.exists() && privateKeyFile.exists()){

            try{

                // create Algorithm

                KeyFactory keyFactory = KeyFactory.getInstance("RSA");


                byte [] publicKeyBytes = Files.readAllBytes(publicKeyFile.toPath());
                EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
                PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);


                byte [] privateKeyBytes = Files.readAllBytes(publicKeyFile.toPath());
                PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
                PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
                keyPair = new KeyPair(publicKey , privateKey);

                return keyPair;

            }catch (NoSuchAlgorithmException | IOException | InvalidKeySpecException e ){

                throw  new RuntimeException(e);
            }

        }else{

            //if(Arrays.asList(envitoment.)){
            //
            //}

        }

        // create key ifs don't have

        File directory = new File("token-keys");

        System.out.println(directory.exists());
        System.out.println(directory.getAbsolutePath());

        if(!directory.exists()){
            directory.mkdirs();
        }

        try{

            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();

            try(FileOutputStream fos = new FileOutputStream(publicKeyPath)){

                X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyPair.getPublic().getEncoded());

                fos.write(keySpec.getEncoded());
            }

            try(FileOutputStream fos = new FileOutputStream(privateKeyPath)){

                PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyPair.getPrivate().getEncoded());

                fos.write(keySpec.getEncoded());
            }

        } catch (  IOException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return keyPair;
    }


    public RSAPublicKey getAccessTokenPublicKey(){

        return (RSAPublicKey) getAccessTokenKeyPair().getPublic();
    }

    public RSAPrivateKey getAccessTokenPrivateKey(){

        return (RSAPrivateKey)  getAccessTokenKeyPair().getPrivate();
    }

    public RSAPrivateKey getRefreshTokenPrivateKey(){

        return (RSAPrivateKey)  getRefreshTokenKeyPair().getPrivate();

    }

    public RSAPublicKey getRefreshTokenPublicKey(){

        return (RSAPublicKey)  getRefreshTokenKeyPair().getPublic();

    }

}
