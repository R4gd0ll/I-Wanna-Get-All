package R4g;


import java.util.Random;

public class RandomStringGenerator {
    private static final String CHAR_SET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final String LETTER_SET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final Random RANDOM = new Random();

    public RandomStringGenerator() {
    }

    public static String generateRandomString(int var0) {
        if (var0 <= 0) {
            return "";
        } else {
            StringBuilder var1 = new StringBuilder(var0);
            int var2 = RANDOM.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".length());
            var1.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".charAt(var2));

            for(int var3 = 1; var3 < var0; ++var3) {
                var2 = RANDOM.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".length());
                var1.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".charAt(var2));
            }

            return var1.toString();
        }
    }

    public static String generateRandomNumString(int var0) {
        Random var1 = new Random();
        int var2 = var1.nextInt(var0);
        StringBuilder var3 = new StringBuilder();

        for(int var4 = 0; var4 < var2; ++var4) {
            var3.append("&num=130");
        }

        return var3.toString();
    }
}
