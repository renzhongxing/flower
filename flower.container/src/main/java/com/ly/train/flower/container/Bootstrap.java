/**
 * Copyright © 2019 同程艺龙 (zhihui.li@ly.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ly.train.flower.container;

import java.net.URL;
import com.ly.train.flower.common.loader.FlowerClassLoader;
import com.ly.train.flower.logging.Logger;
import com.ly.train.flower.logging.LoggerFactory;

/**
 * @author leeyazhou
 *
 */
public abstract class Bootstrap {


  public static void main(String[] args) {
    String mainClass = "com.ly.train.flower.container.FlowerBootstrap";
    final Logger logger = LoggerFactory.getLogger(Bootstrap.class);
    logger.info("flower start class : {}", mainClass);
    try {
      ClassLoader classLoader = new FlowerClassLoader(new URL[0], Bootstrap.class.getClassLoader());
      @SuppressWarnings("unchecked")
      Class<Bootstrap> bootstrapClass = (Class<Bootstrap>) Class.forName(mainClass, true, classLoader);
      Bootstrap bootstrap = bootstrapClass.newInstance();
      bootstrap.startup(null);
    } catch (Throwable e) {
      logger.error("fail to start flower container.", e);
      e.printStackTrace();
    }
  }

  private void startup(String configLocation) throws Throwable {
    doStartup(configLocation);
  }

  /**
   * 启动flower container
   */
  public abstract void doStartup(String configLocation) throws Throwable;
}
