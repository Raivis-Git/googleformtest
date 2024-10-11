package google;

import org.testng.*;

public class GoogleFormTestListener implements ITestListener, IInvokedMethodListener {

        @Override
        public void onTestSuccess(ITestResult result) {
            String testName = result.getMethod().getMethodName();
            Reporter.log("Test Passed: " + testName, true);
            logTestSteps(result);
        }

        @Override
        public void onTestFailure(ITestResult result) {
            Throwable throwable = result.getThrowable();
            if (throwable != null) {
                String customMessage = extractCustomMessage(throwable.getMessage());
                result.setThrowable(new AssertionError(customMessage));
                Reporter.log("Test Failed: " + customMessage, true);
            }
            logTestSteps(result);
        }

        @Override
        public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
            if (method.isTestMethod()) {
                Reporter.log("Starting test: " + method.getTestMethod().getMethodName(), true);
            }
        }

        @Override
        public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        }

        private String extractCustomMessage(String fullMessage) {
            String[] parts = fullMessage.split("\n");
            if (parts.length > 0) {
                String customPart = parts[0].trim();
                return customPart.replaceFirst("^java\\.lang\\.AssertionError:\\s*", "");
            }
            return fullMessage;
        }

        private void logTestSteps(ITestResult result) {
            Reporter.log("Test Steps:", true);
            Object instance = result.getInstance();
            if (instance instanceof TestStepLogger) {
                for (String step : ((TestStepLogger) instance).getTestSteps()) {
                    Reporter.log("- " + step, true);
                }
            }
        }

}
