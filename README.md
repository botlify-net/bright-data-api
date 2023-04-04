# Bright-data API

## How to install locally ?

### Requirements

You need to have maven installed on your machine, and make sure you have a JDK 8 installed.

### How to install the package locally ?

This installation skip the tests, because the tests need a valid API key to run.
```bash
mvn clean install -Dmaven.test.skip=true
```

If you want to run the tests, you need to set the API key in the environment variable:
- BRIGHT_DATA_API_KEY: Your API key.
- BRIGHT_DATA_ZONE_NAME: The zone name you want to use for the tests.
- BRIGHT_DATA_CUSTOMER_ID: The customer id you want to use for the tests.

```bash
mvn clean install
```

### How to use ?

Instantiate the BrightDataAPI class with your API key.
```java
String brightDataApiKey = "youApiKey";
BrightDataAPI api = new BrightDataAPI(brightDataApiKey);
```