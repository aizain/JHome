package com.aizain.jhome.design.pattern.factory.abstractz;

import com.aizain.jhome.design.pattern.entity.BaseVoice;
import com.aizain.jhome.design.pattern.entity.BaseVoiceProduct;

/**
 * AbstractVoiceProductFactory
 *
 * @author Zain
 * @date 2019-07-13
 */
public abstract class AbstractVoiceProductFactory implements AbstractFactory<BaseVoiceProduct> {

    /**
     * 创建mic模块
     *
     * @return
     */
    abstract public String createMic();

    /**
     * 创建voice模块
     *
     * @return
     */
    abstract public BaseVoice createVoice();

}
