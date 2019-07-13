package com.aizain.jhome.design.pattern.entity;

import lombok.Getter;

/**
 * BaseVoice
 *
 * @author Zain
 * @date 2019-07-13
 */
public abstract class BaseVoice {

    @Getter
    private String source;

    /**
     * 播放
     */
    abstract public void play();

    /**
     * 停止
     */
    abstract public void stop();

}
