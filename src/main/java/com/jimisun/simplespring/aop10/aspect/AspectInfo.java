package com.jimisun.simplespring.aop10.aspect;

/**
 * @author jimisun
 * @create 2022-05-09 16:26
 **/
public class AspectInfo {

    public AspectInfo(int order, DefaultAspect defaultAspect) {
        this.order = order;
        this.defaultAspect = defaultAspect;
    }

    private int order;

    private DefaultAspect defaultAspect;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public DefaultAspect getDefaultAspect() {
        return defaultAspect;
    }

    public void setDefaultAspect(DefaultAspect defaultAspect) {
        this.defaultAspect = defaultAspect;
    }
}
