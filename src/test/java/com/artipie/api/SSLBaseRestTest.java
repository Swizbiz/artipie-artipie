/*
 * The MIT License (MIT) Copyright (c) 2020-2021 artipie.com
 * https://github.com/artipie/artipie/LICENSE.txt
 */
package com.artipie.api;

import com.artipie.http.auth.Authentication;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.junit5.VertxTestContext;
import java.util.Optional;
import org.eclipse.jetty.http.HttpStatus;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.Test;

/**
 * Base test for SSL.
 * @since 0.26
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
//@checkstyle AbbreviationAsWordInNameCheck (1 line)
abstract class SSLBaseRestTest extends RestApiServerBase {
    @Test
    void generatesToken(final Vertx vertx, final VertxTestContext ctx) throws Exception {
        this.requestAndAssert(
            vertx, ctx, new TestRequest(
                HttpMethod.POST, "/api/v1/oauth/token",
                new JsonObject().put("name", "Alice").put("pass", "wonderland")
            ), Optional.empty(),
            response -> {
                MatcherAssert.assertThat(
                    response.statusCode(),
                    new IsEqual<>(HttpStatus.OK_200)
                );
                MatcherAssert.assertThat(
                    response.body().toString(),
                    new StringContains("{\"token\":")
                );
            }
        );
    }

    @Override
    Authentication auth() {
        return new Authentication.Single("Alice", "wonderland");
    }

    @Override
    String layout() {
        return "flat";
    }
}
