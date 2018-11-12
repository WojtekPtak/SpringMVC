package beans.springmvc;

import beans.springmvc.configuration.SecurityConfig;
import beans.springmvc.configuration.WebAppConfiguration;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class WebConfiguration extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;//new Class[]{WebAppConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {SecurityConfig.class, WebAppConfiguration.class};
    }

    @Override
    protected javax.servlet.Filter[] getServletFilters() {
        DelegatingFilterProxy delegateFilterProxy = new DelegatingFilterProxy();
        delegateFilterProxy.setTargetBeanName("loggingFilter");
        return new Filter[]{delegateFilterProxy};
    }

}