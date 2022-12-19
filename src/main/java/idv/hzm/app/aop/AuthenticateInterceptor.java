package idv.hzm.app.aop;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class AuthenticateInterceptor implements HandlerInterceptor {
	
	//@Value("${api_key}")
    private String api_key;


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String timestamp = httpServletRequest.getParameter("timestamp");
        String sign = httpServletRequest.getParameter("sign");
        if (!(object instanceof HandlerMethod)) {
            return true;
        } else {
            if (timestamp == null && sign == null) {
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(httpServletRequest.getInputStream(), "UTF-8"));
                StringBuilder responseStrBuilder = new StringBuilder();
                String inputStr;
                while ((inputStr = streamReader.readLine()) != null) {
                    responseStrBuilder.append(inputStr);
                }
                try {
                    
                }catch (Exception e){
                    return false;
                }


            }


            HandlerMethod handlerMethod = (HandlerMethod) object;
            Method method = handlerMethod.getMethod();
            //检查是否有passtoken注释&#xff0c;有则跳过认证
//            if (method.isAnnotationPresent(BankAuthenticate.class)) {
//            	BankAuthenticate passToken = method.getAnnotation(BankAuthenticate.class);
//                if (passToken.required()) {
//                    return true;
//                }
//            }
//            if (method.isAnnotationPresent(BankAuthenticate.class)) {
//            	BankAuthenticate apiAuthentication = method.getAnnotation(BankAuthenticate.class);
//                if (apiAuthentication.required()) {
//                    if (sign == null || timestamp == null) {
//                        throw new RuntimeException("sign and timestamp cann't be null");
//                    }
//                    String signStr = api_key + timestamp;
//                    String sign2 = DigestUtils.md5DigestAsHex(signStr.getBytes());
//                    if (sign.equals(sign2) == false) {
//                        throw new RuntimeException("签名错误");
//                    }
//                    return true;
//                }
//            }
        }


        return true;
    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {


    }


    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {


    }

}
