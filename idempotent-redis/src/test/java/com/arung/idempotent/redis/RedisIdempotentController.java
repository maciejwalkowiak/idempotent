package com.arung.idempotent.redis;

import com.arung.idempotent.core.annotation.Idempotent;
import com.arung.idempotent.core.aspect.IdempotentAspect;
import com.arung.idempotent.core.persistence.IdempotentStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisIdempotentController {

    public record Asset(String id, String type, String name) {}

    public record AssetResponse(String id, String type, String name, String url) {}

    @Idempotent(key="#asset.id", ttlInSeconds = 60)
    @PostMapping("/assets")
    public AssetResponse createAsset(@RequestBody Asset asset) {
        System.out.println(asset.name);
        return new AssetResponse(asset.id, asset.type, asset.name, "https://github.com/arun0009/idempotent");
    }
}
