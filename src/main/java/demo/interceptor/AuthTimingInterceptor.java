package demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AuthTimingInterceptor implements HandlerInterceptor {

    private static final String START_TIME_KEY = "startTime";

    // 1. GHI LOG THỜI GIAN (PRE-HANDLE)
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Lưu thời gian bắt đầu xử lý request
        request.setAttribute(START_TIME_KEY, System.currentTimeMillis());

        // Optional: ghi log URL
        System.out.println(">>> Request URI: " + request.getRequestURI());

        // **Test mode: không kiểm tra quyền ADMIN**
        // Nếu muốn bật kiểm tra quyền thực tế, thêm đoạn code kiểm tra X-AUTH-ROLE
        return true; // luôn cho phép request đi tiếp
    }

    // 2. GHI LOG THỜI GIAN SAU KHI XỬ LÝ (POST-HANDLE)
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

        Long startTime = (Long) request.getAttribute(START_TIME_KEY);
        if (startTime != null) {
            long totalTime = System.currentTimeMillis() - startTime;
            System.out.println("--- LOG THỜI GIAN XỬ LÝ ---");
            System.out.println("URI: [" + request.getRequestURI() + "]");
            System.out.println("Tổng thời gian xử lý: " + totalTime + " ms");
        }
    }
}
