package com.jimisun.simplespring.mvc;

import com.jimisun.simplespring.aop20.WeaverAspect;
import com.jimisun.simplespring.core.SimpleBeanFactory;
import com.jimisun.simplespring.injection.DependencyInjector;
import com.jimisun.simplespring.mvc.processor.RequestProcessor;
import com.jimisun.simplespring.mvc.processor.impl.ControllerRequestProcessor;
import com.jimisun.simplespring.mvc.processor.impl.JspRequestProcessor;
import com.jimisun.simplespring.mvc.processor.impl.PreRequestProcessor;
import com.jimisun.simplespring.mvc.processor.impl.StaticResourceRequestProcessor;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jimisun
 * @create 2022-05-10 10:09
 **/
@WebServlet(value = "/*")
public class DispatcherServlet extends HttpServlet {

    List<RequestProcessor> PROCESSOR = new ArrayList<>();

    @Override
    public void init() {
        //1.初始化容器
        SimpleBeanFactory beanContainer = SimpleBeanFactory.getInstance();
        beanContainer.loadBeans("com.jimisun.simplespring");
        new WeaverAspect().doAop();
        new DependencyInjector().doDi();
        //2.初始化请求处理器责任链
        PROCESSOR.add(new PreRequestProcessor());
        PROCESSOR.add(new StaticResourceRequestProcessor(getServletContext()));
        PROCESSOR.add(new JspRequestProcessor(getServletContext()));
        PROCESSOR.add(new ControllerRequestProcessor());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        //1.创建责任链对象实例
        RequestProcessorChain requestProcessorChain = new RequestProcessorChain(PROCESSOR.iterator(), req, resp);
        //2.通过责任链模式来依次调用请求处理器对请求进行处理
        requestProcessorChain.doRequestProcessorChain();
        //3.对处理结果进行渲染
        requestProcessorChain.doRender();
    }
}
