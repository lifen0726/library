package tw.library.filter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String phone = obtainUsername(request);
        String password = obtainPassword(request);

        if (phone == null) {
            phone = "";
        }

        if (password == null) {
            password = "";
        }

        phone = phone.trim();

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(phone, password);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    // 需要修改這裡來獲取請求中的手機號碼和密碼
    @Override
    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter("phone");
    }
}

