package com.aizain.jhome.design.pattern.factory.abstractz;

import com.aizain.jhome.design.pattern.entity.BaseVoice;
import com.aizain.jhome.design.pattern.entity.BaseVoiceProduct;
import com.aizain.jhome.design.pattern.entity.JaneVoiceProduct;

/**
 * JaneVoiceProductFactory
 *
 * @author Zain
 * @date 2019-07-13
 */
public class JaneVoiceProductFactory extends AbstractVoiceProductFactory {

    @Override
    public String createMic() {
        return "Jane mic";
    }

    @Override
    public BaseVoice createVoice() {
        return JaneVoiceFactory.createVoice();
    }

    @Override
    public BaseVoiceProduct create() {
        JaneVoiceProduct product = new JaneVoiceProduct();
        product.setMic(createMic());
        product.setVoice(createVoice());
        return product;
    }

}
