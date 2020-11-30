package com.aizain.jhome.design.pattern.factory.abstractz;

import com.aizain.jhome.design.pattern.entity.BaseVoice;
import com.aizain.jhome.design.pattern.entity.ZainVoice;

/**
 * ZainVoiceFactory
 *
 * @author Zain
 * @date 2019-07-13
 */
public class ZainVoiceFactory extends AbstractVoiceFactory {

    public static BaseVoice createVoice() {
        return new ZainVoice();
    }

    @Override
    public BaseVoice create() {
        return new ZainVoice();
    }

}
