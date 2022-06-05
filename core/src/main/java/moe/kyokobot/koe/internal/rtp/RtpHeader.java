package moe.kyokobot.koe.internal.rtp;

import io.netty.buffer.ByteBuf;

public class RtpHeader {
    public static final int RTP_VERSION = 2;

    private boolean hasExtension;
    private byte payloadType;
    private char sequence;
    private int timestamp;
    private int ssrc;

    public RtpHeader(boolean hasExtension, byte payloadType, char sequence, int timestamp, int ssrc) {
        this.hasExtension = hasExtension;
        this.payloadType = payloadType;
        this.sequence = sequence;
        this.timestamp = timestamp;
        this.ssrc = ssrc;
    }

    public char getSequence() {
        return sequence;
    }

    public int getSsrc() {
        return ssrc;
    }

    /**
     * Writes this RTP header to the given output.
     * @param output The {@link ByteBuf} to write to.
     */
    public void write(ByteBuf output) {
        output.writeByte(hasExtension ? 0x90 : 0x80);
        output.writeByte(payloadType & 0x7f);
        output.writeChar(sequence);
        output.writeInt(timestamp);
        output.writeInt(ssrc);
    }
}
