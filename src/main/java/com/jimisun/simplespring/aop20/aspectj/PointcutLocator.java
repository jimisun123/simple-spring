package com.jimisun.simplespring.aop20.aspectj;

import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.ShadowMatch;

import java.lang.reflect.Method;

/**
 * 解析Aspect表达式并且定位被织入的目标
 */
public class PointcutLocator {
    /**
     * Pointcut解析器，直接给它赋值上Aspectj的所有表达式，以便支持对众多表达式的解析
     */
    private PointcutParser pointcutParser= PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingContextClassloaderForResolution(
            PointcutParser.getAllSupportedPointcutPrimitives()
    );
    /**
     * 表达式解析器
     */
    private PointcutExpression pointcutExpression;
    public PointcutLocator(String expression){
        this.pointcutExpression = pointcutParser.parsePointcutExpression(expression);
    }

    /**
     * 判断传入的Method对象是否是Aspect的目标代理方法，即匹配Pointcut表达式(精筛)
     * @param method
     * @return
     */
    public boolean accurateMatches(Method method){
        ShadowMatch shadowMatch = pointcutExpression.matchesMethodExecution(method);
        if(shadowMatch.alwaysMatches()){
            return true;
        } else{
            return false;
        }
    }
}
