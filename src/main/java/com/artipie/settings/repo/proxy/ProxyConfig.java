/*
 * The MIT License (MIT) Copyright (c) 2020-2021 artipie.com
 * https://github.com/artipie/artipie/LICENSE.txt
 */
package com.artipie.settings.repo.proxy;

import com.artipie.asto.Storage;
import com.artipie.http.client.auth.Authenticator;
import java.time.Duration;
import java.util.Collection;
import java.util.Optional;

/**
 * Proxy repository config.
 *
 * @since 0.12
 */
public interface ProxyConfig {

    /**
     * Get all configured remote endpoints.
     *
     * @return Remote endpoints.
     */
    Collection<? extends Remote> remotes();

    /**
     * Proxy repository remote.
     *
     * @since 0.12
     */
    interface Remote {

        /**
         * Get URL.
         *
         * @return URL.
         */
        String url();

        /**
         * Get authenticator.
         *
         * @return Authenticator.
         */
        Authenticator auth();

        /**
         * Get cache storage.
         *
         * @return Cache storage, empty if not configured.
         */
        Optional<CacheStorage> cache();
    }

    /**
     * Proxy cache storage config.
     * @since 0.23
     */
    interface CacheStorage {
        /**
         * Storage settings of cache.
         * @return Storage.
         */
        Storage storage();

        /**
         * Max available size of cache storage in bytes.
         * @return Max available size.
         */
        Long maxSize();

        /**
         * Obtains time to live for cache storage.
         * @return Time to live for cache storage.
         */
        Duration timeToLive();
    }
}
