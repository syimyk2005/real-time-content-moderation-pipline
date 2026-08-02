package streamguard.ingestservice.config;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import streamguard.ingestservice.service.RateLimiterService;

import java.io.IOException;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    private final RateLimiterService rateLimiter;

    public RateLimitInterceptor(RateLimiterService rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    @Override
    public boolean preHandle(HttpServletRequest req,
                             HttpServletResponse res, Object handler) throws IOException {
        String key = resolveKey(req);
        Bucket bucket = rateLimiter.resolveBucket(key);

        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);
        if (probe.isConsumed()) {
            res.addHeader("X-Rate-Limit-Remaining",
                String.valueOf(probe.getRemainingTokens()));
            return true;
        } else {
            long waitSeconds = probe.getNanosToWaitForRefill() / 1_000_000_000;
            res.setStatus(429);
            res.addHeader("X-Rate-Limit-Retry-After-Seconds",
                String.valueOf(waitSeconds));
            res.getWriter().write("Too Many Requests");
            return false;
        }
    }

    private String resolveKey(HttpServletRequest req) {
        String xff = req.getHeader("X-Forwarded-For");
        return xff != null ? xff.split(",")[0].trim() : req.getRemoteAddr();
    }
}