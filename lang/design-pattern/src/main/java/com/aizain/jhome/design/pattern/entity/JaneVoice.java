package com.aizain.jhome.design.pattern.entity;

/**
 * JaneVoice
 *
 * @author Zain
 * @date 2019-07-13
 */
public class JaneVoice extends BaseVoice {

    @Override
    public void play() {
        System.out.println("Jane play ...");
    }

    @Override
    public void stop() {
        System.out.println("Jane stop");
    }

    @Override
    public String getSource() {
        return this.getClass().getSimpleName();
    }
}
