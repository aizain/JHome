package com.aizain.jhome.design.pattern.factory.simple;

import com.aizain.jhome.design.pattern.entity.JaneVoice;
import com.aizain.jhome.design.pattern.entity.ZainVoice;

/**
 * VoiceMultipleMethodSimpleFactory
 *
 * @author Zain
 * @date 2019-07-13
 */
public class VoiceMultipleMethodSimpleFactory implements MultipleMethodSimpleFactory {

    public ZainVoice createZainVoice() {
        return new ZainVoice();
    }

    public JaneVoice createJaneVoice() {
        return new JaneVoice();
    }

}
