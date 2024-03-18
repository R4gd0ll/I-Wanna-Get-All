package decrypt.esafenet;

import Rijndael.Rijndael_Algorithm;

import java.lang.reflect.Array;

public class CodeDecoder {
    private static byte remain_low = 15;
    private static byte remain_up = -16;
    private static byte upper_bit_add_0100 = 64;
    private static byte upper_bit_add_0101 = 80;
    public CodeDecoder() {
    }

    private static void Encrypt(byte[] var0, byte[] var1, int var2, Object var3) throws Exception {
        byte[] var5 = new byte[16];
        byte[] var6 = new byte[16];
        if (0 != var2) {
            for(int var4 = 0; var4 < var2 / 16; ++var4) {
                System.arraycopy(var0, var4 * 16, var5, 0, 16);
                var6 = Rijndael_Algorithm.blockEncrypt(var5, 0, var3);
                System.arraycopy(var6, 0, var1, var4 * 16, 16);
            }

        }
    }
    public static void Encode(byte[] var0, int var1, byte[] var2) throws Exception {
        int var3 = 16 * (var1 / 16);
        Object var6 = Rijndael_Algorithm.makeKey(var2);
        Encrypt(var0, var0, var3, var6);
        if (var3 != var1) {
            int var4 = var1 - var3;

            for(int var5 = 0; var5 < var4; ++var5) {
                var0[var3 + var5] = (byte)(var0[var3 + var5] ^ var5);
            }
        }

    }

    private static void Decrypt(byte[] var0, byte[] var1, int var2, Object var3) throws Exception {
        byte[] var5 = new byte[16];
        byte[] var6 = new byte[16];
        if (0 != var2) {
            for(int var4 = 0; var4 < var2 / 16; ++var4) {
                System.arraycopy(var0, var4 * 16, var5, 0, 16);
                var6 = Rijndael_Algorithm.blockDecrypt(var5, 0, var3);
                System.arraycopy(var6, 0, var1, var4 * 16, 16);
            }

        }
    }

    public static void Decode(byte[] var0, int var1, byte[] var2) throws Exception {
        int var3 = 16 * (var1 / 16);
        Object var6 = Rijndael_Algorithm.makeKey(var2);
        Decrypt(var0, var0, var3, var6);
        if (var3 != var1) {
            int var4 = var1 - var3;

            for(int var5 = 0; var5 < var4; ++var5) {
                var0[var3 + var5] = (byte)(var0[var3 + var5] ^ var5);
            }
        }

    }


    public static String getTransferUnEncrptString(String var0) throws Exception {
        byte[] var1 = var0.getBytes("ISO8859_1");
        int var2 = Array.getLength(var1);
        byte[] var3 = new byte[var2 / 2];

        for(int var4 = 0; var4 < var2; var4 += 2) {
            byte var5 = var1[var4];
            byte var6 = var1[var4 + 1];
            var5 &= remain_low;
            var5 = (byte)(var5 << 4);
            var5 &= remain_up;
            var6 &= remain_low;
            var6 |= var5;
            var3[var4 / 2] = var6;
        }

        String var8 = new String(var3, "ISO8859_1");
        return var8;
    }

    public static String getTransferEncrptString(String var0) throws Exception {
        byte[] var1 = var0.getBytes("ISO8859_1");
        int var2 = Array.getLength(var1);
        byte[] var3 = new byte[var2 * 2];

        for(int var4 = 0; var4 < var2; ++var4) {
            byte var5 = var1[var4];
            int var6 = var5 >>> 4;
            byte var7 = (byte)var6;
            byte var9 = 0;
            byte var10 = (byte)(var7 & remain_low);
            if (var10 == var9) {
                var10 |= upper_bit_add_0101;
            } else {
                var10 |= upper_bit_add_0100;
            }

            byte var12 = (byte)(var5 & remain_low);
            if (var12 == var9) {
                var12 |= upper_bit_add_0101;
            } else {
                var12 |= upper_bit_add_0100;
            }

            var3[var4 * 2] = var10;
            var3[var4 * 2 + 1] = var12;
        }

        String var14 = new String(var3, "ISO8859_1");
        return var14;
    }

}
