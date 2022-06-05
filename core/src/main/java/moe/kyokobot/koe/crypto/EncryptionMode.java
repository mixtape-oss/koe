package moe.kyokobot.koe.crypto;

import io.netty.buffer.ByteBuf;
import moe.kyokobot.koe.internal.rtp.RtpHeader;

import java.util.List;

public interface EncryptionMode {
    boolean box(byte[] secretKey, RtpHeader rtpHeader, ByteBuf packet, int start, ByteBuf output);

//    String getName();

    static String select(List<String> modes) throws UnsupportedEncryptionModeException {
        for (String mode : modes) {
            var impl = DefaultEncryptionModes.encryptionModes.get(mode);

            if (impl != null) {
                return mode;
            }
        }

        throw new UnsupportedEncryptionModeException("Cannot find a suitable encryption mode for this connection!");
    }

    static EncryptionMode get(String mode) {
        var factory = DefaultEncryptionModes.encryptionModes.get(mode);
        return factory != null ? factory.get() : null;
    }
}
