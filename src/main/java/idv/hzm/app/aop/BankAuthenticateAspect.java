package idv.hzm.app.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class BankAuthenticateAspect {

	@Before(value = "@annotation(idv.hzm.app.aop.BankAuthenticate)")
	public void getAccountOperationInfo(JoinPoint joinPoint) {

//		Object[] args = joinPoint.getArgs();
//	    MethodSignature ms = (MethodSignature) joinPoint.getSignature();
//	    Method m = ms.getMethod();
//
//	    Annotation[][] parameterAnnotations = m.getParameterAnnotations();
//
//	    for (int i = 0; i < parameterAnnotations.length; i++) {
//	        Annotation[] annotations = parameterAnnotations[i];
//	        System.out.println("I am checking parameter: " + args[i]);
//	        for (Annotation annotation : annotations) {
//	            System.out.println(annotation);
//
////	            if (annotation.annotationType() == Standardized.class) {
////	                System.out.println("we have a Standardized Parameter with type = "
////	                        + ((Standardized) annotation).type());
////	            }
//	        }
//	    }
//
//		// Method Information
//		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//
//		System.out.println("full method description: " + signature.getMethod());
//		System.out.println("method name: " + signature.getMethod().getName());
//		System.out.println("declaring type: " + signature.getDeclaringType());
//
//		// Method args
//		System.out.println("Method args names:");
//		Arrays.stream(signature.getParameterNames()).forEach(s -> System.out.println("arg name: " + s));
//
//		System.out.println("Method args types:");
//		Arrays.stream(signature.getParameterTypes()).forEach(s -> System.out.println("arg type: " + s));
//
//		System.out.println("Method args values:");
//		Arrays.stream(joinPoint.getArgs()).forEach(o -> System.out.println("arg value: " + o.toString()));
//		
//		
//
//		// Additional Information
//		System.out.println("returning type: " + signature.getReturnType());
//		System.out.println("method modifier: " + Modifier.toString(signature.getModifiers()));
//		Arrays.stream(signature.getExceptionTypes()).forEach(aClass -> System.out.println("exception type: " + aClass));

	}

	private final static String API_KEY = "api-key";

	// 只有使用ProceedingJoinPoint，才能通過proceed(Object[])方法改變入參的值
	@Around("@annotation(idv.hzm.app.aop.BankAuthenticate)")
	public Object paramReplace(ProceedingJoinPoint pjp) throws Throwable {

		// 判斷是否有 Authenticate Annotation 沒有的話略過
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method method = signature.getMethod();
		BankAuthenticate bankAuthenticate = method.getAnnotation(BankAuthenticate.class);// Method annotation
		if (bankAuthenticate != null) {
			// 獲取request 從head請求取的參數
			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes();
			HttpServletRequest request = attributes.getRequest();
			String token = request.getHeader(API_KEY);
			// 判斷是否存活 否 拋EXECTION 是 嘗試解密 失敗 拋EXECTION 成功 會得到字串並轉換成Map物件
			// ?
			Map<String, Object> tokenMap = new HashMap<>();
			tokenMap.put("type", "MEMBER");
			tokenMap.put("device", "web");
			tokenMap.put("memberId", "0123456789");
			// 讀取 Type 是否與 Annotation 設置的驗證類型符合
			String type = (tokenMap.get("type") instanceof String) ? (String) tokenMap.get("type") : "";

			BankAuthenticateType[] bankAuthenticateTypeArray = bankAuthenticate.types();
			for (BankAuthenticateType bankAuthenticateType : bankAuthenticateTypeArray) {
				switch (bankAuthenticateType) {
				case MEMBER:
					if (BankAuthenticateType.MEMBER.name().equals(type)) {
						
						ObjectMapper mapper = new ObjectMapper();// 定義一個jackson序列化方式
						// Map<String, Object> map = null;// 定義接收參數的容器
						// Class<?> clazz = null;// 定義接收參數類型的class
						int index = -1;// 定義獲取args位置信息的index
						
						Object[] args = pjp.getArgs();// 獲取方法參數
						Annotation[][] parameterAnnotations = method.getParameterAnnotations();
						// for循環是針對存在多參數的情況
						for (int i = 0; i < args.length; i++) {
							// 由於參數存在繼承關係,所以遍歷到我們需要的參數時,這裡返回true
							if (args[i] instanceof Member) {
								Annotation[] annotations = parameterAnnotations[i];
								for (Annotation annotation : annotations) {
									if (annotation.annotationType() == BankMember.class) {
										// clazz = args[i].getClass();// 賦值class信息
										// map = mapper.convertValue(args[i], Map.class);// 將該參數轉換為map類型，方便處理
										index = i;// 獲取index信息
										// String userId = "123456";// 這裡封裝了通過userId獲取userName的方法
										// String userName = "黃先生";
										// 遍歷map中的所有參數,找出名字為createBy/updateBy的參數，並對其賦值
//											for (Map.Entry entry : map.entrySet()) {
//												if (entry.getKey().equals("userId")) {
//													map.put("userId", userId);
//												}
//												if (entry.getKey().equals("userName")) {
//													map.put("userName", userName);
//												}
//											}
									}
								}
							}
						}
						// **下面很重要,如果不進行賦值操作及調用proceed方法,則入參改變不會生效**
						if (index >= 0) {
							args[index] = mapper.convertValue(tokenMap, Member.class);
						}
						return pjp.proceed(args);
					}
					break;
				case VISITOR:
					if (BankAuthenticateType.VISITOR.name().equals(type)) {

					}
					break;
				case OTP:
					if (BankAuthenticateType.OTP.name().equals(type)) {

					}
					break;
				}
			}

		}
		return pjp.proceed();
	}
}
