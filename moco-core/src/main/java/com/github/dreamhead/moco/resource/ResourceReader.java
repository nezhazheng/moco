package com.github.dreamhead.moco.resource;

import com.github.dreamhead.moco.HttpRequest;
import com.google.common.base.Optional;

public interface ResourceReader {
    byte[] readFor(final Optional<HttpRequest> request);
}
