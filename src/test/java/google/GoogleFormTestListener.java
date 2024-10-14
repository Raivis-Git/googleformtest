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
                Reporter.log("Test Failed: " + throwable.getMessage(), true);
                Reporter.getCurrentTestResult().setThrowable(new AssertionError(throwable.getMessage()));
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

        private void logTestSteps(ITestResult result) {
            Reporter.log("Test Steps:", true);
            Object instance = result.getInstance();
            if (!(instance instanceof TestStepLogger))
                return;
            for (String step : ((TestStepLogger) instance).getTestSteps()) {
                Reporter.log("- " + step, true);
            }
        }

}
