package com.aizain.jhome.design.pattern.factory.abstractz;

import com.aizain.jhome.design.pattern.entity.BaseVoice;

/**
 * VoiceStaticFactory
 *
 * @author Zain
 * @date 2019-07-13
 */
public class VoiceStaticFactory {

    private static final String ZAIN_VOICE = "zain";
    private static final String JANE_VOICE = "jane";

    public static BaseVoice create(String voice) {
        switch (voice) {
            case ZAIN_VOICE:
                return new ZainVoiceFactory().create();
            case JANE_VOICE:
                return new JaneVoiceFactory().create();
            default:
                return null;
        }
    }

}
