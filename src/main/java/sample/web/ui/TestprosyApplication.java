/*
 * Copyright 2012-2015 the original author or authors.
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

package sample.web.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sample.web.ui.service.ProxyService;

@SpringBootApplication
public class TestprosyApplication {
	private static final Logger log = LoggerFactory.getLogger(TestprosyApplication.class);
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(TestprosyApplication.class, args);
		
	}

	@Bean
	public ProxyService demo(ProxyService service) {
		log.info("@@@@@@@@@@@@@@@@@@Customer found with findByLastName('Bauer'):");
		return null;
	}
}
