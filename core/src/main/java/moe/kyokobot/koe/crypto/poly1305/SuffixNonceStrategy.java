package moe.kyokobot.koe.crypto.poly1305;

import io.netty.buffer.ByteBuf;
import moe.kyokobot.koe.internal.rtp.RtpHeader;

import java.util.concurrent.ThreadLocalRandom;

public class SuffixNonceStrategy implements NonceStrategy {
    private final byte[] nonce = new byte[24];

    @Override
    public byte[] generateNonce(RtpHeader header) {
        ThreadLocalRandom.current().nextBytes(nonce);
        return nonce;
    }

    @Override
    public void appendNonce(RtpHeader header, ByteBuf output, byte[] nonce) {
        output.writeBytes(nonce);
    }
}
