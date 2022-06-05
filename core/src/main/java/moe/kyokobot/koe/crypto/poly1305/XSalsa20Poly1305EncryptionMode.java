package moe.kyokobot.koe.crypto.poly1305;

import io.netty.buffer.ByteBuf;
import moe.kyokobot.koe.crypto.EncryptionMode;
import moe.kyokobot.koe.internal.crypto.TweetNaclFastInstanced;
import moe.kyokobot.koe.internal.rtp.RtpHeader;

import java.util.Arrays;

public class XSalsa20Poly1305EncryptionMode implements EncryptionMode {
    private final byte[] m = new byte[984];
    private final byte[] c = new byte[984];
    private final TweetNaclFastInstanced nacl = new TweetNaclFastInstanced();

    private final NonceStrategy nonceStrategy;

    public XSalsa20Poly1305EncryptionMode(NonceStrategy nonceStrategy) {
        this.nonceStrategy = nonceStrategy;
    }

    public static XSalsa20Poly1305EncryptionMode create() {
        return new XSalsa20Poly1305EncryptionMode(new NormalNonceStrategy());
    }

    public static XSalsa20Poly1305EncryptionMode createLite() {
        return new XSalsa20Poly1305EncryptionMode(new LiteNonceStrategy());
    }

    public static XSalsa20Poly1305EncryptionMode createSuffix() {
        return new XSalsa20Poly1305EncryptionMode(new SuffixNonceStrategy());
    }

    @Override
    public boolean box(byte[] secretKey, RtpHeader rtpHeader, ByteBuf packet, int len, ByteBuf output) {
        for (int i = 0; i < c.length; i++) {
            m[i] = 0;
            c[i] = 0;
        }

        for (int i = 0; i < len; i++) {
            m[i + 32] = packet.readByte();
        }

        byte[] nonce = nonceStrategy.generateNonce(rtpHeader);
        System.out.println(Arrays.toString(nonce));

        if (0 == nacl.cryptoSecretboxXSalsa20Poly1305(c, m, len + 32, nonce, secretKey)) {
            for (int i = 0; i < (len + 16); i++) {
                output.writeByte(c[i + 16]);
            }
            nonceStrategy.appendNonce(rtpHeader, output, nonce);
            return true;
        } else {
            return false;
        }
    }
}
