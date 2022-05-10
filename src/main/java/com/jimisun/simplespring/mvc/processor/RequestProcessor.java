package com.jimisun.simplespring.mvc.processor;


import com.jimisun.simplespring.mvc.RequestProcessorChain;

/**
 * 请求执行器
 */
public interface RequestProcessor {
    boolean process(RequestProcessorChain requestProcessorChain) throws Exception;
}
