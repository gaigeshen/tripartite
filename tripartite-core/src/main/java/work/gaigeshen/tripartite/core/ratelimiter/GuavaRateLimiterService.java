package work.gaigeshen.tripartite.core.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;

import java.time.Duration;
import java.util.Map;
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
        ArgumentValidate.notTrue(permitsPerSecond <= 0, "permitsPerSecond is invalid");
        this.permitsPerSecond = permitsPerSecond;
    }

    @Override
    public void acquire(String key, int permits) {
        ArgumentValidate.notNull(key, "key cannot be null");
        ArgumentValidate.notTrue(permits <= 0, "permits is invalid");
        rateLimiters.computeIfAbsent(key, k -> {
            double permitsPerSecond = getPermitsPerSecond();
            return RateLimiter.create(permitsPerSecond);
        }).acquire(permits);
    }

    @Override
    public boolean acquire(String key, int permits, long timeout) {
        ArgumentValidate.notNull(key, "key cannot be null");
        ArgumentValidate.notTrue(permits <= 0, "permits is invalid");
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
