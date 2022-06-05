package moe.kyokobot.koe.crypto;

import java.util.Map;
import java.util.function.Supplier;

import moe.kyokobot.koe.crypto.poly1305.XSalsa20Poly1305EncryptionMode;

class DefaultEncryptionModes {
    static final Map<String, Supplier<EncryptionMode>> encryptionModes;

    static {
        encryptionModes = Map.of( // sorted by priority
            "xsalsa20_poly1305_lite", XSalsa20Poly1305EncryptionMode::createLite,
            "xsalsa20_poly1305_suffix", XSalsa20Poly1305EncryptionMode::createSuffix,
            "xsalsa20_poly1305", XSalsa20Poly1305EncryptionMode::create,
            "plain", PlainEncryptionMode::new // not supported by Discord anymore, implemented for testing.
        );
    }

    private DefaultEncryptionModes() {
        //
    }
}
