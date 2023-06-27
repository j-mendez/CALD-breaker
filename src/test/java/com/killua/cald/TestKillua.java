
package com.killua.cald;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestKillua {

    @Test
    public void testCheckStatus() {
        assertEquals(true, Killua.checkStatus(2));
    }

}