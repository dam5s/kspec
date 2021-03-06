package aspen.examples

import io.damo.aspen.Test
import org.assertj.core.api.Assertions.assertThat
import org.mockito.Mockito.*

/**
 * If you prefer having fields that get initialized in your #before,
 * you can follow this example.
 * Unfortunately you will need to describe your tests in the #init.
 */
class BusinessControllerTestExample : Test() {

    lateinit var mockRepo: BusinessRepository
    lateinit var controller: BusinessController

    init {
        before {
            mockRepo = mock(BusinessRepository::class.java)
            controller = BusinessController(mockRepo)
        }

        describe("#create") {
            test {
                val business = Business(name = "Wayne Enterprises")
                doReturn(business).`when`(mockRepo).create(anyString())

                val response = controller.create("Wayne Ent.")

                assertThat(response).isEqualTo(BusinessResponse(business, true))
                verify(mockRepo).create("Wayne Ent.")
            }

            test("repository creation error") {
                doReturn(null).`when`(mockRepo).create(anyString())

                val response = controller.create("Wayne Ent.")

                assertThat(response).isEqualTo(BusinessResponse(null as Business?, false))
            }
        }
    }
}

class BusinessController(val repository: BusinessRepository) {
    fun create(businessName: String) = BusinessResponse(repository.create(businessName), true)
}

interface BusinessRepository {
    fun create(businessName: String): Business?
}

data class BusinessResponse<T>(val value: T?, val success: Boolean)

data class Business(val name: String)
