#Groovy notes
 
## Up and running with Intellij

* JAXB library dependencies added manual in the project structure settings in intellij. No Maven pom or gradle file used.
* The JAXB library is located in the groovy installation done by SDKman somewhere in the home dir. 

# Groovysh

* Shell doesn't use `def` for variables (it will evaluate only). This behaviour can be changed by `:= interpreterMode`
