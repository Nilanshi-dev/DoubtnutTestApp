/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.doubtnuttestapp;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;

/**
 * A generic class that holds a value with its loading status.
 *
 * @param <T>
 */
public class Resource<T> {
    public static final int ERROR_STATUS = 404;
    public static final int SUCCESS = 90;
    public static final int LOADING = 91;
    public static final int ERROR = 92;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({SUCCESS, LOADING, ERROR})
    @interface Status {
    }

    public final int status;

    @Nullable
    public String message;

    @Nullable
    public T data;

    @Nullable
    private Integer errorType;

    @Nullable
    private Object miscInfo;

    public Resource(@Status int status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public Resource(int status, @Nullable Integer errorType) {
        this.status = status;
        this.errorType = errorType;
    }

    public Resource(int status, @Nullable Integer errorType, @Nullable String msg) {
        this.status = status;
        this.errorType = errorType;
        this.message = msg;
    }

    public Resource(int status, @Nullable Object miscInfo) {
        this.status = status;
        this.miscInfo = miscInfo;
    }

    public static <T> Resource<T> success(@Nullable T data) {
        return new Resource<>(SUCCESS, data, null);
    }


    public static <T> Resource<T> error(String msg, @Nullable Integer errorType) {
        return new Resource<>(ERROR, errorType, msg);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(LOADING, data, null);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Resource<?> resource = (Resource<?>) o;

        if (status != resource.status) {
            return false;
        }
        if (!Objects.equals(message, resource.message)) {
            return false;
        }
        return Objects.equals(data, resource.data);
    }

    @NotNull
    @Override
    public String toString() {
        return "Resource{" + "status=" + status + ", message='" + message + '\'' + ", data=" + data + '}';
    }
}