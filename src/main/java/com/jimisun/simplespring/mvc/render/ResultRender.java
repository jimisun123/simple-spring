package com.jimisun.simplespring.mvc.render;


import com.jimisun.simplespring.mvc.RequestProcessorChain;

/**
 * 渲染请求结果
 */
public interface ResultRender {
    //执行渲染
    void render(RequestProcessorChain requestProcessorChain) throws Exception;
}
