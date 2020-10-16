package org.knowm.xchange.gateiov4.service;

import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import java.time.Duration;
import org.knowm.xchange.client.ResilienceRegistries;

/** Created by lin on 2020-10-16. */
public final class GateioResilience {

  public static final String RATE_LIMITER_WITHDRAW = "withdraw";

  public static final String RATE_LIMITER_REQUEST_PER_SECOND_IN_SPOT = "requestPerSecondInSpot";

  public static final String RATE_LIMITER_REQUEST_PER_SECOND_IN_FUTURES =
      "requestPerSecondInFutures";

  private GateioResilience() {}

  public static ResilienceRegistries createRegistries() {
    ResilienceRegistries registries = new ResilienceRegistries();
    registries
        .rateLimiters()
        .rateLimiter(
            RATE_LIMITER_WITHDRAW,
            RateLimiterConfig.from(registries.rateLimiters().getDefaultConfig())
                .limitRefreshPeriod(Duration.ofSeconds(10))
                .limitForPeriod(1)
                .build());
    registries
        .rateLimiters()
        .rateLimiter(
            RATE_LIMITER_REQUEST_PER_SECOND_IN_SPOT,
            RateLimiterConfig.from(registries.rateLimiters().getDefaultConfig())
                .limitRefreshPeriod(Duration.ofSeconds(1))
                .limitForPeriod(300)
                .build());
    registries
        .rateLimiters()
        .rateLimiter(
            RATE_LIMITER_REQUEST_PER_SECOND_IN_FUTURES,
            RateLimiterConfig.from(registries.rateLimiters().getDefaultConfig())
                .limitRefreshPeriod(Duration.ofSeconds(1))
                .limitForPeriod(200)
                .build());
    return registries;
  }
}
