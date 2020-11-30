package com.aizain.jhome.design.pattern.entity;

/**
 * ZainVoice
 *
 * @author Zain
 * @date 2019-07-13
 */
public class ZainVoice extends BaseVoice {

    @Override
    public void play() {
        System.out.println("Zain play ...");
    }

    @Override
    public void stop() {
        System.out.println("Zain stop");
    }

    @Override
    public String getSource() {
        return this.getClass().getSimpleName();
    }
}
