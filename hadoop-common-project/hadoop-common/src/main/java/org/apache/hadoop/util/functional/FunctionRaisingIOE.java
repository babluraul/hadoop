/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.util.functional;

import java.io.IOException;
import java.io.UncheckedIOException;

/**
 * Function of arity 1 which may raise an IOException.
 * @param <T> type of arg1
 * @param <R> type of return value.
 */
@FunctionalInterface
public interface FunctionRaisingIOE<T, R> {

  /**
   * Apply the function.
   * @param t argument 1
   * @return result
   * @throws IOException Any IO failure
   */
  R apply(T t) throws IOException;

  /**
   * Apply unchecked.
   * @param t argument
   * @return the evaluated function
   * @throws UncheckedIOException IOE raised.
   */
  default R unchecked(T t) {
    try {
      return apply(t);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }
}
