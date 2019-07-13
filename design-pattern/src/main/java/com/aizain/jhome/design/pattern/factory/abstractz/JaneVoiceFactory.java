package com.aizain.jhome.design.pattern.factory.abstractz;

import com.aizain.jhome.design.pattern.entity.BaseVoice;
import com.aizain.jhome.design.pattern.entity.JaneVoice;

/**
 * JaneVoiceFactory
 *
 * @author Zain
 * @date 2019-07-13
 */
public class JaneVoiceFactory extends AbstractVoiceFactory {

    public static BaseVoice createVoice() {
        return new JaneVoice();
    }


    @Override
    public BaseVoice create() {
        return new JaneVoice();
    }

}
