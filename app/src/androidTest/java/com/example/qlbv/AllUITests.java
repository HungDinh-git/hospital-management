package com.example.qlbv;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        RegisterActivityTest.class,
        LoginActivityTest.class
})
public class AllUITests {
    // Không cần code thêm, chỉ khai báo để gom test
}
