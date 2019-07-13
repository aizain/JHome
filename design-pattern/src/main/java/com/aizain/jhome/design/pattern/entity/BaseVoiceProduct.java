package com.aizain.jhome.design.pattern.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * BaseVoiceProduct
 *
 * @author Zain
 * @date 2019-07-13
 */
public abstract class BaseVoiceProduct {

    @Getter
    @Setter
    private BaseVoice voice;

    @Getter
    @Setter
    private String mic;

}
