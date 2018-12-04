package com.github.tonybaines.gestalt

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

object Configurations {
    fun <T> definedBy(configInterface: Class<T>): ConfigurationBuilder<T> =
            ConfigurationBuilder(configInterface)


    enum class Behaviour {
        isOptional
    }

    class ConfigurationBuilder<T>(private val configInterface: Class<T>) {
        fun fromPropertiesFile(filePath: String, behaviour: Behaviour): ConfigurationBuilder<T> {
            return this
        }

        fun done(): T =
                Proxy.newProxyInstance(
                        ConfigurationBuilder::class.java.classLoader,
                        arrayOf(configInterface),
                        ConfigProxy(configInterface)
                ) as T
    }
}

class ConfigProxy<T>(private val configInterface: Class<T>) : InvocationHandler {
    override fun invoke(proxy: Any?, method: Method, args: Array<out Any>?): Any? {

        println("${method.name} $args")
        return if (method.returns(String::class.java)) {
            "foo"
        } else if (method.returns(java.lang.Boolean::class.java)) {
            false
        } else if (method.returns(Integer::class.java)) {
            42
        } else if (method.returns(Double::class.java)) {
            42.0
        } else if (method.returns(Long::class.java)) {
            42.0.toLong()
        } else if (method.returnType.isEnum) {
            null
        } else {
            println("Unmatched return type ${method.returnType}")
            true
        }
    }

    private fun Method.returns(clazz: Class<*>) = this.returnType.isAssignableFrom(clazz)

}
