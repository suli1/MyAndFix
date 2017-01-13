package com.example.suli690.myandfix;

/**
 * Created by suli690 on 2017/1/13.
 */

public class Test {

    /***
     * hotfix埋点验证
     */
    public void printHotFixTest() {
        functionPublic();
        functionProtected();
        functionPrivate();
        functionStaticPublic();
        functionStaticProtected();
        functionStaticPrivate();
        InnerClass innerClass = new InnerClass();
        innerClass.printTest();
        HotFixUtil.logJavaClass(FINAL_STRING);
    }

    private final static String FINAL_STRING = "常量";

    public void functionPublic() {
        HotFixUtil.logJavaClass("public function");
    }

    protected void functionProtected() {
        HotFixUtil.logJavaClass("protected function");
    }

    private void functionPrivate() {
        HotFixUtil.logJavaClass("private function");
    }

    public static void functionStaticPublic() {
        HotFixUtil.logJavaClass("static public function");
    }

    protected static void functionStaticProtected() {
        HotFixUtil.logJavaClass("static protected function");
    }

    private static void functionStaticPrivate() {
        HotFixUtil.logJavaClass("static private function");
    }

    private class InnerClass {

        public void printTest() {
            functionPublic();
            functionProtected();
            functionPrivate();
        }

        public void functionPublic() {
            HotFixUtil.logJavaClass("inner class public function");
        }

        protected void functionProtected() {
            HotFixUtil.logJavaClass("inner class protected function");
        }

        private void functionPrivate() {
            HotFixUtil.logJavaClass("inner class private function");
        }
    }

}
