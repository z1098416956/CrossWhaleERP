import java.io.*;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

/**
 * @author TheSunshine
 * @date 2024-10-25 17:01:46
 */
public class RsaKeyGenerator {
    public static void main(String[] args) {
        try {
            // 生成RSA密钥对
            KeyPair keyPair = generateRsaKey();

            // 将私钥保存到文件
            saveKeyToFile(keyPair.getPrivate(), "D:\\java-project\\smp-visualization-cloud\\smp-visualization-oauth\\src\\main\\resources/private.key");

            // 将公钥保存到文件
            saveKeyToFile(keyPair.getPublic(), "D:\\java-project\\smp-visualization-cloud\\smp-visualization-oauth\\src\\main\\resources/public.key");

            System.out.println("RSA keys have been generated and saved to files in the 'resources' folder.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static KeyPair generateRsaKey() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    private static void saveKeyToFile(java.security.Key key, String fileName) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            fos.write(key.getEncoded());
        }
    }
}
