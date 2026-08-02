package streamguard.ingestservice.service;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.BucketConfiguration;
import io.github.bucket4j.redis.lettuce.cas.LettuceBasedProxyManager;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.function.Supplier;

@Service
public class RateLimiterService {

    private final LettuceBasedProxyManager<String> proxyManager;

    public RateLimiterService(LettuceBasedProxyManager<String> proxyManager) {
        this.proxyManager = proxyManager;
    }

    public Bucket resolveBucket(String key) {
        Supplier<BucketConfiguration> config = () -> BucketConfiguration.builder()
            .addLimit(limit -> limit
                .capacity(200)
                .refillGreedy(5, Duration.ofSeconds(30)))
            .build();

        return proxyManager.builder().build(key, config);
    }
}