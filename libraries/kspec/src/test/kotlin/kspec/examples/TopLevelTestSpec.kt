package kspec.examples

import io.damo.kspec.Spec
import org.junit.Assert.assertThat
import org.hamcrest.Matchers.equalTo


/**
 * If your class only has one function,
 * you probably do not want to use a #describe block.
 */
class RunnableSpec: Spec({
    test {
        val runnable = MyRunnable()
        assertThat(runnable.value, equalTo(1))

        runnable.run()
        assertThat(runnable.value, equalTo(2))

        runnable.run()
        assertThat(runnable.value, equalTo(3))
    }
})

class MyRunnable : Runnable {

    var value = 1
        private set

    override fun run() {
        value ++
    }
}