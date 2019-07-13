package com.aizain.jhome.design.pattern.factory.abstractz;

import com.aizain.jhome.design.pattern.entity.BaseVoice;
import com.aizain.jhome.design.pattern.entity.BaseVoiceProduct;
import com.aizain.jhome.design.pattern.entity.ZainVoiceProduct;

/**
 * ZainVoiceProductFactory
 *
 * @author Zain
 * @date 2019-07-13
 */
public class ZainVoiceProductFactory extends AbstractVoiceProductFactory {

    @Override
    public String createMic() {
        return "Zain mic";
    }

    @Override
    public BaseVoice createVoice() {
        return ZainVoiceFactory.createVoice();
    }

    @Override
    public BaseVoiceProduct create() {
        ZainVoiceProduct product = new ZainVoiceProduct();
        product.setMic(createMic());
        product.setVoice(createVoice());
        return product;
    }

}
