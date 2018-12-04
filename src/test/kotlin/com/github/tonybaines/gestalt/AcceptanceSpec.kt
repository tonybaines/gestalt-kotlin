package com.github.tonybaines.gestalt

import com.github.tonybaines.gestalt.Configurations.Behaviour.*
import org.junit.Test


class AcceptanceSpec {

    @Test
    fun `Can read a simple config file`() {
        val config = Configurations.definedBy(SimpleConfig::class.java)
                .fromPropertiesFile("simple-config.properties", isOptional)
                .done()

        config.isEnabled
        config.defaultOnly
        config.level
        config.name
    }

    @Test
    fun `Can read a more complex config file`() {
        val config = Configurations.definedBy(TestConfig::class.java)
                .fromPropertiesFile("test-config.properties", isOptional)
                .done()

        config.longValue
        config.doubleValue
        config.handedness
    }
}