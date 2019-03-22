/*
 * Copyright 2013-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.aws.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

/**
 * @author Agim Emruli
 */
@SpringBootTest(classes = BootMailSenderAwsTest.BootMailSenderAwsTestConfig.class)
public class BootMailSenderAwsTest extends MailSenderAwsTest {

	@SpringBootApplication
	@PropertySource({ "classpath:Integration-test-config.properties",
			"file://${els.config.dir}/access.properties",
			"file://${els.config.dir}/mail.properties" })
	static class BootMailSenderAwsTestConfig {

		@Value("file://${els.config.dir}/mail.properties")
		private Resource mailConfigResource;

		@Bean(name = "mail")
		public PropertiesFactoryBean mail() {
			PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
			factoryBean.setLocation(this.mailConfigResource);
			return factoryBean;
		}

	}

}
