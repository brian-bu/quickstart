//package net.brian.coding.java.utils.smartframe;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.lang.reflect.Method;
//import java.util.Map;
//
//import javax.servlet.ServletConfig;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRegistration;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import net.brian.coding.java.utils.JsonTools;
//import net.brian.coding.java.utils.StringUtil;
//import net.brian.coding.java.utils.smartframe.bean.Data;
//import net.brian.coding.java.utils.smartframe.bean.Handler;
//import net.brian.coding.java.utils.smartframe.bean.Param;
//import net.brian.coding.java.utils.smartframe.bean.View;
//import net.brian.coding.java.utils.smartframe.helper.BeanHelper;
//import net.brian.coding.java.utils.smartframe.helper.ConfigHelper;
//import net.brian.coding.java.utils.smartframe.helper.ControllerHelper;
//import net.brian.coding.java.utils.smartframe.helper.RequestHelper;
//import net.brian.coding.java.utils.smartframe.helper.ServletHelper;
//import net.brian.coding.java.utils.smartframe.helper.UploadHelper;
//import net.brian.coding.java.utils.smartframe.util.ReflectionUtil;
//
///**
// * ÇëÇó×ª·¢Æ÷
// *
// */
//@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
//public class DispatcherServlet extends HttpServlet {
//
//    @Override
//    public void init(ServletConfig servletConfig) throws ServletException {
//        HelperLoader.init();
//
//        ServletContext servletContext = servletConfig.getServletContext();
//
//        registerServlet(servletContext);
//
//        UploadHelper.init(servletContext);
//    }
//
//    private void registerServlet(ServletContext servletContext) {
//        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
//        jspServlet.addMapping("/index.jsp");
//        jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");
//
//        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
//        defaultServlet.addMapping("/favicon.ico");
//        defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
//    }
//
//    @Override
//    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        ServletHelper.init(request, response);
//        try {
//            String requestMethod = request.getMethod().toLowerCase();
//            String requestPath = request.getPathInfo();
//            Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
//            if (handler != null) {
//                Class<?> controllerClass = handler.getControllerClass();
//                Object controllerBean = BeanHelper.getBean(controllerClass);
//
//                Param param;
//                if (UploadHelper.isMultipart(request)) {
//                    param = UploadHelper.createParam(request);
//                } else {
//                    param = RequestHelper.createParam(request);
//                }
//
//                Object result;
//                Method actionMethod = handler.getActionMethod();
//                if (param.isEmpty()) {
//                    result = ReflectionUtil.invokeMethod(controllerBean, actionMethod);
//                } else {
//                    result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
//                }
//
//                if (result instanceof View) {
//                    handleViewResult((View) result, request, response);
//                } else if (result instanceof Data) {
//                    handleDataResult((Data) result, response);
//                }
//            }
//        } finally {
//            ServletHelper.destroy();
//        }
//    }
//
//    private void handleViewResult(View view, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        String path = view.getPath();
//        if (StringUtil.isNotEmpty(path)) {
//            if (path.startsWith("/")) {
//                response.sendRedirect(request.getContextPath() + path);
//            } else {
//                Map<String, Object> model = view.getModel();
//                for (Map.Entry<String, Object> entry : model.entrySet()) {
//                    request.setAttribute(entry.getKey(), entry.getValue());
//                }
//                request.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(request, response);
//            }
//        }
//    }
//
//    private void handleDataResult(Data data, HttpServletResponse response) throws IOException {
//        Object model = data.getModel();
//        if (model != null) {
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
//            PrintWriter writer = response.getWriter();
//            String json = JsonTools.toJson(model);
//            writer.write(json);
//            writer.flush();
//            writer.close();
//        }
//    }
//}
