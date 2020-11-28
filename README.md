# Exercise-microservices

Assignment for an interview consisting in creating 2 services: 
- ImporterService: an HTTP service that can receive these reservations string and stores them into a file.
- Compaction service: computes some figures out of every reservation. It is not important that it actually does something meaningful. The only important thing is that its job takes some time to complete (e.g. 5 seconds).

The core part of the task is to think about different ways of communication among these services. 

### Framing conditions:
it uses Java and the Spring Framework.
