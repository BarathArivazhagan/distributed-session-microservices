package com.barath.app;

import org.apache.geode.cache.client.ClientRegionShortcut;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.EnablePool;
import org.springframework.data.gemfire.config.xml.GemfireConstants;
import org.springframework.session.data.gemfire.config.annotation.web.http.EnableGemFireHttpSession;

@SpringBootApplication
@ClientCacheApplication(name="inventory-client-cache")
@EnableGemFireHttpSession(regionName="gemfire-http-sessions", maxInactiveIntervalInSeconds= 60, clientRegionShortcut = ClientRegionShortcut.PROXY)
@EnablePool(name=GemfireConstants.DEFAULT_GEMFIRE_POOL_NAME,
locatorsString= "localhost[10334]"
,subscriptionEnabled= true)
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
}
