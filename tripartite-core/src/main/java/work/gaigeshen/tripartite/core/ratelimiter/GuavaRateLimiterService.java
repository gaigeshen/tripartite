package work.gaigeshen.tripartite.core.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

import java.time.Duration;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 这是默认的限流服务实现
 *
 * @author gaigeshen
 * @see #create(double)
 */
public class GuavaRateLimiterService implements RateLimiterService {

    private final Map<String, RateLimiter> rateLimiters = new ConcurrentHashMap<>();

    private final double permitsPerSecond;

    public GuavaRateLimiterService(double permitsPerSecond) {
        if (permitsPerSecond <= 0) {
            throw new IllegalArgumentException("permitsPerSecond");
        }
        this.permitsPerSecond = permitsPerSecond;
    }

    @Override
    public void acquire(String key, int permits) {
        if (Objects.isNull(key)) {
            throw new IllegalArgumentException("key cannot be null");
        }
        if (permits <= 0) {
            throw new IllegalArgumentException("permits is invalid");
        }
        rateLimiters.computeIfAbsent(key, k -> {
            double permitsPerSecond = getPermitsPerSecond();
            return RateLimiter.create(permitsPerSecond);
        }).acquire(permits);
    }

    @Override
    public boolean acquire(String key, int permits, long timeout) {
        if (Objects.isNull(key)) {
            throw new IllegalArgumentException("key cannot be null");
        }
        if (permits <= 0) {
            throw new IllegalArgumentException("permits is invalid");
        }
        return rateLimiters.computeIfAbsent(key, k -> {
            double permitsPerSecond = getPermitsPerSecond();
            return RateLimiter.create(permitsPerSecond);
        }).tryAcquire(permits, Duration.ofSeconds(timeout));
    }

    @Override
    public double getPermitsPerSecond() {
        return permitsPerSecond;
    }
}
