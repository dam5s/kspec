package kspec

import io.damo.kspec.Spec
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat

class PersonSpec : Spec() {
    lateinit var person: Person

    init {
        before {
            person = buildPerson(firstName = "Jane", lastName = "Doe")
        }

        describe("#fullName") {
            context {
                assertThat(person.fullName(), equalTo("Jane Doe"))
            }

            context("with a middle name") {
                person = buildPerson(
                    firstName = "John",
                    middleName = "William",
                    lastName = "Doe"
                )

                assertThat(person.fullName(), equalTo("John W. Doe"))
            }
        }

        describe("#greeting") {
            context {
                assertThat(person.greeting(), equalTo("Greetings Jane!"))
            }
        }
    }
}

fun buildPerson(
    firstName: String = "John",
    lastName: String = "Doe",
    middleName: String? = null
) = Person(firstName, lastName, middleName)

data class Person(
    val firstName: String,
    val lastName: String,
    val middleName: String? = null
)

fun Person.fullName() = "$firstName $lastName"

fun Person.greeting() = "Greetings $firstName!"