package com.aizain.jhome.design.pattern.factory.simple;

import com.aizain.jhome.design.pattern.entity.BaseVoice;
import com.aizain.jhome.design.pattern.entity.JaneVoice;
import com.aizain.jhome.design.pattern.entity.ZainVoice;

/**
 * VoiceSimpleFactory
 *
 * @author Zain
 * @date 2019-07-13
 */
public class VoiceSimpleFactory implements SimpleFactory<String, BaseVoice> {

    private static final String ZAIN_VOICE = "zain";
    private static final String JANE_VOICE = "jane";

    @Override
    public BaseVoice create(String voice) {
        switch (voice) {
            case ZAIN_VOICE:
                return new ZainVoice();
            case JANE_VOICE:
                return new JaneVoice();
            default:
                return null;
        }
    }


}
