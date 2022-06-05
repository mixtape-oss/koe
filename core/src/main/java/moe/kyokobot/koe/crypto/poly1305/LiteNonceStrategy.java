package moe.kyokobot.koe.crypto.poly1305;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import moe.kyokobot.koe.internal.rtp.RtpHeader;

public class LiteNonceStrategy implements NonceStrategy {
    private final byte[] nonce = new byte[24];
    private int seq = 0x80000000;

    @Override
    public byte[] generateNonce(RtpHeader header) {
        int s = ++this.seq;
        nonce[0] = (byte) (s & 0xff);
        nonce[1] = (byte) ((s >> 8) & 0xff);
        nonce[2] = (byte) ((s >> 16) & 0xff);
        nonce[3] = (byte) ((s >> 24) & 0xff);

        return nonce;
    }

    @Override
    public void appendNonce(RtpHeader header, ByteBuf output, byte[] nonce) {
        output.writeIntLE(seq);
    }
}
