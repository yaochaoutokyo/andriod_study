// IMyAidlInterface.aidl
package com.example.andriodlearning9;

import com.example.andriodlearning9.Position;
import com.example.andriodlearning9.Staff;

// Declare any non-default types here with import statements

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    Position get(in Staff staff);
}
