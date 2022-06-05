package moe.kyokobot.koe.crypto.poly1305;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import moe.kyokobot.koe.internal.rtp.RtpHeader;

public class NormalNonceStrategy implements NonceStrategy {
    private final ByteBuf nonce = Unpooled.buffer(NonceStrategy.NONCE_LENGTH);

    @Override
    public byte[] generateNonce(RtpHeader header) {
        nonce.writerIndex(0);
        header.write(nonce);
        return nonce.array();
    }

    @Override
    public void appendNonce(RtpHeader header, ByteBuf output, byte[] nonce) {
        // this nonce strategy does not append the nonce to the end of the packet
    }
}
