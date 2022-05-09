package com.jimisun.simplespring.aop20.aspect;

import com.jimisun.simplespring.aop20.aspectj.PointcutLocator;

/**
 * @author jimisun
 * @create 2022-05-09 16:26
 **/
public class AspectInfo {

    public AspectInfo(int order, DefaultAspect defaultAspect,PointcutLocator pointcutLocator) {
        this.order = order;
        this.defaultAspect = defaultAspect;
        this.pointcutLocator = pointcutLocator;
    }

    private int order;

    private DefaultAspect defaultAspect;

    private PointcutLocator pointcutLocator;

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

    public PointcutLocator getPointcutLocator() {
        return pointcutLocator;
    }

    public void setPointcutLocator(PointcutLocator pointcutLocator) {
        this.pointcutLocator = pointcutLocator;
    }
}
