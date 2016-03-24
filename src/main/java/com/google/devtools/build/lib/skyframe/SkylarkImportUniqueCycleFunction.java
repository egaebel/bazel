// Copyright 2015 The Bazel Authors. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.google.devtools.build.lib.skyframe;

import com.google.common.collect.ImmutableList;
import com.google.devtools.build.lib.cmdline.Label;
import com.google.devtools.build.skyframe.SkyKey;

/**
 * Emits an error message exactly once when a Skylark import cycle is found when running inlined
 * {@link SkylarkImportLookupFunction}s.
 */
class SkylarkImportUniqueCycleFunction extends AbstractChainUniquenessFunction<Label> {

  static SkyKey key(ImmutableList<Label> cycle) {
    return ChainUniquenessUtils.key(SkyFunctions.SKYLARK_IMPORT_CYCLE, cycle);
  }

  @Override
  protected String getConciseDescription() {
    return "cycle in referenced extension files";
  }

  @Override
  protected String getHeaderMessage() {
    return "";
  }

  @Override
  protected String getFooterMessage() {
    return "";
  }

  @Override
  protected String elementToString(Label importLabel) {
    return importLabel.toString();
  }
}