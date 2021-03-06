/*
 * Copyright (C) 2014 The Android Open Source Project
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

package dexfuzz.fuzzers;

import dexfuzz.Options;
import dexfuzz.listeners.BaseListener;

/**
 * Superclass for fuzzing strategies that perform multiple fuzzes, and want
 * their inputs to come from the input list in a round-robin fashion.
 */
public abstract class FuzzerMultiple extends Fuzzer {
  protected int iterations;

  protected FuzzerMultiple(BaseListener listener) {
    super(listener);
  }

  @Override
  protected String getNextInputFilename() {
    String inputFile = Options.inputFileList.get(0);
    if (Options.inputFileList.size() > 1) {
      int nextIndex = iterations % Options.inputFileList.size();
      inputFile = Options.inputFileList.get(nextIndex);
    }
    listener.handleFuzzingFile(inputFile);
    return inputFile;
  }
}
