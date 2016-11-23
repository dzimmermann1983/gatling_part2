package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._


class LoginSimulation extends Simulation{

  object GetStartpage {
    val getStartpageRequest = exec(http("Frontpage")
      .get("/")
      .check(status.is(200)))
  }

  object Login {
    val doLoginRequest = exec(http("Login Request")
      .post("/login")
      .headers(headers_1)
      .formParam("firstName", "Some")
      .formParam("password", "User"))
  }

  val httpConf = http.baseURL("http://localhost:8080")
    .inferHtmlResources(BlackList(""".*\.css""", """.*\.js""", """.*\.ico""", """.*\.png""", """.*\.gif""", """.*\.svg""", """.*\.jpg"""))
    .acceptHeader("*/*")
    .acceptEncodingHeader("gzip, deflate, sdch")
    .acceptLanguageHeader("de-DE,de;q=0.8,en-US;q=0.6,en;q=0.4")
    .userAgentHeader("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36")

  val headers_1 = Map("Content-Type" -> """application/x-www-form-urlencoded""")

  val scn = scenario("Login Simulation")
      .exec(
        GetStartpage.getStartpageRequest
      )
      .pause(10)
      .exec(
        Login.doLoginRequest
      )


  setUp(scn.inject(atOnceUsers(1)))
    .protocols(httpConf)
}
