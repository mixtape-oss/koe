package moe.kyokobot.koe.crypto;

import io.netty.buffer.ByteBuf;
import moe.kyokobot.koe.internal.rtp.RtpHeader;

public class PlainEncryptionMode implements EncryptionMode {
    @Override
    public boolean box(byte[] secretKey, RtpHeader rtpHeader, ByteBuf packet, int start, ByteBuf output) {
        packet.readerIndex(start);
        output.writeBytes(packet);
        return true;
    }
}
