## NOTES & PROGRESS
# 28.05.2019
Removed happypath solution
Refactored a lot of code
KISS, no need for supporting database now
I need to dockerize this application to linux+openjdk11
+ evolve, test and refactor it a lot

# 16/17.05.2019
EvaluationService should be doing the bare minimum - evaluate (sandboxing, compilation, running tests, evaluation)
Everything it needs should be provided in request:
- problemID
- userID
- all test cases code
- submission code
- programming language
- (final submission flag might be not needed? handling whether it was final submission can be done externally)

We then would have evaluation service working as simple engine - this is desired.
Context of service needs to be kept to absolute minimum.

Even results itself might be sent to some "statistics service"? Or should it be kept here and when someone would want to
check their past results - there would be a call to evaluation service. Not sure if it doesn't break SRP. TBD.

Gateway/LB -> Service Discovery -> Evaluation Service -> SomeKindOfFrontEndClient to orchestrate all calls and lifycycle?
-> ProblemService (? its going to be basically a bare database with some translation layer)
-> UserService (? its going to be basically a bare database with some translation layer)
-> Statistics Service (some available easy to setup microservice solution I guess)
-> GlobalConfigService

is it wise tho to split it into many microservices? EvaluationService - yes, fine, but rest can be done effectively in one monolithic app.


