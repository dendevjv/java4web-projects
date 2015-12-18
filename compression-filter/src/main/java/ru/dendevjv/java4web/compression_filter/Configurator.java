package ru.dendevjv.java4web.compression_filter;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Programmatically configures filters for the application.
 */
@WebListener
public class Configurator implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Entering Configurator.contextInitialized()");
        ServletContext context = sce.getServletContext();

        FilterRegistration.Dynamic registration = context.addFilter(
                "requestLogFilter", new RequestLogFilter());
        registration.addMappingForUrlPatterns(null, false, "/*");

        registration = context.addFilter("compressionFilter",
                new CompressionFilter());
        registration.setAsyncSupported(true);
        registration.addMappingForUrlPatterns(null, false, "/*");

        System.out.println("Leaving Configurator.contextInitialized()");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

}
