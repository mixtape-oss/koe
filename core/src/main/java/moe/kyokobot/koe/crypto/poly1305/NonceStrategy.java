package moe.kyokobot.koe.crypto.poly1305;

import io.netty.buffer.ByteBuf;
import moe.kyokobot.koe.internal.rtp.RtpHeader;

public interface NonceStrategy {
    int NONCE_LENGTH = 24;

    /**
     * Generates a nonce for the given packet.
     *
     * @param header The RTP header of the packet.
     * @return The generated nonce.
     */
    byte[] generateNonce(RtpHeader header);

    void appendNonce(RtpHeader header, ByteBuf output, byte[] nonce);
}
